package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.*;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // Get the substitution from the left and right expressions
        TypeResult lr = l.typecheck(E);
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = r.typecheck(E1);
        Substitution s = rr.s.compose(lr.s);
        // Left expression must be a function
        // t1 = t2 -> a
        // TODO: What's the expected equality type of a?
        TypeVar a = new TypeVar(true);
        ArrowType func = new ArrowType(s.apply(rr.t), a);
        s = s.compose(s.apply(lr.t).unify(func));
        return TypeResult.of(s, s.apply(a));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        FunValue f = (FunValue) l.eval(s);
        Value v = r.eval(s);
        // Evaluate the result
        // Add f.symbol -> v to the environment
        var new_state = State.of(new Env(f.env, f.symbol, v), s.Memory, s.MemoryIndex);
        return f.expr.eval(new_state);
    }
}
