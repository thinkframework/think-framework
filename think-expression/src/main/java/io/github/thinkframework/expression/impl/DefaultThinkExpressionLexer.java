package io.github.thinkframework.expression.impl;

import io.github.thinkframework.expression.ThinkExpression;
import io.github.thinkframework.expression.ThinkExpressionParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 词法分析
 */
public class DefaultThinkExpressionLexer {

    public List<Token> parse(String expressionString) {
        char[] chars = expressionString.toCharArray();
        List<Token> list = new ArrayList();
        Token token = null;
        for(char c : chars){
            if(Pattern.compile(Token.STRING).matcher(String.valueOf(c)).matches()){
                if(token == null) {
                    token = new Token(Token.STRING, String.valueOf(c));
                } else if (Token.STRING.equals(token.getType())){
                    token.setValue(token.getValue().concat(String.valueOf(c)));
                    continue;
                } else {
                    token = new Token(Token.STRING, String.valueOf(c));
                }
            } else if(Pattern.compile(Token.ADD).matcher(String.valueOf(c)).matches()){
                if(token == null) {
                    token = new Token(Token.ADD, String.valueOf(c));
                } else if (Token.ADD.equals(token.getType())){
                } else {
                    token = new Token(Token.ADD, String.valueOf(c));
                }
            } else if(Pattern.compile(Token.SUBTRACT).matcher(String.valueOf(c)).matches()){
                if(token == null) {
                    token = new Token(Token.SUBTRACT, String.valueOf(c));
                } else if (Token.SUBTRACT.equals(token.getType())){
                } else {
                    token = new Token(Token.SUBTRACT, String.valueOf(c));
                }
            } else if(Pattern.compile(Token.MULTIPLY).matcher(String.valueOf(c)).matches()){
                if(token == null) {
                    token = new Token(Token.MULTIPLY, String.valueOf(c));
                } else if (Token.MULTIPLY.equals(token.getType())){
                } else {
                    token = new Token(Token.MULTIPLY, String.valueOf(c));
                }
            } else if(Pattern.compile(Token.DIVIDE).matcher(String.valueOf(c)).matches()){
                if(token == null) {
                    token = new Token(Token.DIVIDE, String.valueOf(c));
                } else if (Token.DIVIDE.equals(token.getType())){
                } else {
                    token = new Token(Token.DIVIDE, String.valueOf(c));
                }
            }
            list.add(token);
        }
        return list;
    }

}
