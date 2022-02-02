package io.github.thinkframework.expression;

import io.github.thinkframework.expression.antlr.CalcBaseVisitor;
import io.github.thinkframework.expression.antlr.CalcLexer;
import io.github.thinkframework.expression.antlr.CalcParser;
import io.github.thinkframework.expression.antlr.CalcVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.RuleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntlrExpressionParserTest {
    final static Logger logger = LoggerFactory.getLogger(AntlrExpressionParserTest.class);

    public static void main(String[] args) {
        // 词法分析
        Lexer lexer = new CalcLexer(CharStreams.fromString("1+2"));
        // Stream
        TokenStream tokenStream = new CommonTokenStream(lexer);
        // 语法分析
        CalcParser parser = new CalcParser(tokenStream);
        // 上下文
        CalcParser.ProgContext progContext = parser.prog();
        // 访问者
        CalcVisitor visitor = new CalcBaseVisitor();
        // 访问
        visitor.visit(progContext);
    }
}
