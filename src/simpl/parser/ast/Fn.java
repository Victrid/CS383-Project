package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeVar a = new TypeVar(true);
        TypeResult r = e.typecheck(TypeEnv.of(E, x, a));
        Substitution s = r.s;
        ArrowType arrow = new ArrowType(s.apply(a), s.apply(r.t));
        return TypeResult.of(s, s.apply(arrow));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return new FunValue(s.Environment, x, e);
    }
}
