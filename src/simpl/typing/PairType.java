package simpl.typing;

public final class PairType extends Type {

    public Type t1, t2;

    public PairType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        return t1.isEqualityType() && t2.isEqualityType();
    }

    @Override
    public Substitution unify(Type S) throws TypeError {
        // Types and Programming Languages: P327 Fig. 22-2
        // T = PairType<t>
        // let {S = T} = C in
        // else if (S = T) then unify(none)
        //   unify its two components
        if (S instanceof PairType) {
            // Unify t1
            var s1 = ((PairType) S).t1.unify(this.t1);
            // apply s1 to S.t2 and this.t2
            var applied_S_t2 = s1.apply(((PairType) S).t2);
            var applied_T_t2 = s1.apply(this.t2);
            // Unify t2
            var s2 = applied_S_t2.unify(applied_T_t2);
            // return s1 o s2
            return s1.compose(s2);
        }
        // else if S = X and X not in FV(T)
        if (S instanceof TypeVar)
            return Substitution.of((TypeVar) S, this);
        // else if T = X and X not in FV(S): impossible
        // else if S = S1 -> S2 and T = T1 -> T2: impossible
        // else fail
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new PairType(t1.replace(a, t), t2.replace(a, t));
    }

    public String toString() {
        return "(" + t1 + " * " + t2 + ")";
    }
}
