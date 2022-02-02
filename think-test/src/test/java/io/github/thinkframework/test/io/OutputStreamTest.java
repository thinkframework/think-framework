package io.github.thinkframework.test.io;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

public class OutputStreamTest {

    @Test
    public void test() {
        try (OutputStream outputStream = new ThinkOutputStream(null)){
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
