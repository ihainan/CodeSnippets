grammar ArrayInit;

init:	'{' value (',' value)* '}';		// 对整型数组的定义

// 对值的定义，递归定义
value:	init
		| INT
		;

// 对整型的定义
INT:	[0-9]+;

WS:		[ \t\r\n]+ -> skip;
