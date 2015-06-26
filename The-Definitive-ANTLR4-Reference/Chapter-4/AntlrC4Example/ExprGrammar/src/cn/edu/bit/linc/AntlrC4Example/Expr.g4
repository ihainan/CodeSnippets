// 语法规则名
grammar Expr;

// 初始规则
prog:	stat+;

// 定义语句
stat:	expr	NEWLINE		// 表达式
	|	ID '=' expr NEWLINE	// 赋值
	|	NEWLINE				// 换行
	;

// 定义表达式
expr:	expr ('*' | '/') expr
	|	expr ('+' | '-') expr
	|	INT
	|	ID
	|	'(' expr ')'
	;

// 定义变量名
ID:		[a-zA-Z]+;

// 定义整数
INT:	[0-9]+;

// 定义换行
NEWLINE:	'\r'? '\n';

// 无视空格
WS:		[ \t]+ -> skip;
