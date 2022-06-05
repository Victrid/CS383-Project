package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Eq extends EqExpr {

    public Eq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " = " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v1 = r.eval(s);
        Value v2 = l.eval(s);
        return new BoolValue(v1.equals(v2));
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Eq(l.substitute(t, s), r.substitute(t, s));
    }
}
