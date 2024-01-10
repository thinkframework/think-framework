package io.github.thinkframework.context;

public interface Lifecycle {
    void start();
    void stop();
    boolean isRunning();
}
