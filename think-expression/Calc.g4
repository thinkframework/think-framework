grammar Calc;
prog:	(expr NEWLINE)* ;
expr:	expr ('*'|'/') expr # opmd
    |	expr ('+'|'-') expr # opsd
    |	INT                 # value
    |	'(' expr ')'        # op
    ;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
