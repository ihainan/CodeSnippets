/**
 * Created by ihainan on 6/26/15.
 */
package cn.edu.bit.linc.AntlrC4Example;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class ExprJoyRide{
    public static void main(String[] args) throws Exception{
        // 确定输入流
        String inputFile = null;
        if(args.length > 0){
            inputFile = args[0];
        }

        InputStream is = System.in;
        if(inputFile != null)
            is = new FileInputStream(inputFile);

        // 解析
        ANTLRInputStream input = new ANTLRInputStream(is);
        LabeledExprLexer lexer = new LabeledExprLexer(input);                     // 词法解析
        CommonTokenStream tokens = new CommonTokenStream(lexer);    // 获取单词
        LabeledExprParser parser = new LabeledExprParser(tokens);                 // 语法解析
        ParseTree tree = parser.prog();                             // 调用 Prog 规则

        System.out.println(tree.toStringTree(parser));

        // 运算
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }
}
