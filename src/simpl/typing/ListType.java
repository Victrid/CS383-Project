package simpl.typing;

public final class ListType extends Type {

    public final Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type S) throws TypeError {
        // Types and Programming Languages: P327 Fig. 22-2
        // T = ListType<t>
        // let {S = T} = C in
        // else if (S = T) then unify(none)
        if (S instanceof ListType)
            return ((ListType) S).t.unify(this.t);
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
        return new ListType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " list";
    }
}
