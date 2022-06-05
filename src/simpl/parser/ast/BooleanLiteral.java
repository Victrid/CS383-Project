package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class BooleanLiteral extends Expr {

    public final boolean b;

    public BooleanLiteral(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) {
        return TypeResult.of(Type.BOOL);
    }

    @Override
    public Value eval(State s) {
        return new BoolValue(b);
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return this;
    }
}
