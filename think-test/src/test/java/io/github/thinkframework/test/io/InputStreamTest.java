package io.github.thinkframework.test.io;

import org.junit.Test;

import java.io.InputStream;

public class InputStreamTest {

    @Test
    public void test() {
        try (InputStream inputStream = new ThinkInpuStream(null)){

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
