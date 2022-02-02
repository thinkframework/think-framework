package io.github.thinkframework.expression.ast;

import java.math.BigDecimal;

public class AddNode implements ThinkCNode{

    private ThinkNode left;

    private ThinkNode right;

    public AddNode(ThinkNode left, ThinkNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object getValue() {
        return new BigDecimal(left.getValue().toString()).add(new BigDecimal(right.getValue().toString()));
    }

    public ThinkNode getLeft() {
        return left;
    }

    public void setLeft(ThinkNode left) {
        this.left = left;
    }

    public ThinkNode getRight() {
        return right;
    }

    public void setRight(ThinkNode right) {
        this.right = right;
    }
}
