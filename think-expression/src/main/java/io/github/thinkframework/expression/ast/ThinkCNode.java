package io.github.thinkframework.expression.ast;

public interface ThinkCNode extends ThinkNode{
    ThinkNode getLeft();
    ThinkNode getRight();
}
