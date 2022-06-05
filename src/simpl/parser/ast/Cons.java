package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lr = l.typecheck(E);
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = r.typecheck(E1);

        Substitution s = rr.s.compose(lr.s);
        // TODO: determine equalityType
        TypeVar a = new TypeVar(true);
        ListType list = new ListType(a);
        s = s.compose(s.apply(lr.t).unify(a));
        s = s.compose(s.apply(rr.t).unify(list));
        return TypeResult.of(s, list);
    }


    @Override
    public Value eval(State s) throws RuntimeError {
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new ConsValue(v1, v2);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new Cons(l.substitute(t, s), r.substitute(t, s));
    }
}
