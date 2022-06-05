package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeResult;

public class IntegerLiteral extends Expr {

    public final int n;

    public IntegerLiteral(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) {
        return TypeResult.of(Type.INT);
    }

    @Override
    public Value eval(State s) {
        return new IntValue(n);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return this;
    }
}
