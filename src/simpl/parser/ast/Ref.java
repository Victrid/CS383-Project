package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult r = e.typecheck(E);
        Substitution s = r.s;
        return TypeResult.of(s, new RefType(s.apply(r.t)));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        int size = s.MemoryIndex.get() + 1;
        s.MemoryIndex.set(size);
        Value v = e.eval(s);
        s.Memory.put(size, v);
        return new RefValue(size);
    }
}
