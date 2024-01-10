package io.github.thinkframework.context;

public class ThinkThreadLocalContext {
    private static final ThreadLocal<ThinkThreadLocalContext> context = new ThreadLocal<ThinkThreadLocalContext>(){
        @Override
        protected ThinkThreadLocalContext initialValue() {
            return new ThinkThreadLocalContext();
        }
    };

    public static ThinkThreadLocalContext get(){
        return context.get();
    }

}
