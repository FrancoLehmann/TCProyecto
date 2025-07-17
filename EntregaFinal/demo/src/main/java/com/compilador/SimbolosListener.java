package com.compilador;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;

import com.compilador.TablaSimbolos.Simbolo;

import java.util.ArrayList;
import java.util.List;

/**
 * Listener mejorado para construir la tabla de símbolos y realizar verificación de tipos
 */
public class SimbolosListener extends MiLenguajeBaseListener {
    
    private TablaSimbolos tablaSimbolos;
    private List<String> errores;
    private String tipoRetornoActual; // Para verificar return
    private List<String> warnings; // ← nueva lista
    
    public SimbolosListener() {
        this.tablaSimbolos = new TablaSimbolos();
        this.errores = new ArrayList<>();
        this.tipoRetornoActual = null;
        this.warnings = new ArrayList<>();
    }
    
    /**
     * Obtiene la tabla de símbolos construida
     */
    public TablaSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }
    
    /**
     * Obtiene la lista de errores semánticos
     */
    public List<String> getErrores() {
        return errores;
    }

    // getters para exponer los warnings
    public List<String> getWarnings() {
        return warnings;
    }
    
    /**
     * Cuando se encuentra una declaración de función
     */
    @Override
    public void enterDeclaracionFuncion(MiLenguajeParser.DeclaracionFuncionContext ctx) {
        // Obtener información de la función
        String nombre = ctx.ID().getText();
        String tipo = ctx.tipo().getText();
        int linea = ctx.ID().getSymbol().getLine();
        int columna = ctx.ID().getSymbol().getCharPositionInLine();
        
        // Crear símbolo para la función
        TablaSimbolos.Simbolo simbolo = new TablaSimbolos.Simbolo(
            nombre, tipo, "funcion", linea, columna, "global"
        );
        
        // Agregar parámetros si existen
        if (ctx.parametros() != null) {
            for (MiLenguajeParser.ParametroContext paramCtx : ctx.parametros().parametro()) {
                String tipoParam = paramCtx.tipo().getText();
                String nombreParam = paramCtx.ID().getText();
                
                // Agregar tipo de parámetro a la función
                simbolo.addParametro(tipoParam);
                
                // Crear símbolo para el parámetro
                TablaSimbolos.Simbolo paramSimbolo = new TablaSimbolos.Simbolo(
                    nombreParam, tipoParam, "parametro", 
                    paramCtx.ID().getSymbol().getLine(),
                    paramCtx.ID().getSymbol().getCharPositionInLine(),
                    nombre  // El ámbito del parámetro es el nombre de la función
                );
                
                // Agregar el parámetro a la tabla de símbolos
                if (!tablaSimbolos.agregar(paramSimbolo)) {
                    errores.add("Error semántico en línea " + paramCtx.ID().getSymbol().getLine() + 
                              ": Parámetro duplicado '" + nombreParam + "'");
                }
            }
        }
        
        // Agregar la función a la tabla de símbolos
        if (!tablaSimbolos.agregar(simbolo)) {
            errores.add("Error semántico en línea " + linea + 
                      ": Función '" + nombre + "' ya declarada");
        }
        
        // Cambiar el ámbito actual
        tablaSimbolos.setAmbito(nombre);
        
        // Guardar el tipo de retorno para verificar las sentencias return
        tipoRetornoActual = tipo;
    }
    
    /**
     * Al salir de una declaración de función
     */
    @Override
    public void exitDeclaracionFuncion(MiLenguajeParser.DeclaracionFuncionContext ctx) {
        // Verificar si la función no void tiene al menos un return
        String tipo = ctx.tipo().getText();
        String nombre = ctx.ID().getText();
        
        if (!tipo.equals("void")) {
            // Podríamos hacer un análisis más profundo para garantizar que todos los caminos tienen return
            // pero eso requeriría un análisis de flujo de control más complejo
            boolean tieneReturn = false;
            
            for (int i = 0; i < ctx.bloque().sentenciaInterior().size(); i++) {
                if (ctx.bloque().sentenciaInterior(i).retorno() != null) {
                    tieneReturn = true;
                    break;
                }
            }
            
            if (!tieneReturn) {
                errores.add("Error semántico en función '" + nombre + "': Función con tipo de retorno '" + 
                          tipo + "' debe tener al menos una sentencia return");
            }
        }
        
        // Restaurar el ámbito global y el tipo de retorno
        tablaSimbolos.setAmbito("global");
        tipoRetornoActual = null;
    }
    
    /**
     * Cuando se encuentra una declaración de variable
     */
    @Override
    public void enterDeclaracionVariable(MiLenguajeParser.DeclaracionVariableContext ctx) {
        String nombre = ctx.ID().getText();
        String tipo = ctx.tipo().getText();
        int linea = ctx.ID().getSymbol().getLine();
        int columna = ctx.ID().getSymbol().getCharPositionInLine();
        
        // Crear y agregar el símbolo
        TablaSimbolos.Simbolo simbolo = new TablaSimbolos.Simbolo(
            nombre, tipo, "variable", linea, columna, tablaSimbolos.getAmbito()
        );
        
        if (!tablaSimbolos.agregar(simbolo)) {
            errores.add("Error semántico en línea " + linea + 
                      ": Variable '" + nombre + "' ya declarada en este ámbito");
        }
    }

    @Override
    public void exitDeclaracionVariable(MiLenguajeParser.DeclaracionVariableContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();

        // 1. Asegurarse de que la variable esté en la tabla
        TablaSimbolos.Simbolo simbolo = tablaSimbolos.buscar(nombre);
        if (simbolo == null) {
            errores.add("❌ [Crítico] Línea " + linea + ": Variable '" + nombre + "' no declarada");
            return;
        }

        // 2. Si la declaración incluye [INTEGER], es un arreglo
        if (ctx.CA() != null && ctx.INTEGER() != null) {
            int size = Integer.parseInt(ctx.INTEGER().getText());
            simbolo.setArray(true)
                .setArraySize(size);
        }

        // 3. Si hay inicializador (= expresion), marcamos uso e inicialización
        if (ctx.expresion() != null) {            
            simbolo.setInicializada(true);
        }
    }

    
    /**
     * Cuando se encuentra una asignación
     */
    @Override
    public void enterAsignacion(MiLenguajeParser.AsignacionContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();

        // 1. Comprobar existencia
        TablaSimbolos.Simbolo simbolo = tablaSimbolos.buscar(nombre);
        if (simbolo == null) {
            errores.add("Variable '" + nombre + "' no declarada (línea " + linea + ")");
            return;
        }

        // 2. Solo variables y parámetros pueden recibir asignación
        String categoria = simbolo.getCategoria();
        if (!categoria.equals("variable") && !categoria.equals("parametro")) {
            errores.add("No se puede asignar a '" + nombre +
                    "' porque no es una variable (línea " + linea + ")");
            return;
        }

        // 3. Marcar uso e inicialización
        simbolo.setUsada(true);
        if (categoria.equals("variable")) {
            simbolo.setInicializada(true);
        }

      // 4. Determinar la expresión del lado derecho
        List<MiLenguajeParser.ExpresionContext> exprs = ctx.expresion();
        MiLenguajeParser.ExpresionContext rhs = exprs.get(exprs.size() - 1);
        String tipoVar = simbolo.getTipo();
        String tipoExp = determinarTipoExpresion(rhs);

        // 5. Comprobar incompatibilidad de tipos,
        //    permitiendo int -> double implícito
        boolean incompatible = 
            !"desconocido".equals(tipoExp)
            && !tipoVar.equals(tipoExp)
            && !(tipoVar.equals("double") && tipoExp.equals("int"));

        if (incompatible) {
            errores.add(" [Crítico] Línea " + linea +
                    ": variable '" + nombre + "' es de tipo '" + tipoVar +
                    "' pero se le asigna una expresión de tipo '" + tipoExp + "'");
        }
    }

    
    /**
     * Cuando se encuentra una expresión de variable
     */
    @Override
    public void enterExpVariable(MiLenguajeParser.ExpVariableContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        
        TablaSimbolos.Simbolo simbolo = tablaSimbolos.buscar(nombre);
        if (simbolo == null) {
            errores.add("Error semántico en línea " + linea + 
                      ": Identificador '" + nombre + "' no declarado");
        }else{
            simbolo.setUsada(true); // Marcar como usado
            simbolo.setInicializada(true); // Marcar como inicializada si se usa en una expresión
        }
    }
    
    /**
     * Cuando se encuentra una llamada a función
     */
    @Override
    public void enterExpFuncion(MiLenguajeParser.ExpFuncionContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();
        
        // Verificar si la función existe
        TablaSimbolos.Simbolo simbolo = tablaSimbolos.buscar(nombre);
        if (simbolo == null) {
            errores.add("Error semántico en línea " + linea + 
                      ": Función '" + nombre + "' no declarada");
            return;
        }
        
        // Verificar que sea una función
        if (!simbolo.getCategoria().equals("funcion")) {
            errores.add("Error semántico en línea " + linea + 
                      ": '" + nombre + "' no es una función");
            return;
        }
        
        // Verificar número de argumentos
        int numArgumentosEsperados = simbolo.getParametros().size();
        int numArgumentosRecibidos = ctx.argumentos() == null ? 0 : ctx.argumentos().expresion().size();
        
        if (numArgumentosEsperados != numArgumentosRecibidos) {
            errores.add("Error semántico en línea " + linea + 
                      ": Función '" + nombre + "' espera " + numArgumentosEsperados + 
                      " argumentos, pero recibió " + numArgumentosRecibidos);
        }
        
        simbolo.setUsada(true); // Marcar como usada

        // Para una verificación completa de tipos, necesitaríamos determinar el tipo de cada expresión
    }
    
    @Override
    public void enterRetorno(MiLenguajeParser.RetornoContext ctx) {
        if (tipoRetornoActual == null) {
            errores.add("Línea " + ctx.getStart().getLine() +
                    ": sentencia return fuera de una función");
            return;
        }
        
        // void: no debe devolver valor
        if ("void".equals(tipoRetornoActual)) {
            if (ctx.expresion() != null) {
                errores.add("Línea " + ctx.getStart().getLine() +
                        ": función void no debe retornar un valor");
            }
        } else {
            // funciones no-void: deben devolver algo
            if (ctx.expresion() == null) {
                errores.add("Línea " + ctx.getStart().getLine() +
                        ": función de tipo '" + tipoRetornoActual +
                        "' debe retornar un valor");
            } else {
                // validación de tipo de la expresión de retorno
                String tipoExp = determinarTipoExpresion(ctx.expresion());
                if (!"desconocido".equals(tipoExp) && !tipoRetornoActual.equals(tipoExp)) {
                    errores.add(" Línea " + ctx.getStart().getLine() +
                            ": return de tipo '" + tipoExp +
                            "' incompatible con tipo de función '" + tipoRetornoActual + "'");
                }
            }
        }
    }
            
    
    /**
     * Al encontrar un nodo de error en el árbol de análisis sintáctico
     */
    @Override
    public void visitErrorNode(ErrorNode node) {
        errores.add("Error sintáctico en token: " + node.getText());
    }
    
    /**
     * Método para determinar el tipo de una expresión (implementación básica)
     * Una implementación completa requeriría más lógica para evaluar expresiones complejas
     */
    private String determinarTipoExpresion(MiLenguajeParser.ExpresionContext ctx) {
        if (ctx instanceof MiLenguajeParser.ExpEnteroContext) {
            return "int";
        }
        if (ctx instanceof MiLenguajeParser.ExpDecimalContext) {
            return "double";
        }
        if (ctx instanceof MiLenguajeParser.ExpCaracterContext) {
            return "char";
        }
        if (ctx instanceof MiLenguajeParser.ExpCadenaContext) {
            return "String";
        }
        if (ctx instanceof MiLenguajeParser.ExpVariableContext) {
            String nombre = ((MiLenguajeParser.ExpVariableContext) ctx).ID().getText();
            Simbolo s = tablaSimbolos.buscar(nombre);
            return s != null ? s.getTipo() : "desconocido";
        }
        if (ctx instanceof MiLenguajeParser.ExpFuncionContext) {
            String nombre = ((MiLenguajeParser.ExpFuncionContext) ctx).ID().getText();
            Simbolo s = tablaSimbolos.buscar(nombre);
            return s != null ? s.getTipo() : "desconocido";
        }
        if (ctx instanceof MiLenguajeParser.ExpBinariaContext) {
            MiLenguajeParser.ExpBinariaContext bin = (MiLenguajeParser.ExpBinariaContext) ctx;
            // Obtener el operador directamente
            String op = bin.getChild(1).getText();
            String t1 = determinarTipoExpresion(bin.expresion(0));
            String t2 = determinarTipoExpresion(bin.expresion(1));
            switch (op) {
                case "+":
                    if (t1.equals("String") && t2.equals("String")) return "String";
                    if (t1.equals("int")    && t2.equals("int"))    return "int";
                    if (t1.equals("double") || t2.equals("double")) return "double";
                    break;
                case "-":
                case "*":
                case "/":
                    if (t1.equals("int")    && t2.equals("int"))    return "int";
                    if (t1.equals("double") || t2.equals("double")) return "double";
                    break;
                case "==":
                case "!=":
                case "<":
                case ">":
                case "<=":
                case ">=":
                    return "boolean";
            }
            return "desconocido";
        }
        if (ctx instanceof MiLenguajeParser.ExpParentizadaContext) {
            return determinarTipoExpresion(
                ((MiLenguajeParser.ExpParentizadaContext) ctx).expresion());
        }
        if (ctx instanceof MiLenguajeParser.ExpNegacionContext) {
            return determinarTipoExpresion(
                ((MiLenguajeParser.ExpNegacionContext) ctx).expresion());
        }
        return "desconocido";
    }

     
    private void checkUnusedVariables() {
        for (TablaSimbolos.Simbolo s : tablaSimbolos.getTodos()) {
            if (s.getCategoria().equals("variable") && !s.isUsada()) {
                warnings.add("Warning semántico en línea " + s.getLinea()
                + ": variable '" + s.getNombre() + "' declarada pero no usada");
            }
        }
    }

    private void checkNoFunctionUsage() {
        for (TablaSimbolos.Simbolo s : tablaSimbolos.getTodos()) {
            // Solo funciones (no variables, parámetros u otros símbolos)
            if ("funcion".equals(s.getCategoria()) && !s.isUsada() && !s.getNombre().equals("main")) {
                warnings.add(String.format(
                    "Warning semántico en línea %d: función '%s' declarada pero nunca invocada",
                    s.getLinea(), s.getNombre()
                ));
            }
        }
    }
    
    private void checkUninitializedVariables() {
        for (TablaSimbolos.Simbolo s : tablaSimbolos.getTodos()) {
            // Solo variables (no funciones, parámetros u otros símbolos)
            if ("variable".equals(s.getCategoria()) && !s.isInicializada() && s.isUsada()) {
                warnings.add(String.format(
                    "⚠️ Warning semántico en línea %d: variable '%s' declarada pero no inicializada antes de su uso",
                    s.getLinea(), s.getNombre()
                ));
            }
        }
    }

    

    private void checkUnusedParameters() {
        for (TablaSimbolos.Simbolo s : tablaSimbolos.getTodos()) {
            // Solo parámetros (no variables locales ni funciones)
            if ("parametro".equals(s.getCategoria()) && !s.isUsada()) {
                warnings.add(String.format(
                    "⚠️ Warning semántico en línea %d: parámetro '%s' de la función '%s' no utilizado",
                    s.getLinea(), s.getNombre(), s.getAmbito()
                ));
            }
        }
    }


    @Override
    public void exitPrograma(MiLenguajeParser.ProgramaContext ctx) {
        super.exitPrograma(ctx);
        // aquí llamas a todos tus métodos de validación de warnings:
        checkUnusedVariables();
        checkNoFunctionUsage();
        checkUninitializedVariables();
        checkUnusedParameters();
    }

    @Override
    public void enterExpIndex(MiLenguajeParser.ExpIndexContext ctx) {
        String nombre = ctx.ID().getText();
        int linea = ctx.ID().getSymbol().getLine();

        // 1) La variable debe existir
        TablaSimbolos.Simbolo s = tablaSimbolos.buscar(nombre);
        if (s == null) {
            errores.add("[Crítico] Línea " + linea + ": identificador '" + nombre + "' no declarado");
            return;
        }

        // 2) Debe ser un arreglo
        if (!s.isArray()) {
            errores.add(" [Crítico] Línea " + linea + ": '" + nombre + "' no es un arreglo");
        }

        // 3) El índice debe ser de tipo int
        String tipoIdx = determinarTipoExpresion(ctx.expresion());
        if (!"int".equals(tipoIdx)) {
            errores.add(" [Crítico] Línea " + linea + ": índice de '" + nombre + "' debe ser int, no " + tipoIdx);
        }

        // 4) Si el índice es literal, chequear bounds y emitir warning
        if (ctx.expresion() instanceof MiLenguajeParser.ExpEnteroContext) {
            int idx = Integer.parseInt(ctx.expresion().getText());
            int size = s.getArraySize();
            if (idx < 0 || idx >= size) {
                warnings.add(" [Warning] Línea " + linea +
                            ": índice " + idx + " fuera de rango para '" +
                            nombre + "[" + size + "]'");
            }
        }

        // 5) Marcar el arreglo como usado
        s.setUsada(true);
    }


    @Override
    public void exitExpIndex(MiLenguajeParser.ExpIndexContext ctx) {
        // Marcar la variable como usada si se accede a un índice
        String nombre = ctx.ID().getText();
        Simbolo s = tablaSimbolos.buscar(nombre);
        if (s != null) {
            s.setUsada(true);
        }
    }

}