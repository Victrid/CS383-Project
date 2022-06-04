package simpl.interpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {
    void test_file(String filename) throws Exception {
        Interpreter i = new Interpreter();
        i.run_file(filename);
    }

    @DisplayName("Testing Interpreter")
    @ParameterizedTest
    @ValueSource(strings = {
            "doc/examples/plus.spl",
            "doc/examples/factorial.spl",
            "doc/examples/gcd1.spl",
            "doc/examples/gcd2.spl",
            "doc/examples/max.spl",
            "doc/examples/sum.spl",
            "doc/examples/map.spl",
            // "doc/examples/true.spl", // Seems having problem
            "doc/examples/pcf.sum.spl",
            "doc/examples/pcf.even.spl",
            // "doc/examples/pcf.twice.spl", // Seems having problem
            "doc/examples/pcf.minus.spl",
            "doc/examples/pcf.factorial.spl",
            "doc/examples/pcf.fibonacci.spl",
            // "doc/examples/pcf.lists.spl" // Seems having problem
    })
    void test_files(String filename) {
        try{
            test_file(filename);
        } catch (Exception e) {
            fail("" + e);
        }
    }
}