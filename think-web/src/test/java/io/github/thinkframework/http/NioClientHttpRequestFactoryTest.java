package io.github.thinkframework.http;

import io.github.thinkframework.http.client.NioClientHttpRequestFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioClientHttpRequestFactoryTest {

    private static final Logger logger = LoggerFactory.getLogger(NioClientHttpRequestFactoryTest.class);

    /**
     * 文件上传
     * @throws IOException
     */
    @Test
    public void multipart() throws IOException {
        String uri = "http://localhost:8081/api/hello/upload";
        String boundary= "--------------------------607416221961439863605994";
        Path path = Files.createTempFile("abc","txt");
        Files.writeString(path,"hello.");

        NioClientHttpRequestFactory.NioClientHttpRequest request =  (NioClientHttpRequestFactory.NioClientHttpRequest) new NioClientHttpRequestFactory()
                .createRequest(URI.create(uri),HttpMethod.POST);
        request.getHeaders().add(HttpHeaders.HOST,"localhost");
        request.getHeaders().add(HttpHeaders.CONTENT_TYPE,"multipart/form-data; boundary="+boundary);
//        request.getHeaders().add(HttpHeaders.CONTENT_LENGTH,String.valueOf(path.toFile().length()));
        request.getBody().write("--".getBytes(StandardCharsets.UTF_8));
        request.getBody().write(boundary.getBytes(StandardCharsets.UTF_8));
        request.getBody().write(rn());
        request.getBody().write("Content-Disposition: form-data; name=\"file\"; filename=\"tmp.txt\"".getBytes(StandardCharsets.UTF_8));
        request.getBody().write(rn());
        request.getBody().write("Content-Type: text/plain".getBytes(StandardCharsets.UTF_8));
        request.getBody().write(rn());
        request.getBody().write(rn());

        request.getBody().write(Files.readAllBytes(path));
        // 结束行
        request.getBody().write(rn());
        request.getBody().write("--".getBytes(StandardCharsets.UTF_8));
        request.getBody().write(boundary.getBytes(StandardCharsets.UTF_8));
        request.getBody().write("--".getBytes(StandardCharsets.UTF_8));
//        request.getBody().write(rn());

        NioClientHttpRequestFactory.NioClientHttpResponse response = (NioClientHttpRequestFactory.NioClientHttpResponse) request.execute();
        InputStream inputStream = response.getBody();
        logger.debug("{}",new String(inputStream.readAllBytes()));
    }

    /**
     * 普通请求
     * @throws IOException
     */
    @Test
    public void json() throws IOException {
        String uri = "http://localhost:8081/api/hello";
        String body = "{\"name\" : \"hello\"}";
        ClientHttpRequest request =  new NioClientHttpRequestFactory()
                .createRequest(URI.create(uri),HttpMethod.POST);
        request.getHeaders().add(HttpHeaders.HOST,"localhost");
        request.getHeaders().add(HttpHeaders.CONTENT_TYPE,"application/json");
//        request.getHeaders().add(HttpHeaders.CONTENT_LENGTH,String.valueOf(body.getBytes(StandardCharsets.UTF_8).length));
        request.getBody().write(body.getBytes(StandardCharsets.UTF_8));
        ClientHttpResponse response = request.execute();
        InputStream inputStream = response.getBody();
        logger.debug("{}",new String(inputStream.readAllBytes()));
    }

    private byte[] rn() {
        return "\r\n".getBytes(StandardCharsets.UTF_8);
    }
}
