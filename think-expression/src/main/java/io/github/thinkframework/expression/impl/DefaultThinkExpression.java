package io.github.thinkframework.expression.impl;

import io.github.thinkframework.expression.ThinkExpression;
import io.github.thinkframework.expression.ast.ThinkCNode;
import io.github.thinkframework.expression.ast.ThinkNode;

/**
 * 表达式
 */
public class DefaultThinkExpression implements ThinkExpression {

    ThinkNode node;

    public DefaultThinkExpression(ThinkNode node) {
        this.node = node;
    }

    public Object getValue() {
        return node.getValue();
    }
}
