package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Cond extends Expr {

    public final Expr e1;
    public final Expr e2;
    public final Expr e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult r1 = e1.typecheck(E);
        TypeEnv E1 = r1.s.compose(E);
        TypeResult r2 = e2.typecheck(E1);
        TypeEnv E2 = r2.s.compose(E1);
        TypeResult r3 = e3.typecheck(E2);
        Substitution s = r3.s.compose(r2.s).compose(r1.s);
        // t1 = bool
        s = s.compose(s.apply(r1.t).unify(Type.BOOL));
        // t2 = t3
        s = s.compose(s.apply(r2.t).unify(s.apply(r3.t)));
        return TypeResult.of(s, s.apply(r2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue v1 = (BoolValue) e1.eval(s);
        if (v1.b) {
            return e2.eval(s);
        } else {
            return e3.eval(s);
        }
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Cond(e1.substitute(t, s), e2.substitute(t, s), e3.substitute(t, s));
    }
}
