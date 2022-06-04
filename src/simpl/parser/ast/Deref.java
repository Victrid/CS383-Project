package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult r = e.typecheck(E);
        Substitution s = r.s;
        TypeVar a = new TypeVar(true);
        RefType ref = new RefType(a);
        s = s.compose(s.apply(r.t).unify(s.apply(ref)));
        return TypeResult.of(s, s.apply(a));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RefValue ref = (RefValue) e.eval(s);
        return s.Memory.get(ref.p);
    }
}
