package simpl.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileInputStream;

class ParserTest {
    @DisplayName("Testing Interpreter")
    @ParameterizedTest
    @ValueSource(strings = {
            "doc/examples/plus.spl",
            "doc/examples/factorial.spl",
            "doc/examples/gcd1.spl",
            "doc/examples/gcd2.spl",
            "doc/examples/max.spl",
            "doc/examples/sum.spl",
            "doc/examples/true.spl",
            "doc/examples/pcf.sum.spl",
            "doc/examples/pcf.even.spl",
            "doc/examples/pcf.twice.spl",
            "doc/examples/pcf.minus.spl",
            "doc/examples/pcf.factorial.spl",
            "doc/examples/pcf.fibonacci.spl",
            "doc/examples/pcf.lists.spl"
    })
    void parse(String filename) {
        try {
            Parser parser = new Parser(new FileInputStream(filename));
            java_cup.runtime.Symbol parseTree = parser.parse();
            System.out.println(filename);
            System.out.println(parseTree.value);
        } catch (Exception e) {
            System.out.println("syntax error");
        }
    }
}