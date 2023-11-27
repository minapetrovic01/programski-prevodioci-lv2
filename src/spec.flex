// import sekcija

%%

// sekcija opcija i deklaracija
%class MPLexer
%function next_token
%line
%column
%debug //loguje se svaka akcija, ako necemo onda standalone
%type Yytoken

%eofval{
return new Yytoken( sym.EOF, null, yyline, yycolumn);
%eofval}

%{
//dodatni clanovi generisane klase - dodaje se ekao java kod nepromenjeno
KWTable kwTable = new KWTable();
Yytoken getKW()
{
	return new Yytoken( kwTable.find( yytext() ),
	yytext(), yyline, yycolumn );
}
%}

//stanja
%xstate KOMENTAR
//pocetno stanje komentar
//makroi se pozivaju izmedju viticastih zagrada
//sluzi za rregularne izraze koje cemo vise puta da koristimo
slovo = [a-zA-Z]
cifra = [0-9]

%%

// pravila
\|\* { yybegin( KOMENTAR ); }
<KOMENTAR>~"*|" { yybegin( YYINITIAL ); }

[\t\n\r ] { ; }

//operatori
\< { return new Yytoken( sym.LESS, yytext(), yyline, yycolumn ); }
\<= { return new Yytoken( sym.LESSEQ, yytext(), yyline, yycolumn ); }
\> { return new Yytoken( sym.GREATER, yytext(), yyline, yycolumn ); }
\>= { return new Yytoken( sym.GREATEREQ, yytext(), yyline, yycolumn ); }
\== { return new Yytoken( sym.EQ, yytext(), yyline, yycolumn ); }
\<> { return new Yytoken( sym.NOTEQ, yytext(), yyline, yycolumn ); }

//separatori
\( { return new Yytoken( sym.LEFTPAR, yytext(), yyline, yycolumn ); }
\) { return new Yytoken( sym.RIGHTPAR, yytext(), yyline, yycolumn ); }
; { return new Yytoken( sym.SEMICOLON, yytext(), yyline, yycolumn ); }
: { return new Yytoken( sym.COLON, yytext(), yyline, yycolumn ); }
:= { return new Yytoken( sym.ASSIGN, yytext(), yyline, yycolumn ); }
\. { return new Yytoken( sym.DOT, yytext(), yyline, yycolumn ); }
=> { return new Yytoken( sym.STATES, yytext(), yyline, yycolumn ); }

(true|false) { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn ); }

//kljucne reci
{slovo}+ { return getKW(); }

//identifikatori
({slovo}|\$)({slovo}|{cifra}|\$)* { return new Yytoken(sym.ID, yytext(),yyline, yycolumn ); }

//konstante

0[0-7]+ { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn ); }
0x[0-9a-fA-F]+ { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn ); }
{cifra}+ { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn ); }
{cifra}?.{cifra}+(E[+|-]?{cifra}+)? { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn ); }
'[^]' { return new Yytoken( sym.CONST, yytext(), yyline, yycolumn ); }
//bilo koji karakter

//obrada gresaka
. { if (yytext() != null && yytext().length() > 0) System.out.println( "ERROR: " + yytext() ); }
