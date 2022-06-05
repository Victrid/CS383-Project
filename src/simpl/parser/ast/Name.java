package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Name extends Expr {

    public final Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        Type t = E.get(x);
        if (t == null) {
            throw new TypeMismatchError();
        }
        return TypeResult.of(t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = s.Environment.get(x);
        if (v == null) {
            throw new RuntimeError("Undefined variable: " + x);
        } else if (v instanceof RecValue) {
            return new Rec(((RecValue) v).x, ((RecValue) v).e).eval(State.of(((RecValue) v).E, s.Memory, s.MemoryIndex));
        }
        return v;
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        if (x.equals(t)) {
            return s;
        } else {
            return this;
        }
    }
}
