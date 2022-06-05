package simpl.typing;

final class BoolType extends Type {

    BoolType () {
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
        if (S instanceof BoolType) return Substitution.IDENTITY;
        // else if S = X and X not in FV(T)
        if (S instanceof TypeVar) return Substitution.of((TypeVar) S, this);
        // else if T = X and X not in FV(S): impossible
        // else if S = S1 -> S2 and T = T1 -> T2: impossible
        // else fail
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // BoolType is one of bottom types, so it cannot contain any type variable
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // BoolType is one of bottom types, so T[a/t] = BoolType
        return Type.BOOL;
    }

    public String toString() {
        return "bool";
    }
}
