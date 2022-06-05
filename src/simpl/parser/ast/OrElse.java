package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
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
            return new BoolValue(true);
        } else {
            return r.eval(s);
        }
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return new OrElse(l.substitute(t, s), r.substitute(t, s));
    }
}
