package simpl.parser.ast;

import simpl.typing.*;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lr = l.typecheck(E);
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = r.typecheck(E1);
        Substitution s = rr.s.compose(lr.s);

        s = s.compose(s.apply(lr.t).unify(Type.INT));
        s = s.compose(s.apply(rr.t).unify(Type.INT));
        return TypeResult.of(s, Type.BOOL);
    }
}
