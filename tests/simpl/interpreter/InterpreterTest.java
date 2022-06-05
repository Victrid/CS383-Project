package simpl.interpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpl.parser.Parser;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

class InterpreterTest {
    private static Stream<Arguments> get_example_testcase () {
        return Stream.of(Arguments.of("doc/examples/plus.spl", "3"), Arguments.of("doc/examples/factorial.spl", "24"),
                         Arguments.of("doc/examples/gcd1.spl", "1029"), Arguments.of("doc/examples/gcd2.spl", "1029"),
                         Arguments.of("doc/examples/max.spl", "2"), Arguments.of("doc/examples/sum.spl", "6"),
                         Arguments.of("doc/examples/map.spl", "fun"), Arguments.of("doc/examples/pcf.sum.spl", "fun"),
                         Arguments.of("doc/examples/pcf.even.spl", "fun"),
                         Arguments.of("doc/examples/pcf.minus.spl", "46"),
                         Arguments.of("doc/examples/pcf.factorial.spl", "720"),
                         Arguments.of("doc/examples/pcf.fibonacci.spl", "6765"));
    }

    private static Stream<Arguments> get_additional_testcase () {
        return Stream.of(Arguments.of("doc/tests/pcf.headtail.spl", "fun"),
                         Arguments.of("doc/tests/let-polymorphism.spl", "0"),
                         Arguments.of("doc/tests/sum.arithexpr.spl", "30"),
                         Arguments.of("doc/tests/andAlso.boolExpr.spl", "true"),
                         Arguments.of("doc/tests/sequence.spl", "5"),
                         Arguments.of("doc/tests/pcf.twice.fixed.spl", "8"),
                         Arguments.of("doc/tests/true.fixed.spl", "true"),
                         Arguments.of("doc/tests/largememory.spl", "unit"));
    }

    void test_file (String filename) throws Exception {
        InputStream             inp       = new FileInputStream(filename);
        Parser                  parser    = new Parser(inp);
        java_cup.runtime.Symbol parseTree = parser.parse();
        Expr                    program   = (Expr) parseTree.value;
        System.out.println(program.typecheck(new DefaultTypeEnv()).t);
    }

    @DisplayName("TypeCheck: Given Example test")
    @ParameterizedTest
    @MethodSource("get_example_testcase")
    void test_files (String filename) {
        try {
            test_file(filename);
        } catch (Exception e) {
            fail("" + e);
        }
    }

    @DisplayName("TypeCheck: Implemented tests")
    @ParameterizedTest
    @MethodSource("get_additional_testcase")
    void test_files_2 (String filename) {
        try {
            test_file(filename);
        } catch (Exception e) {
            fail("" + e);
        }
    }

    @DisplayName("Evaluate: Given Example test")
    @ParameterizedTest
    @MethodSource("get_example_testcase")
    void test_eval (String filename, String expected) {
        try {
            InputStream             inp       = new FileInputStream(filename);
            Parser                  parser    = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr                    program   = (Expr) parseTree.value;
            System.out.println(program.typecheck(new DefaultTypeEnv()).t);
            var value = program.eval(new InitialState());
            if (!("" + value).equals(expected)) {
                System.out.println("Expected: " + expected);
                System.out.println("Actual:   " + value);
                fail("Expected: " + expected + " Actual: " + value);
            }
            else {
                System.out.println("Matched expectation: " + expected);
            }
        } catch (Exception e) {
            fail("" + e);
        }
    }

    @DisplayName("Evaluate: Implemented Tests")
    @ParameterizedTest
    @MethodSource("get_additional_testcase")
    void test_eval_2 (String filename, String expected) {
        try {
            InputStream             inp       = new FileInputStream(filename);
            Parser                  parser    = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr                    program   = (Expr) parseTree.value;
            System.out.println(program.typecheck(new DefaultTypeEnv()).t);
            var value = program.eval(new InitialState());
            if (!("" + value).equals(expected)) {
                System.out.println("Expected: " + expected);
                System.out.println("Actual:   " + value);
                fail("Expected: " + expected + " Actual: " + value);
            }
            else {
                System.out.println("Matched expectation: " + expected);
            }
        } catch (Exception e) {
            fail("" + e);
        }
    }
}