package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public abstract class Expr {

    public abstract TypeResult typecheck(TypeEnv E) throws TypeError;

    public abstract Expr substitute(Symbol t, Expr s);

    public abstract Value eval(State s) throws RuntimeError;
}
