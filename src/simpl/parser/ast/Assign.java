package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // Get the substitution from the left and right expressions
        TypeResult lr = l.typecheck(E);
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = r.typecheck(E1);

        Substitution s = rr.s.compose(lr.s);

        RefType ref = new RefType(rr.t);

        s = s.compose(s.apply(lr.t).unify(s.apply(ref)));
        return TypeResult.of(s, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RefValue ref = (RefValue) l.eval(s);
        Value v = r.eval(s);
        s.Memory.put(ref.p, v);
        return Value.UNIT;
    }
}
