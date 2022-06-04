package simpl.parser.ast;

import simpl.typing.*;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult lr = l.typecheck(E);
        if (!lr.t.isEqualityType()){
            throw new TypeMismatchError();
        }
        TypeEnv E1 = lr.s.compose(E);
        TypeResult rr = r.typecheck(E1);
        if (!rr.t.isEqualityType()){
            throw new TypeMismatchError();
        }
        Substitution s = rr.s.compose(lr.s);

        s = s.compose(s.apply(lr.t).unify(s.apply(rr.t)));
        return TypeResult.of(s, Type.BOOL);
    }
}
