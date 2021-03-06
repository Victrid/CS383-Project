package simpl.typing;

public class TypeResult {

    public final Substitution s;
    public final Type t;

    private TypeResult(Substitution s, Type t) {
        this.s = s;
        this.t = t;
    }

    public static TypeResult of(Type t) {
        return TypeResult.of(Substitution.IDENTITY, t);
    }

    public static TypeResult of(Substitution s, Type t) {
        return new TypeResult(s, t);
    }

    public String toString() {
        return "TR[" + t + ";" + s + "]";
    }
}
