package io.github.thinkframework.expression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelExpressionParserTest {
    final static Logger logger = LoggerFactory.getLogger(SpelExpressionParserTest.class);

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("1+2");
        logger.info("{}", expression.getValue());
    }
}
