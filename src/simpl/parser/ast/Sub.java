package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Sub extends ArithExpr {

    public Sub(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " - " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return new IntValue(((IntValue) l.eval(s)).n - ((IntValue) r.eval(s)).n);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Sub(l.substitute(t, s), r.substitute(t, s));
    }
}
