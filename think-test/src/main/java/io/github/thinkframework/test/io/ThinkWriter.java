package io.github.thinkframework.test.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/**
 * 就是代理下
 * 查看close(), flush()的调用时机
 */
public class ThinkWriter extends Writer implements Appendable, Closeable, Flushable {

    private Writer writer;

    protected ThinkWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void write(int c) throws IOException {
        writer.write(c);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        writer.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        writer.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        writer.write(str, off, len);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return writer.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return writer.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return writer.append(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writer.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
