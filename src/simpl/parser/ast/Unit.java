package simpl.parser.ast;

import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeResult;

public class Unit extends Expr {

    public String toString() {
        return "()";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) {
        return TypeResult.of(Type.UNIT);
    }

    @Override
    public Value eval(State s) {
        return Value.UNIT;
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return this;
    }
}
