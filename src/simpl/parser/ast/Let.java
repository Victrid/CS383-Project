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

    public Symbol x;
    public Expr e1, e2;

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
        TypeResult lr = e1.typecheck(E);
        TypeEnv E1 = lr.s.compose(TypeEnv.of(E, x, lr.t));
        TypeResult rr = e2.typecheck(E1);
        Substitution s = rr.s;
        return TypeResult.of(s, s.apply(rr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v = e1.eval(s);
        var new_state = State.of(new Env(s.Environment, x, v), s.Memory, s.MemoryIndex);
        return e2.eval(new_state);
    }
}
