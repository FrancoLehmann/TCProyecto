grammar MiLenguaje;

// =======================
// Reglas del Parser
// =======================

programa
    : (sentencia)* EOF
    ;

sentencia
    : declaracionFuncion
    | declaracionVariable
    | asignacion
    ;

// Reglas para sentencias que pueden aparecer dentro de un bloque (funciones, bucles, etc.)
sentenciaInterior
    : sentencia
    | retorno
    | sentenciaBreak
    | sentenciaContinue
    | sentenciaIf
    | sentenciaWhile
    | sentenciaFor
    ;

sentenciaBreak
    : BREAK PYC
    ;

sentenciaContinue
    : CONTINUE PYC
    ;

sentenciaIf
    : IF PA expresion PC bloque (ELSE bloque)?
    ;

bloque
    : LA (sentenciaInterior)* LC
    ;

declaracionFuncion
    : tipo ID PA parametros? PC bloque
    ;

sentenciaWhile
    : WHILE PA expresion PC bloque
    ;

sentenciaFor
    : FOR PA declaracionVariable? PYC expresion? PYC asignacion? PC bloque
    ;

parametros
    : parametro (COMA parametro)*
    ;

parametro
    : tipo ID
    ;

// Declaración de variable con asignación opcional
declaracionVariable
    : tipo ID (IGUAL expresion)? PYC                    // Variable simple: int x = 5;
    | tipo ID CA INTEGER CC (IGUAL expresion)? PYC      // Array: int arr[10] = expr;
    ;

asignacion
    : ID IGUAL expresion PYC                    // Asignación simple: x = expr;
    | ID CA expresion CC IGUAL expresion PYC       // Asignación array: arr[i] = expr;
    | ID SUM SUM PYC                            // Incremento: x++;
    | ID RES RES PYC                            // Decremento: x--;
    ;

retorno
    : RETURN expresion? PYC
    ;

tipo
    : INT
    | CHAR
    | DOUBLE
    | VOID
    | STRING
    | BOOL
    ;

// -----------------------
// Expresiones con precedencia
// -----------------------
// OR tiene la precedencia más baja, luego AND, luego comparaciones, luego suma/resta,
// luego multiplicación/división/mod, luego unario (NOT), luego paréntesis, literales, variables y llamada a función.
expresion
    : expresion OR expresion                  #expOr
    | expresion AND expresion                 #expAnd
    | expresion comparadorBinario expresion   #expComparacion
    | expresion SUM expresion                 #expBinaria
    | expresion RES expresion                 #expBinaria
    | expresion MUL expresion                 #expBinaria
    | expresion DIV expresion                 #expBinaria
    | expresion MOD expresion                 #expBinaria
    | NOT expresion                           #expNegacion
    | PA expresion PC                         #expParentizada
    | ID PA argumentos? PC                    #expFuncion
    | ID                                      #expVariable
    | ID CA expresion CC                      #expArray
    | INTEGER                                 #expEntero
    | DECIMAL                                 #expDecimal
    | CHARACTER                               #expCaracter
    | STRING_LITERAL                          #expCadena
    | TRUE                                    #expBooleano
    | FALSE                                   #expBooleano
    ;

// Lista de argumentos en llamadas a función
argumentos
    : expresion (COMA expresion)*
    ;

// Comparadores relacionales
comparadorBinario
    : MAYOR
    | MAYOR_IGUAL
    | MENOR
    | MENOR_IGUAL
    | EQL
    | DISTINTO
    ;

// =======================
// Reglas del Lexer (Tokens)
// =======================

// Palabras clave (antes de ID)
FOR       : 'for' ;
WHILE     : 'while' ;
IF        : 'if' ;
ELSE      : 'else' ;
INT       : 'int' ;
CHAR      : 'char' ;
DOUBLE    : 'double' ;
VOID      : 'void' ;
STRING    : 'String' ;
BOOL      : 'bool' ;
TRUE      : 'true' ;
FALSE     : 'false' ;
RETURN    : 'return' ;
BREAK     : 'break' ;
CONTINUE  : 'continue' ;

// Operadores y delimitadores
PA        : '(' ;
PC        : ')' ;
CA        : '[' ;
CC        : ']' ;
LA        : '{' ;
LC        : '}' ;
PYC       : ';' ;
COMA      : ',' ;
IGUAL     : '=' ;
MAYOR     : '>' ;
MAYOR_IGUAL: '>=' ;
MENOR     : '<' ;
MENOR_IGUAL: '<=' ;
EQL       : '==' ;
DISTINTO  : '!=' ;
SUM       : '+' ;
RES       : '-' ;
MUL       : '*' ;
DIV       : '/' ;
MOD       : '%' ;
OR        : '||' ;
AND       : '&&' ;
NOT       : '!'   ;

// Literales
INTEGER   : DIGITO+ ;
DECIMAL   : DIGITO+ '.' DIGITO+ ;
CHARACTER : '\'' (~['\r\n] | '\\' .) '\'' ;
STRING_LITERAL : '"' (~["\\\r\n] | '\\' .)* '"' ;

// Identificador (después de palabras clave)
ID : (LETRA | '_') (LETRA | DIGITO | '_')* ;

// Comentarios y espacios
COMENTARIO_LINEA : '//' ~[\r\n]* -> skip ;
COMENTARIO_BLOQUE : '/*' .*? '*/' -> skip ;
WS : [ \r\n\t]+ -> skip ;

// Captura de caracteres no reconocidos (útil en etapas iniciales; en producción es posible eliminarlo)
OTRO : . ;

// Fragmentos
fragment LETRA : [A-Za-z] ;
fragment DIGITO : [0-9] ;
