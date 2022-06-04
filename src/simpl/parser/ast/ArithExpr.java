package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // Get the substitution from the left and right expressions
        TypeResult lr = l.typecheck(E);
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = r.typecheck(E1);
        Substitution s = rr.s.compose(lr.s);
        // Check if expressions are IntType
        s = s.compose(s.apply(lr.t).unify(Type.INT));
        s = s.compose(s.apply(rr.t).unify(Type.INT));
        return TypeResult.of(s, Type.INT);
    }
}
