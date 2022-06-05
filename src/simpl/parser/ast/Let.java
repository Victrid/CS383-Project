package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Let extends Expr {

    public final Symbol x;
    public final Expr e1;
    public final Expr e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        e1.typecheck(E);
        TypeResult rr = e2.substitute(x, e1).typecheck(E);
        Substitution s = rr.s;
        return TypeResult.of(s, s.apply(rr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e1.eval(s);
        var new_state = State.of(new Env(s.Environment, x, v), s.Memory, s.MemoryIndex);
        return e2.eval(new_state);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        // Let polymorphism
        if (x.equals(t)) {
            return new Let(x, e1.substitute(t, s), e2);
        } else {
            return new Let(x, e1.substitute(t, s), e2.substitute(t, s));
        }
    }
}
