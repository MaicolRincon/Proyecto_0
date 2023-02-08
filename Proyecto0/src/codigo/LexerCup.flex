package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
    	return new Symbol(type,yyline,yycolumn,value);
    	}
    
    private Symbol symbol(int type){
    	return new Symbol(type,yyline,yycolumn);
    	}
%}
%%
int {return new Symbol(sym.Int,yychar,yyline,yytext());}
M {return new Symbol(sym.M,yychar,yyline,yytext());}
R {return new Symbol(sym.R,yychar,yyline,yytext());}
C {return new Symbol(sym.C,yychar,yyline,yytext());}
B {return new Symbol(sym.B,yychar,yyline,yytext());}
c {return new Symbol(sym.c,yychar,yyline,yytext());}
b {return new Symbol(sym.b,yychar,yyline,yytext());}
P {return new Symbol(sym.P,yychar,yyline,yytext());}
J(yytext) {return new Symbol(sym.J,yychar,yyline,yytext());}
G(yytext,yytext) {return new Symbol(sym.G,yychar,yyline,yytext());}
vars {return new Symbol(sym.vars,yychar,yyline,yytext());}
procs {return new Symbol(sym.procs,yychar,yyline,yytext());}
assingTo {return new Symbol(sym.assingTo,yychar,yyline,yytext());}
goto {return new Symbol(sym.Int,goto,yyline,yytext());}
move {return new Symbol(sym.Int,move,yyline,yytext());}
turn {return new Symbol(sym.Int,turn,yyline,yytext());}
face {return new Symbol(sym.Int,face,yyline,yytext());}
put {return new Symbol(sym.Int,put,yyline,yytext());}
pick {return new Symbol(sym.Int,pick,yyline,yytext());}
moveToThe {return new Symbol(sym.moveToThe,yychar,yyline,yytext());}
moveInDir {return new Symbol(sym.moveInDir,yychar,yyline,yytext());}
jumpToThe {return new Symbol(sym.jumpToThe,yychar,yyline,yytext());}
jumpInDir {return new Symbol(sym.jumpInDir,yychar,yyline,yytext());}
nop {return new Symbol(sym.nop,yychar,yyline,yytext());}
if {return new Symbol(sym.if,yychar,yyline,yytext());}
else {return new Symbol(sym.else,yychar,yyline,yytext());}
repeat {return new Symbol(sym.repeat,yychar,yyline,yytext());}
while {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return new Symbol(sym.Igual,yychar,yyline,yytext());}
"+" {return new Symbol(sym.Suma,yychar,yyline,yytext());}
"-" {return new Symbol(sym.Resta,yychar,yyline,yytext());}
"*" {return new Symbol(sym.Division,yychar,yyline,yytext());}
"/" {return new Symbol(sym.Multiplicacion,yychar,yyline,yytext());}
/* Parentesis de apertura */
( "(" ) {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {return new Symbol(sym.Llave_a, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {return new Symbol(sym.Llave_c, yychar, yyline, yytext());}

/* Corchete de apertura */
( "[" ) {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}

/* Corchete de cierre */
( "]" ) {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "robot_r" ) {return new Symbol(sym.robot_r, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {return new Symbol(sym.P_coma, yychar, yyline, yytext());}

/* Identificador */
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Numero */
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}