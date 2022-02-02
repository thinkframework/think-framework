package io.github.thinkframework.test.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * 就是代理下
 * 查看close()的调用时机
 */
public class ThinkInpuStream extends InputStream implements Closeable {

    private static final Logger logger = Logger.getGlobal();

    private InputStream inputStream;

    public ThinkInpuStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return inputStream.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return inputStream.readAllBytes();
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        return inputStream.readNBytes(len);
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        return inputStream.readNBytes(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return inputStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    /**
     * 监听关闭方法是否被调用
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        logger.info("close() 被调用");
        inputStream.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        inputStream.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        inputStream.reset();
    }

    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        return inputStream.transferTo(out);
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }
}
