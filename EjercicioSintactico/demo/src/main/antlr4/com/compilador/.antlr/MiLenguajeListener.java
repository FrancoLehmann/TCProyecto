// Generated from c:/Users/Usuario/Desktop/2025/TC/TCProyecto/Ejercicios_Sintacticos/Ejercicio/demo/src/main/antlr4/com/compilador/MiLenguaje.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiLenguajeParser}.
 */
public interface MiLenguajeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(MiLenguajeParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(MiLenguajeParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConParentesis}
	 * labeled alternative in {@link MiLenguajeParser#a}.
	 * @param ctx the parse tree
	 */
	void enterConParentesis(MiLenguajeParser.ConParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConParentesis}
	 * labeled alternative in {@link MiLenguajeParser#a}.
	 * @param ctx the parse tree
	 */
	void exitConParentesis(MiLenguajeParser.ConParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VacioA}
	 * labeled alternative in {@link MiLenguajeParser#a}.
	 * @param ctx the parse tree
	 */
	void enterVacioA(MiLenguajeParser.VacioAContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VacioA}
	 * labeled alternative in {@link MiLenguajeParser#a}.
	 * @param ctx the parse tree
	 */
	void exitVacioA(MiLenguajeParser.VacioAContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimerHexa}
	 * labeled alternative in {@link MiLenguajeParser#b}.
	 * @param ctx the parse tree
	 */
	void enterPrimerHexa(MiLenguajeParser.PrimerHexaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimerHexa}
	 * labeled alternative in {@link MiLenguajeParser#b}.
	 * @param ctx the parse tree
	 */
	void exitPrimerHexa(MiLenguajeParser.PrimerHexaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HexaRecursiva}
	 * labeled alternative in {@link MiLenguajeParser#c}.
	 * @param ctx the parse tree
	 */
	void enterHexaRecursiva(MiLenguajeParser.HexaRecursivaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HexaRecursiva}
	 * labeled alternative in {@link MiLenguajeParser#c}.
	 * @param ctx the parse tree
	 */
	void exitHexaRecursiva(MiLenguajeParser.HexaRecursivaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VacioC}
	 * labeled alternative in {@link MiLenguajeParser#c}.
	 * @param ctx the parse tree
	 */
	void enterVacioC(MiLenguajeParser.VacioCContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VacioC}
	 * labeled alternative in {@link MiLenguajeParser#c}.
	 * @param ctx the parse tree
	 */
	void exitVacioC(MiLenguajeParser.VacioCContext ctx);
}