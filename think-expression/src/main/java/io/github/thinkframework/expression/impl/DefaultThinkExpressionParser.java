package io.github.thinkframework.expression.impl;

import io.github.thinkframework.expression.ThinkExpression;
import io.github.thinkframework.expression.ThinkExpressionParser;
import io.github.thinkframework.expression.ast.AddNode;
import io.github.thinkframework.expression.ast.StringNode;
import io.github.thinkframework.expression.ast.ThinkNode;

import java.util.List;
import java.util.Stack;

/**
 * 语法分析
 * @author lixiaobin
 */
public class DefaultThinkExpressionParser implements ThinkExpressionParser {

    public ThinkExpression parse(String expressionString) {
        List<Token> tokens = new DefaultThinkExpressionLexer().parse(expressionString);

        Stack<ThinkNode> left = new Stack();
        Stack<ThinkNode> right = new Stack();
        for(int i =0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if(Token.STRING.equals(token.getType())){
                left.push(new StringNode(token.getValue()));
            } else if(Token.ADD.equals(token.getType())){
                if(Token.STRING.equals(tokens.get(++i).getType())) {
                    left.push(new AddNode(left.pop(),new StringNode(tokens.get(i).getValue())));
                }
            } else if(Token.SUBTRACT.equals(token.getType())){
            } else if(Token.MULTIPLY.equals(token.getType())){
            } else if(Token.DIVIDE.equals(token.getType())){
            }
        }

        ThinkExpression expression = new DefaultThinkExpression(left.peek());

        return expression;
    }
}
