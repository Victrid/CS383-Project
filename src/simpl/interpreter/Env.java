package simpl.interpreter;

import org.jetbrains.annotations.Contract;
import simpl.parser.Symbol;

/**
 * <b>The Environment.</b>
 * <br/>
 * The environment is a mapping from a symbol to a value, forming a linked-list like structure.
 * <br/>
 * Example:
 * <pre>
 * Env(Env(Env.empty, A -> 1), B -> 2)
 * </pre>
 */
public class Env {

    public static final Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
    };
    final Env Environment;
    private final Symbol Symbol;
    final Value Value;

    private Env() {
        Environment = null;
        Symbol = null;
        Value = null;
    }

    public Env(Env Environment, Symbol Symbol, Value Value) {
        this.Environment = Environment;
        this.Symbol = Symbol;
        this.Value = Value;
    }

    public Value get(Symbol y) {
        if (Symbol == null) return null;
        if (y.toString().equals(Symbol.toString())) {
            return Value;
        } else {
            if (Environment == null) return null;
            else return Environment.get(y);
        }
    }

    public Env clone() {
        return new Env(Environment == null ? null : Environment.clone(), Symbol, Value);
    }
}
