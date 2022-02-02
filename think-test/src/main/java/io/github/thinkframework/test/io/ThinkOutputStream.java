package io.github.thinkframework.test.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * 就是代理下
 * 查看close(), flush()的调用时机
 */
public class ThinkOutputStream extends OutputStream implements Closeable, Flushable {

    private static final Logger logger = Logger.getGlobal();

    private OutputStream outputStream;

    public ThinkOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        logger.info("flush() 被调用");
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        logger.info("close() 被调用");
        outputStream.close();
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }
}
