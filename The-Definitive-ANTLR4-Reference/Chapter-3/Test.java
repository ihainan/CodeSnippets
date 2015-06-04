import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test{
	public static void main(String[] args) throws Exception{
		// 读取标准数据，创建 CharStream
		ANTLRInputStream input = new ANTLRInputStream(System.in);

		// 根据 CharStream 创建一个词法解析器
		ArrayInitLexer lexer = new ArrayInitLexer(input);

		// 根据词法解析器创建单词(Token)的缓冲区
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// 根据缓冲区创建语法解析器
		ArrayInitParser parser = new ArrayInitParser(tokens);

		// 解析，输出解析结果
		ParseTree tree = parser.init();
		System.out.println(tree.toStringTree(parser));
	}
}
