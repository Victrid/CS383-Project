package simpl.typing;

final class IntType extends Type {

    IntType () {
    }

    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type S) throws TypeError {
        // Types and Programming Languages: P327 Fig. 22-2
        // S = t, T = intType
        // let {S = T} = C in
        // else if (S = T) then unify(none)
        if (S instanceof IntType)
            return Substitution.IDENTITY;
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
        // IntType is one of bottom types, so it cannot contain any type variable
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // IntType is one of bottom types, so T[a/t] = IntType
        return Type.INT;
    }

    public String toString() {
        return "int";
    }
}
