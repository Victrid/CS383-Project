package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Loop extends Expr {

    public final Expr e1;
    public final Expr e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lr = e1.typecheck(E);
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = e2.typecheck(E1);
        Substitution s = rr.s;
        s = s.compose(s.apply(lr.t).unify(Type.BOOL));
        s = s.compose(s.apply(rr.t).unify(Type.UNIT));
        return TypeResult.of(s, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue v = (BoolValue) e1.eval(s);
        while (v.b) {
            e2.eval(s);
            v = (BoolValue) e1.eval(s);
        }
        return Value.UNIT;
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Loop(e1.substitute(t, s), e2.substitute(t, s));
    }
}
