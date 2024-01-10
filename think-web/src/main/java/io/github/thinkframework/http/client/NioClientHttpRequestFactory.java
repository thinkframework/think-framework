package io.github.thinkframework.http.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 基于NIO的Http Request
 * @author lixiaobin
 */
public class NioClientHttpRequestFactory implements ClientHttpRequestFactory {
    private static final Logger logger = LoggerFactory.getLogger(NioClientHttpRequestFactory.class);

    Executor executor = Executors.newSingleThreadExecutor();

    private volatile boolean finish;

    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return new NioClientHttpRequest(uri,httpMethod);
    }

    class NioHttpHeaders extends HttpHeaders {

    }

    public class NioClientHttpRequest implements ClientHttpRequest {

        private URI uri;
        private HttpMethod method;
        private HttpHeaders headers = new HttpHeaders();

        private final String protocol = "HTTP/1.1";

        private Selector selector;
        private ByteBuffer body = ByteBuffer.allocate(1024);

        public NioClientHttpRequest(URI uri, HttpMethod method) {
            this.uri = uri;
            this.method = method;
        }

        @Override
        public ClientHttpResponse execute() throws IOException {

            try {
                selector = Selector.open();

                SocketChannel socketChannel = SocketChannel.open(); // 打开监听
                socketChannel.configureBlocking(false); // 链接的时候就报错

                connect(socketChannel);

                FutureTask<NioClientHttpResponse> task = new FutureTask<>(new Worker(socketChannel));
                executor.execute(task);
                return task.get();
            } catch (IOException e) {
                logger.error("",e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        /**
         * 打开Channel
         * @param socketChannel
         * @return
         * @throws IOException
         */
        private boolean connect(SocketChannel socketChannel) throws IOException {
            // 链接
            socketChannel.connect(new InetSocketAddress(uri.getHost(), uri.getPort()));
            // 注册监听
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            boolean finishConnect = false;
            SelectionKey selectionKey = null;
            while (!finishConnect) {
                int count = selector.select();
                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    if (selectionKeys != null) {
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            selectionKey = iterator.next();
                            iterator.remove();
                            if (selectionKey.isConnectable()) {

                                SelectableChannel selectableChannel = selectionKey.channel();
                                selectableChannel.configureBlocking(false);
                                if(((SocketChannel)selectableChannel).isConnectionPending()){
                                    while(!((SocketChannel)selectableChannel).finishConnect()){
                                        // nothing.
                                    }
                                    finishConnect = true; // 链接成功
                                }
                            }
                        }
                    }
                }
            }

            logger.debug("链接成功");
            // 取消链接事件的关注
            selectionKey.interestOps(selectionKey.interestOps() &(~SelectionKey.OP_CONNECT));
            socketChannel.register(selector,selectionKey.interestOps());
            return true;
        }

        /**
         * 请求行
         */
        private ByteBuffer requestLine() {
            ByteBuffer requestLine = ByteBuffer.allocate(1024); // fixme 写死,改掉
            requestLine.put(method.name().getBytes(StandardCharsets.UTF_8));
            space(requestLine);
            requestLine.put(uri.getPath().getBytes(StandardCharsets.UTF_8));
            space(requestLine);
            requestLine.put(protocol.getBytes(StandardCharsets.UTF_8));
            return requestLine;
        }

        /**
         * 请求头
         */
        private ByteBuffer requestHeader() {
            ByteBuffer requestHeader = ByteBuffer.allocate(1024); // fixme 写死,改掉
            headers.forEach((key,values) -> {
                requestHeader.put(key.getBytes(StandardCharsets.UTF_8));
                requestHeader.put(": ".getBytes(StandardCharsets.UTF_8));
                requestHeader.put(values.get(0).getBytes(StandardCharsets.UTF_8)); // fixme 之后修复,现在只能取一个
                rn(requestHeader);
            });
            return requestHeader;
        }

        /**
         * 空格
         * @param byteBuffer
         */
        private ByteBuffer space(ByteBuffer byteBuffer) {
            return byteBuffer.put(" ".getBytes(StandardCharsets.UTF_8));
        }

        /**
         * 换行符
         * @param byteBuffer
         */
        private ByteBuffer rn(ByteBuffer byteBuffer) {
            return byteBuffer.put("\r\n".getBytes(StandardCharsets.UTF_8));
        }


        /**
         * 换行符
         */
        private ByteBuffer rn() {
            return ByteBuffer.wrap("\r\n".getBytes(StandardCharsets.UTF_8));
        }

        @Override
        public OutputStream getBody() throws IOException {
            return new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    body.put((byte) b);
                }
            };
        }

        @Override
        public String getMethodValue() {
            return method.name();
        }

        @Override
        public URI getURI() {
            return uri;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

        class Worker implements Callable<NioClientHttpResponse> {

            private SocketChannel socketChannel;

            public Worker(SocketChannel socketChannel) {
                this.socketChannel = socketChannel;
            }

            @Override
            public NioClientHttpResponse call() {
                try {
                    selector = Selector.open();

                    // 注册监听
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                    while (true) {
                        int count = selector.select();
                        if (count > 0) {
                            Set<SelectionKey> selectionKeys = selector.selectedKeys();
                            if (selectionKeys != null) {
                                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                                while (iterator.hasNext()) {
                                    SelectionKey selectionKey = iterator.next();
                                    iterator.remove();
                                    if (selectionKey.isWritable()) {
                                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                        write(socketChannel);

                                        selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_WRITE));
                                        socketChannel.register(selector, selectionKey.interestOps());
                                    } else if (selectionKey.isReadable()) {
                                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                        ByteBuffer byteBuffer = read(socketChannel);
                                        selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_READ));
                                        socketChannel.register(selector, selectionKey.interestOps());
                                        socketChannel.close();
                                        return new NioClientHttpResponse(byteBuffer);
                                    }
                                }
                            }
                        }
                    }


                } catch (ClosedChannelException e) {
                    finish = true; // fixme 先这么处理
                    e.printStackTrace();
                } catch (IOException e) {
                    finish = true; // fixme 先这么处理
                    e.printStackTrace();
                } catch (Exception e) {
                    finish = true; // fixme 先这么处理
                    e.printStackTrace();
                }
                return null;
            }

            private ByteBuffer read(SocketChannel socketChannel) throws IOException {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.read(byteBuffer);
                return byteBuffer;
            }

            private void write(SocketChannel socketChannel) throws IOException {
                ByteBuffer requestLine = requestLine();
                ByteBuffer requestHeader = requestHeader();
                try {
                    socketChannel.write(requestLine.flip());
                    socketChannel.write(rn());
                    socketChannel.write(requestHeader.flip());
                    body.flip();
                    if(body.limit() > 0) {
                        ByteBuffer contentLength = ByteBuffer.wrap((HttpHeaders.CONTENT_LENGTH + ": " + body.limit()).getBytes(StandardCharsets.UTF_8));
                        socketChannel.write(contentLength);
                        socketChannel.write(rn());
                        socketChannel.write(rn());
                        contentLength.clear();
                        socketChannel.write(body);
                        socketChannel.write(rn());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    requestLine.clear();
                    requestHeader.clear();
                    body.clear();
                }
                logger.info("等待响应");
            }

        }
    }

    public class NioClientHttpResponse implements ClientHttpResponse {
        char s = ' ';
        char r = '\r';
        char n = '\n';

        private ByteBuffer byteBuffer;

        public NioClientHttpResponse(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
            responseLine();
            responseHeader();
            responsBody();
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return null;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 0;
        }

        @Override
        public String getStatusText() throws IOException {
            return null;
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            try {
                byteBuffer.flip();
                int limit = byteBuffer.limit();
                byte[] dst = new byte[limit];
                byteBuffer.get(dst);
                return new ByteArrayInputStream(dst);
            } finally {
                byteBuffer.clear();
            }
        }

        @Override
        public HttpHeaders getHeaders() {
            return null;
        }

        private void responseLine(){
            byteBuffer.flip();
            List<String> list = new ArrayList<>();
            list.add(new String());
            String tmp = "";
            for (int i = 0;i < byteBuffer.limit();i++) {
                byte c = byteBuffer.get();
                if(c == s) {
                    list.add(new String());
                    continue;
                } else if (c == r) {
                    c = byteBuffer.get();
                    if(c == n) {
                        break;
                    }
                }
                list.set(list.size()-1, list.get(list.size()-1).concat(String.valueOf((char) c)));
            }
            list.size();
        }


        private void responseHeader(){
//            byteBuffer.flip();
            List<String> list = new ArrayList<>();
            list.add(new String());
            String tmp = "";
            for (int i = 0;i < byteBuffer.limit();i++) {
                byte c = byteBuffer.get();
                if (c == r) {
                    c = byteBuffer.get();
                    if(c == n) {
                        if(list.get(list.size()-1).length() == 0){
                            break;
                        }
                        list.add(new String());
                        continue;
                    }
                }
                list.set(list.size()-1, list.get(list.size()-1).concat(String.valueOf((char) c)));
            }
            list.size();
        }


        private void responsBody(){
            byte[] bytes = new byte[byteBuffer.remaining()];
            ByteBuffer body = byteBuffer.get(bytes);
            body.get();
        }
    }

}
