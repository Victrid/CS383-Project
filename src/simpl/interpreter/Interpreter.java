package simpl.interpreter;

import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

import java.io.FileInputStream;
import java.io.InputStream;

public class Interpreter {

    private static void interpret (String filename) {
        Interpreter i = new Interpreter();
        //System.out.println(filename);
        i.run(filename);
    }

    public static void main (String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar SimPL.jar <filename>");
            System.exit(1);
        }
        interpret(args[0]);
    }

    public void run (String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser                  parser    = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr                    program   = (Expr) parseTree.value;
            program.typecheck(new DefaultTypeEnv());
            System.out.println(program.eval(new InitialState()));
        } catch (SyntaxError e) {
            System.out.println("syntax error");
        } catch (TypeError e) {
            System.out.println("type error");
        } catch (RuntimeError e) {
            System.out.println("runtime error");
        } catch (Exception e) {
            //e.printStackTrace(System.err);
            System.out.println("syntax error");
        }
    }
}
