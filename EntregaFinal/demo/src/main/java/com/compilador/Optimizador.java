package com.compilador;

import java.util.*;
import java.util.regex.*;

/**
 * Optimizador de código de tres direcciones
 */
public class Optimizador {
    private List<String> codigo;

    public Optimizador(List<String> codigo) {
        this.codigo = new ArrayList<>(codigo);
    }

    /**
     * Realiza todas las optimizaciones disponibles
     */
    public List<String> optimizar() {
        eliminarCodigoMuerto();
        propagarConstantes();
        simplificarExpresiones();
        eliminarSentenciasRedundantes();
        eliminarAsignacionesInutiles();

        return codigo;
    }

    /**
     * Elimina código muerto (código inalcanzable)
     */
    public void eliminarCodigoMuerto() {
        Set<Integer> lineasAlcanzables = new HashSet<>();
        Map<String, Integer> etiquetas = new HashMap<>();
        // Identificar etiquetas
        for (int i = 0; i < codigo.size(); i++) {
            String linea = codigo.get(i);
            if (linea.endsWith(":")) {
                String etiqueta = linea.substring(0, linea.length() - 1);
                etiquetas.put(etiqueta, i);
            }
        }
        // Marcar desde inicio y desde func_main
        marcarLineasAlcanzables(0, lineasAlcanzables, etiquetas);
        if (etiquetas.containsKey("func_main")) {
            marcarLineasAlcanzables(etiquetas.get("func_main"), lineasAlcanzables, etiquetas);
        }
        // Filtrar
        List<String> codigoOpt = new ArrayList<>();
        for (int i = 0; i < codigo.size(); i++) {
            if (lineasAlcanzables.contains(i)) {
                codigoOpt.add(codigo.get(i));
            }
        }
        codigo = codigoOpt;
    }

    private void marcarLineasAlcanzables(int linea, Set<Integer> visitadas, Map<String, Integer> etiquetas) {
        if (linea >= codigo.size() || visitadas.contains(linea)) return;
        visitadas.add(linea);
        String instr = codigo.get(linea).trim();
        if (instr.startsWith("goto ")) {
            String et = instr.substring(5).trim();
            if (etiquetas.containsKey(et)) {
                marcarLineasAlcanzables(etiquetas.get(et), visitadas, etiquetas);
            }
            return;
        }
        if (instr.startsWith("if ") && instr.contains(" goto ")) {
            String[] partes = instr.split(" goto ");
            String et = partes[1].trim();
            if (etiquetas.containsKey(et)) {
                marcarLineasAlcanzables(etiquetas.get(et), visitadas, etiquetas);
            }
        }
        if (instr.equals("return") || instr.startsWith("return ")) {
            return;
        }
        marcarLineasAlcanzables(linea + 1, visitadas, etiquetas);
    }

    /**
     * Propaga constantes cuando es posible
     */
    public void propagarConstantes() {
        Map<String, String> constantValues = new HashMap<>();
        List<String> codigoOpt = new ArrayList<>();
        for (String linea : codigo) {
            if (linea.contains(" = ") && !linea.matches(".*call.*")) {
                String[] partes = linea.split("=", 2);
                String dest = partes[0].trim();
                String val = partes[1].replace(";", "").trim();
                if (val.matches("-?\\d+")) {
                    constantValues.put(dest, val);
                    codigoOpt.add(dest + " = " + val);
                    continue;
                }
                if (constantValues.containsKey(val)) {
                    constantValues.put(dest, constantValues.get(val));
                    codigoOpt.add(dest + " = " + constantValues.get(val));
                    continue;
                }
            }
            String tmp = linea;
            for (Map.Entry<String, String> e : constantValues.entrySet()) {
                tmp = tmp.replaceAll("\\b" + e.getKey() + "\\b", e.getValue());
            }
            codigoOpt.add(tmp);
        }
        codigo = codigoOpt;
    }

    /**
     * Simplifica expresiones constantes y neutras
     */
    public void simplificarExpresiones() {
        List<String> codigoOpt = new ArrayList<>();
        for (String linea : codigo) {
            String newline = linea;
            if (newline.contains(" = ")) {
                String[] partes = newline.split("=", 2);
                String dest = partes[0].trim();
                String expr = partes[1].replace(";", "").trim();
                // Constantes aritméticas
                if (expr.matches("-?\\d+ [+\\-*/%] -?\\d+")) {
                    String[] op = expr.split(" ");
                    int a = Integer.parseInt(op[0]);
                    String oper = op[1];
                    int b = Integer.parseInt(op[2]);
                    int res = 0;
                    boolean ok = true;
                    switch (oper) {
                        case "+": res = a + b; break;
                        case "-": res = a - b; break;
                        case "*": res = a * b; break;
                        case "/": if (b != 0) res = a / b; else ok = false; break;
                        case "%": if (b != 0) res = a % b; else ok = false; break;
                    }
                    if (ok) {
                        newline = dest + " = " + res;
                        codigoOpt.add(newline);
                        continue;
                    }
                }
                // Operaciones neutras
                newline = newline.replaceAll("\\b(\\w+) \\+ 0\\b", "$1");
                newline = newline.replaceAll("\\b0 \\+ (\\w+)\\b", "$1");
                newline = newline.replaceAll("\\b(\\w+) \\* 1\\b", "$1");
                newline = newline.replaceAll("\\b1 \\* (\\w+)\\b", "$1");
            }
            codigoOpt.add(newline);
        }
        codigo = codigoOpt;
    }

    /**
     * Elimina sentencias redundantes (a = a)
     */
    public void eliminarSentenciasRedundantes() {
        List<String> codigoOpt = new ArrayList<>();
        for (String linea : codigo) {
            if (linea.contains(" = ")) {
                String[] partes = linea.split("=", 2);
                String dest = partes[0].trim();
                String val  = partes[1].replace(";", "").trim();
                if (dest.equals(val)) continue;
            }
            codigoOpt.add(linea);
        }
        codigo = codigoOpt;
    }

    /**
     * Elimina asignaciones a variables nunca usadas (dead-store)
     */
    public void eliminarAsignacionesInutiles() {
        List<String> result = new ArrayList<>();
        Set<String> vivas = new HashSet<>();
        vivas.add("resultado");  // considera resultado como entrada/salida
        Pattern varPat = Pattern.compile("\\b[a-zA-Z_]\\w*\\b");
        for (int i = codigo.size() - 1; i >= 0; i--) {
            String linea = codigo.get(i);
            if (linea.contains(" = ")) {
                String[] partes = linea.split("=", 2);
                String dest = partes[0].trim();
                String expr = partes[1].replaceAll("//.*", "").replace(";", "").trim();
                if (!vivas.contains(dest)) continue;
                result.add(0, linea);
                Matcher m = varPat.matcher(expr);
                while (m.find()) {
                    String var = m.group();
                    if (!var.matches("\\d+")) vivas.add(var);
                }
                vivas.add(dest);
            } else {
                result.add(0, linea);
                Matcher m = varPat.matcher(linea);
                while (m.find()) {
                    String var = m.group();
                    if (!var.matches("\\d+")) vivas.add(var);
                }
            }
        }
        codigo = result;
    }


    /**
     * Obtiene el código optimizado
     */
    public List<String> getCodigoOptimizado() {
        return codigo;
    }

    /**
     * Imprime el código optimizado
     */
    public void imprimirCodigoOptimizado() {
        System.out.println("\n=== CÓDIGO OPTIMIZADO ===");
        for (int i = 0; i < codigo.size(); i++) {
            System.out.println(i + ": " + codigo.get(i));
        }
    }
}