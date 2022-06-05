package simpl.typing;

public final class RefType extends Type {

    public final Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type S) throws TypeError {
        // Types and Programming Languages: P327 Fig. 22-2
        // T = PairType<t>
        // let {S = T} = C in
        // else if (S = T) then unify(none)
        //   unify its two components
        if (S instanceof RefType) {
            return ((RefType) S).t.unify(this.t);
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
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new RefType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " ref";
    }
}
