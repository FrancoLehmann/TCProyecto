package com.compilador;

import java.util.*;

public class OptimizadorCodigo {
    private List<String> codigoOriginal;
    private List<String> codigoOptimizado;
    private Map<String, String> constantPropagation;
    private Set<String> deadCode;
    
    public OptimizadorCodigo(List<String> codigo) {
        this.codigoOriginal = new ArrayList<>(codigo);
        this.codigoOptimizado = new ArrayList<>();
        this.constantPropagation = new HashMap<>();
        this.deadCode = new HashSet<>();
    }
    
    public List<String> optimizar() {
        System.out.println("\n=== OPTIMIZACIÓN DE CÓDIGO ===");
        
        // 1. Constant Folding
        constantFolding();
        
        // 2. Constant Propagation
        constantPropagation();
        
        // 3. Dead Code Elimination
        deadCodeElimination();
        
        // 4. Algebraic Simplification
        algebraicSimplification();
        
        System.out.println("Optimización completada:");
        System.out.println("- Instrucciones originales: " + codigoOriginal.size());
        System.out.println("- Instrucciones optimizadas: " + codigoOptimizado.size());
        System.out.println("- Reducción: " + (codigoOriginal.size() - codigoOptimizado.size()) + " instrucciones");
        
        return codigoOptimizado;
    }
    
    private void constantFolding() {
        System.out.println("Aplicando constant folding...");
        for (String instruccion : codigoOriginal) {
            String optimizada = foldConstants(instruccion);
            codigoOptimizado.add(optimizada);
        }
    }
    
    private String foldConstants(String instruccion) {
        // Buscar patrones como: t1 = 5 + 3
        if (instruccion.matches(".*=\\s*\\d+\\s*[+\\-*/]\\s*\\d+.*")) {
            try {
                String[] parts = instruccion.split("=");
                if (parts.length == 2) {
                    String left = parts[0].trim();
                    String right = parts[1].trim();
                    
                    // Evaluar expresiones aritméticas simples
                    if (right.matches("\\d+\\s*\\+\\s*\\d+")) {
                        String[] operands = right.split("\\+");
                        int result = Integer.parseInt(operands[0].trim()) + Integer.parseInt(operands[1].trim());
                        return left + " = " + result;
                    } else if (right.matches("\\d+\\s*\\-\\s*\\d+")) {
                        String[] operands = right.split("\\-");
                        int result = Integer.parseInt(operands[0].trim()) - Integer.parseInt(operands[1].trim());
                        return left + " = " + result;
                    } else if (right.matches("\\d+\\s*\\*\\s*\\d+")) {
                        String[] operands = right.split("\\*");
                        int result = Integer.parseInt(operands[0].trim()) * Integer.parseInt(operands[1].trim());
                        return left + " = " + result;
                    } else if (right.matches("\\d+\\s*/\\s*\\d+")) {
                        String[] operands = right.split("/");
                        int divisor = Integer.parseInt(operands[1].trim());
                        if (divisor != 0) {
                            int result = Integer.parseInt(operands[0].trim()) / divisor;
                            return left + " = " + result;
                        }
                    }
                }
            } catch (Exception e) {
                // Si hay error, devolver instrucción original
            }
        }
        return instruccion;
    }
    
    private void constantPropagation() {
        System.out.println("Aplicando constant propagation...");
        List<String> nuevoCodigo = new ArrayList<>();
        Map<String, String> constants = new HashMap<>();
        
        for (String instruccion : codigoOptimizado) {
            // Detectar asignaciones de constantes: x = 5
            if (instruccion.matches("\\w+\\s*=\\s*\\d+")) {
                String[] parts = instruccion.split("=");
                String var = parts[0].trim();
                String value = parts[1].trim();
                constants.put(var, value);
                nuevoCodigo.add(instruccion);
            } else {
                // Reemplazar variables por sus valores constantes
                String optimizada = instruccion;
                for (Map.Entry<String, String> entry : constants.entrySet()) {
                    optimizada = optimizada.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
                }
                nuevoCodigo.add(optimizada);
            }
        }
        
        codigoOptimizado = nuevoCodigo;
    }
    
    private void deadCodeElimination() {
        System.out.println("Aplicando dead code elimination...");
        Set<String> usedVars = new HashSet<>();
        List<String> nuevoCodigo = new ArrayList<>();
        
        // Primer pase: identificar variables usadas
        for (String instruccion : codigoOptimizado) {
            // Buscar variables en el lado derecho de asignaciones
            if (instruccion.contains("=")) {
                String[] parts = instruccion.split("=");
                if (parts.length == 2) {
                    String rightSide = parts[1];
                    // Extraer variables (simplificado)
                    String[] tokens = rightSide.split("[\\s+\\-*/()\\[\\],]+");
                    for (String token : tokens) {
                        if (token.matches("[a-zA-Z]\\w*")) {
                            usedVars.add(token);
                        }
                    }
                }
            }
        }
        
        // Segundo pase: eliminar asignaciones a variables no usadas
        for (String instruccion : codigoOptimizado) {
            if (instruccion.matches("t\\d+\\s*=.*")) {
                String[] parts = instruccion.split("=");
                String var = parts[0].trim();
                if (usedVars.contains(var) || !var.startsWith("t")) {
                    nuevoCodigo.add(instruccion);
                }
            } else {
                nuevoCodigo.add(instruccion);
            }
        }
        
        codigoOptimizado = nuevoCodigo;
    }
    
    private void algebraicSimplification() {
        System.out.println("Aplicando simplificación algebraica...");
        List<String> nuevoCodigo = new ArrayList<>();
        
        for (String instruccion : codigoOptimizado) {
            String optimizada = simplifyAlgebraic(instruccion);
            nuevoCodigo.add(optimizada);
        }
        
        codigoOptimizado = nuevoCodigo;
    }
    
    private String simplifyAlgebraic(String instruccion) {
        // x = y + 0 -> x = y
        instruccion = instruccion.replaceAll("(\\w+)\\s*\\+\\s*0", "$1");
        instruccion = instruccion.replaceAll("0\\s*\\+\\s*(\\w+)", "$1");
        
        // x = y * 1 -> x = y
        instruccion = instruccion.replaceAll("(\\w+)\\s*\\*\\s*1", "$1");
        instruccion = instruccion.replaceAll("1\\s*\\*\\s*(\\w+)", "$1");
        
        // x = y * 0 -> x = 0
        instruccion = instruccion.replaceAll("\\w+\\s*\\*\\s*0", "0");
        instruccion = instruccion.replaceAll("0\\s*\\*\\s*\\w+", "0");
        
        // x = y - 0 -> x = y
        instruccion = instruccion.replaceAll("(\\w+)\\s*-\\s*0", "$1");
        
        return instruccion;
    }
    
    public void imprimirComparacion() {
        System.out.println("\n=== COMPARACIÓN CÓDIGO ORIGINAL VS OPTIMIZADO ===");
        System.out.println("\nCÓDIGO ORIGINAL:");
        for (int i = 0; i < codigoOriginal.size(); i++) {
            System.out.printf("%3d: %s\n", i, codigoOriginal.get(i));
        }
        
        System.out.println("\nCÓDIGO OPTIMIZADO:");
        for (int i = 0; i < codigoOptimizado.size(); i++) {
            System.out.printf("%3d: %s\n", i, codigoOptimizado.get(i));
        }
    }
}