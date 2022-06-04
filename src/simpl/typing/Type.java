package simpl.typing;

public abstract class Type {

    public static final Type INT = new IntType();
    public static final Type BOOL = new BoolType();
    public static final Type UNIT = new UnitType();

    /**
     * This method returns true if this type is comparable.
     * <p>
     * Only int, bool, t list, t ref, and Pair a a can be comparable.
     *
     * @return true if this type is comparable
     */
    public abstract boolean isEqualityType();

    /**
     * The substitution of a type variable to a type.
     *
     * @param a a type variable
     * @param t a type to be replaced
     * @return this_type[a/t]
     */
    public abstract Type replace(TypeVar a, Type t);

    /**
     * If this type contains a type variable.
     *
     * @param tv a type variable
     * @return if this type contains the type variable tv
     */
    public abstract boolean contains(TypeVar tv);

    /**
     * The unification algorithm by Hindley-Milner.
     *
     * @param t a type
     * @return Unify({ Type_t = this_type })
     * @throws TypeError
     */
    public abstract Substitution unify(Type t) throws TypeError;
}
