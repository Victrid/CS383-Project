package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Add extends ArithExpr {

    public Add(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " + " + r + ")";
    }

    /**
     * @param s the state
     * @return the result of adding the two operands
     */
    @Override
    public Value eval(State s) throws RuntimeError {
        IntValue lv = (IntValue) l.eval(s);
        IntValue rv = (IntValue) r.eval(s);
        return new IntValue(lv.n + rv.n);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Add(l.substitute(t, s), r.substitute(t, s));
    }
}
