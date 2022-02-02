package io.github.thinkframework.test.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;

/**
 * 就是代理下
 * 查看close()的调用时机
 */
public class ThinkReader extends Reader implements Readable, Closeable {

    private Reader reader;

    protected ThinkReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        return reader.read(target);
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return reader.read(cbuf);
    }

    @Override
    public long skip(long n) throws IOException {
        return reader.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return reader.ready();
    }

    @Override
    public boolean markSupported() {
        return reader.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        reader.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        reader.reset();
    }

    @Override
    public long transferTo(Writer out) throws IOException {
        return reader.transferTo(out);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
