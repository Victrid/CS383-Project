package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public final Symbol x;
    public final Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeVar tv = new TypeVar(true);
        TypeResult tr = e.typecheck(TypeEnv.of(E, x, tv));
        Substitution s = tr.s;
        s = s.compose(s.apply(tr.t).unify(tv));
        return TypeResult.of(s, s.apply(tr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RecValue rv = new RecValue(s.Environment, x, e);
        return e.eval(State.of(new Env(s.Environment, x, rv), s.Memory, s.MemoryIndex));
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        if (x.equals(t)) {
            return s;
        } else {
            return new Rec(x, e.substitute(t, s));
        }
    }
}
