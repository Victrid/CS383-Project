package simpl.parser.ast;

import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) {
        // TODO: equalityType
        TypeVar t = new TypeVar(true);
        ListType lt = new ListType(t);
        return TypeResult.of(lt);
    }

    @Override
    public Value eval(State s) {
        return Value.NIL;
    }

    @Override
    public Expr substitute(Symbol t, Expr s) {
        return this;
    }
}
