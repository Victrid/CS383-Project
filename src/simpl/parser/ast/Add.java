package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

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
        return new IntValue(((IntValue) l.eval(s)).n + ((IntValue) r.eval(s)).n);
    }
}
