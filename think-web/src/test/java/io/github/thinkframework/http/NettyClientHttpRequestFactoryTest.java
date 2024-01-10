package io.github.thinkframework.http;

import io.github.thinkframework.http.client.NioClientHttpRequestFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class NettyClientHttpRequestFactoryTest {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientHttpRequestFactoryTest.class);


    @Test
    public void a() throws IOException {
        String uri = "http://localhost:8081/api/hello";
        String boundary= "-----------------------------7db372eb000e2";
        Path path = Files.createTempFile("abc","txt");
        Files.writeString(path,"hello.");

        ClientHttpRequest request = new Netty4ClientHttpRequestFactory()
                .createRequest(URI.create(uri),HttpMethod.POST);
        request.getHeaders().add(HttpHeaders.HOST,"localhost");
        request.getHeaders().add(HttpHeaders.CONTENT_TYPE,"multipart/form-data; boundary="+boundary);
//        request.getHeaders().add(HttpHeaders.CONTENT_LENGTH,String.valueOf(path.toFile().length()));
        request.getBody().write(boundary.getBytes(StandardCharsets.UTF_8));
        request.getBody().write(Files.readAllBytes(path));
        request.getBody().write(Files.readAllBytes(path));
        request.getBody().write(Files.readAllBytes(path));
        // 结束行
        request.getBody().write(boundary.getBytes(StandardCharsets.UTF_8));
        request.getBody().write("--".getBytes(StandardCharsets.UTF_8));
        NioClientHttpRequestFactory.NioClientHttpResponse response = (NioClientHttpRequestFactory.NioClientHttpResponse) request.execute();
        InputStream inputStream = response.getBody();
    }


    @Test
    public void json() throws IOException {
        String uri = "http://localhost:8081/api/hello";
        String body = "{\"name\" : \"hello\"}";
        ClientHttpRequest request = new Netty4ClientHttpRequestFactory()
                .createRequest(URI.create(uri),HttpMethod.POST);
        request.getHeaders().add(HttpHeaders.HOST,"localhost");
        request.getHeaders().add(HttpHeaders.CONTENT_TYPE,"application/json");
//        request.getHeaders().add(HttpHeaders.CONTENT_LENGTH,String.valueOf(body.getBytes(StandardCharsets.UTF_8).length));
        request.getBody().write(body.getBytes(StandardCharsets.UTF_8));
        ClientHttpResponse response = request.execute();
        InputStream inputStream = response.getBody();
        logger.debug("{}",new String(inputStream.readAllBytes()));
    }
}
