package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult rl = l.typecheck(E);
        TypeEnv E1 = rl.s.compose(E);
        TypeResult rr = r.typecheck(E1);
        Substitution s = rr.s.compose(rl.s);

        s = s.compose(s.apply(rl.t).unify(Type.UNIT));
        return TypeResult.of(s, s.apply(rr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        l.eval(s);
        return r.eval(s);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Seq(l.substitute(t, s), r.substitute(t, s));
    }
}
