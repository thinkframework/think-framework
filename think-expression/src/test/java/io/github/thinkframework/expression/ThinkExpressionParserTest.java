package io.github.thinkframework.expression;

import io.github.thinkframework.expression.impl.DefaultThinkExpressionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThinkExpressionParserTest {
    final static Logger logger = LoggerFactory.getLogger(ThinkExpressionParserTest.class);

    public static void main(String[] args) {
        System.out.println(new DefaultThinkExpressionParser().parse("1+2").getValue());
    }
}
