package io.github.thinkframework.context;

public class ThinkContext {
    private static final ThreadLocal<ThinkContext> context = new ThreadLocal<ThinkContext>(){
        @Override
        protected ThinkContext initialValue() {
            return new ThinkContext();
        }
    };

    public static ThinkContext get(){
        return context.get();
    }

}
