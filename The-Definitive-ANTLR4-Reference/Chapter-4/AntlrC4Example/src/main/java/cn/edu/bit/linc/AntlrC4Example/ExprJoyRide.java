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
        String inputFile = null;
        if(args.length > 0){
            inputFile = args[0];
        }

        InputStream is = System.in;
        if(inputFile != null)
            is = new FileInputStream(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog();

        System.out.println(tree.toStringTree(parser));
    }
}
