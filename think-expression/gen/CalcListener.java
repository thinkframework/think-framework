// Generated from /Users/lixiaobin/git/think-framework/think-framework/think-expression/Calc.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalcParser}.
 */
public interface CalcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalcParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CalcParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CalcParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code op}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOp(CalcParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code op}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOp(CalcParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opsd}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOpsd(CalcParser.OpsdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opsd}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOpsd(CalcParser.OpsdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opmd}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOpmd(CalcParser.OpmdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opmd}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOpmd(CalcParser.OpmdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code value}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterValue(CalcParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code value}
	 * labeled alternative in {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitValue(CalcParser.ValueContext ctx);
}