grammar MiLenguaje;

// Regla inicial
programa : s EOF ;


s : ID EQ e DC      
  | IF PA e PC s          
  ;

t : N
  | ID
  | PA e PC
  ;

e : e SUM t
  | t         
  ;


// Definición de los tokens
PA : '(' ;
PC : ')' ;
EQ   : '=' ;
DC : ';' ;
SUM : '+' ;
N: 'NUM';
IF : 'if' ;
ID: [a-zA-Z_][a-zA-Z_0-9]* ;



// Ignorar espacios, tabulaciones y saltos de línea
WS : [ \t\r\n]+ -> skip ;