package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult rl = l.typecheck(E);
        TypeEnv E1 = rl.s.compose(E);
        TypeResult rr = r.typecheck(E1);
        Substitution s = rr.s.compose(rl.s);

        return TypeResult.of(s, new PairType(rl.t, rr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return new PairValue(l.eval(s), r.eval(s));
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Pair(l.substitute(t, s), r.substitute(t, s));
    }
}
