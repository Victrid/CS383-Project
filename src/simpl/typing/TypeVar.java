package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;
    private final Symbol name;
    private final boolean equalityType;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type S) throws TypeCircularityError {
        if (S.contains(this)) {
            if (S instanceof TypeVar) {
                return Substitution.IDENTITY;
            } else {
                // S contains this while using this to unify with S
                throw new TypeCircularityError();
            }
        } else return Substitution.of(this, S);
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TypeVar only contains itself
        return name.equals(tv.name);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        if (a.name.equals(name)) {
            return t;
        } else {
            return this;
        }
    }
}
