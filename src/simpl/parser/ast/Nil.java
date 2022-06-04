package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO: equalityType
        TypeVar t = new TypeVar(true);
        ListType lt = new ListType(t);
        return TypeResult.of(lt);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        return Value.NIL;
    }
}
