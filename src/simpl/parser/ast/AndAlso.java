package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // Get the substitution from the left and right expressions
        TypeResult lr = l.typecheck(E);
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = r.typecheck(E1);
        Substitution s = rr.s.compose(lr.s);
        // Apply Type.BOOL to it
        s = s.compose(s.apply(lr.t).unify(Type.BOOL));
        s = s.compose(s.apply(rr.t).unify(Type.BOOL));
        return TypeResult.of(s, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        if (((BoolValue) l.eval(s)).b) {
            return r.eval(s);
        } else {
            return new BoolValue(false);
        }
    }
}
