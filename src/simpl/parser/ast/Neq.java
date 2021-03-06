package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;

public class Neq extends EqExpr {

    public Neq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " <> " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        IntValue v1 = (IntValue) l.eval(s);
        IntValue v2 = (IntValue) r.eval(s);
        return new BoolValue(v1.n != v2.n);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Neq(l.substitute(t, s), r.substitute(t, s));
    }
}
