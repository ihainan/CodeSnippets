grammar Hello;		// 规则名
r  : 'hello' ID;	// 规则 r，hello 字符串后面跟随标示符
ID : [a-z]+;		// ID 定义，小写字母构成的单词
WS : [ \t\r\n]+	-> skip;	// 巫师空格、制表符和换行
