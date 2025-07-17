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
        Set<Integer> reach = new HashSet<>();
        Map<String,Integer> labels = new HashMap<>();
        // 1) Mapea etiquetas
        for (int i = 0; i < codigo.size(); i++) {
            String l = codigo.get(i).trim();
            if (l.endsWith(":")) {
                labels.put(l.substring(0, l.length()-1), i);
            }
        }
        // 2) Comienza DFS desde la primera instrucción
        dfsReach(0, reach, labels);
        // 3) Reconstruye solo con las que quedaron reachables
        List<String> kept = new ArrayList<>();
        for (int i = 0; i < codigo.size(); i++) {
            if (reach.contains(i)) kept.add(codigo.get(i));
        }
        codigo = kept;
    }

    private void dfsReach(int ip, Set<Integer> reach, Map<String,Integer> labels) {
        if (ip < 0 || ip >= codigo.size() || reach.contains(ip)) return;
        reach.add(ip);
        String instr = codigo.get(ip).trim();

        if (instr.startsWith("goto ")) {
            String lbl = instr.substring(5).trim();
            if (labels.containsKey(lbl)) {
                dfsReach(labels.get(lbl), reach, labels);
            }
            // no caemos al siguiente
            return;
        }

        if (instr.startsWith("if ") && instr.contains(" goto ")) {
            String lbl = instr.split(" goto ")[1].trim();
            // ambas rutas: salto y caída
            if (labels.containsKey(lbl)) {
                dfsReach(labels.get(lbl), reach, labels);
            }
            dfsReach(ip + 1, reach, labels);
            return;
        }

        if (instr.startsWith("return")) {
            // no caemos al siguiente
            return;
        }

        // caso normal: siguiente instrucción
        dfsReach(ip + 1, reach, labels);
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
            // 1) Solo procesamos asignaciones a temporales tN
            if (linea.contains(" = ") && !linea.matches(".*call.*")) {
                String[] partes = linea.split("=", 2);
                String dest = partes[0].trim();                   // lado izquierdo
                String rhs  = partes[1].replace(";", "").trim();  // lado derecho

                // a) Si es “tN = LITERAL_NUMÉRICO”, registramos la constante
                if (dest.matches("t\\d+") && rhs.matches("-?\\d+")) {
                    constantValues.put(dest, rhs);
                    codigoOpt.add(dest + " = " + rhs);
                    continue;
                }
                // b) Si es “tM = tN” y tN estaba en constantValues, propagamos
                if (dest.matches("t\\d+") && constantValues.containsKey(rhs)) {
                    String val = constantValues.get(rhs);
                    constantValues.put(dest, val);
                    codigoOpt.add(dest + " = " + val);
                    continue;
                }
            }

            // 2) Para TODAS las demás líneas, NUNCA cambiamos el dest (que puede ser variable de usuario).
            //    Solo, opcionalmente, podemos propagar dentro de temporales en RHS:
            String tmp = linea;
            // Si la línea escribe en un temporal, permitir propagar en su RHS
            if (tmp.matches("^t\\d+\\s*=.*")) {
                for (Map.Entry<String, String> e : constantValues.entrySet()) {
                    tmp = tmp.replaceAll("\\b" + e.getKey() + "\\b", e.getValue());
                }
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

        // 1) Semilla: considera vivas todas las variables “no temporales”
        //    (por ejemplo, las globals y locales declaradas)
        Pattern varPat = Pattern.compile("\\b([a-zA-Z_]\\w*)\\b");
        for (String linea : codigo) {
            if (linea.startsWith("if ") || linea.contains("call ")) {
                Matcher m = varPat.matcher(linea);
                while (m.find()) {
                    String v = m.group(1);
                    if (!v.matches("t\\d+")) vivas.add(v);
                }
            }
            // añade también las variables de retorno
            if (linea.startsWith("return ")) {
                Matcher m = varPat.matcher(linea);
                while (m.find()) {
                    String v = m.group(1);
                    if (!v.matches("t\\d+")) vivas.add(v);
                }
            }
        }

        // 2) Escaneo hacia atrás
        for (int i = codigo.size() - 1; i >= 0; i--) {
            String linea = codigo.get(i);
            if (linea.contains(" = ")) {
                String[] partes = linea.split("=", 2);
                String dest = partes[0].trim();
                String expr = partes[1].replaceAll("//.*","").trim();

                // Si dest no es un temporal, siempre lo mantenemos
                if (!dest.matches("t\\d+")) {
                    result.add(0, linea);
                    // Y marcamos vivos todos los operadores de la expresión
                    Matcher m = varPat.matcher(expr);
                    while (m.find()) {
                        String v = m.group(1);
                        if (!v.matches("\\d+")) vivas.add(v);
                    }
                    continue;
                }

                // Si dest es temp, solo lo mantenemos si sigue vivo
                if (vivas.contains(dest)) {
                    result.add(0, linea);
                    Matcher m = varPat.matcher(expr);
                    while (m.find()) {
                        String v = m.group(1);
                        if (!v.matches("\\d+")) vivas.add(v);
                    }
                    vivas.add(dest);
                }
            } else {
                // no es asignación: la mantenemos y marcamos sus variables vivas
                result.add(0, linea);
                Matcher m = varPat.matcher(linea);
                while (m.find()) {
                    String v = m.group(1);
                    if (!v.matches("\\d+")) vivas.add(v);
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