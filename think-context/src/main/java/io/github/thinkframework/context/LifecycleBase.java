package io.github.thinkframework.context;

import java.util.Collection;

public abstract class LifecycleBase implements Lifecycle {
    protected volatile boolean running;

    protected Collection<LifecycleListener> listeners;
    @Override
    public void start() {
        LifecycleEvent event = LifecycleEvent.START;
        if(listeners != null && !listeners.isEmpty()) {
            for (LifecycleListener listener : listeners) {
                listener.exec(event);
            }
        }
        startInternal();
    }

    @Override
    public void stop() {
        LifecycleEvent event = LifecycleEvent.STOP;
        if(listeners != null && !listeners.isEmpty()) {
            for (LifecycleListener listener : listeners) {
                listener.exec(event);
            }
        }
        stopInternal();
    }

    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    protected abstract void startInternal();
    protected abstract void stopInternal();
}
