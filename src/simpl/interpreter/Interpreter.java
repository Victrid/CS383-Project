package simpl.interpreter;

import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

import java.io.FileInputStream;
import java.io.InputStream;

public class Interpreter {

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename);
    }

    public static void main(String[] args) {
        var strings = new String[]{
//                "doc/examples/plus.spl",
//                "doc/examples/factorial.spl",
//                "doc/examples/gcd1.spl",
//                "doc/examples/gcd2.spl",
//                "doc/examples/max.spl",
//                "doc/examples/sum.spl",
//                "doc/examples/hdtl-tst.spl"
                "doc/examples/map.spl",
//                "doc/examples/true.spl",
//                "doc/examples/pcf.sum.spl",
//                "doc/examples/pcf.even.spl",
//                "doc/examples/pcf.twice.spl",
//                "doc/examples/pcf.minus.spl",
//                "doc/examples/pcf.factorial.spl",
//                "doc/examples/pcf.fibonacci.spl",
//                "doc/examples/pcf.lists.spl"
        };
        for (var s : strings) {
            interpret(s);
        }
    }

    public void run_file(String filename) throws Exception {
        InputStream inp = new FileInputStream(filename);
        Parser parser = new Parser(inp);
        java_cup.runtime.Symbol parseTree = parser.parse();
        Expr program = (Expr) parseTree.value;
        System.out.println(program.typecheck(new DefaultTypeEnv()).t);
    }

    public void run(String filename) {
        try {
            InputStream inp = new FileInputStream(filename);
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;
            System.out.println(program.typecheck(new DefaultTypeEnv()).t);
            System.out.println(program.eval(new InitialState()));
        } catch (SyntaxError e) {
            System.out.println("syntax error");
        } catch (TypeError e) {
            e.printStackTrace(System.err);
            System.out.println("type error");
        } catch (RuntimeError e) {
            System.out.println("runtime error");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
