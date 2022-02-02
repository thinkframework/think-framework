package io.github.thinkframework.expression.ast;

public class StringNode implements ThinkNode{
    private Object value;

    public StringNode(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
