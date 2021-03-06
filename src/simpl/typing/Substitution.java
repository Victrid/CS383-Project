package simpl.typing;

import simpl.parser.Symbol;

public abstract class Substitution {

    public static final Substitution IDENTITY = new Identity();

    public static Substitution of(TypeVar a, Type t) {
        return new Replace(a, t);
    }

    public abstract Type apply(Type t);

    public Substitution compose(Substitution inner) {
        if (this == IDENTITY)
            return inner;
        if (inner == IDENTITY)
            return this;
        return new Compose(this, inner);
    }

    public TypeEnv compose(final TypeEnv E) {
        return new TypeEnv() {
            public Type get(Symbol x) throws TypeError {
                return apply(E.get(x));
            }

            public String toString() {
                return "" + E;
            }
        };
    }

    private static final class Identity extends Substitution {
        public Type apply(Type t) {
            return t;
        }

        public String toString() {
            return "[]";
        }
    }

    private static final class Replace extends Substitution {
        private final TypeVar a;
        private final Type t;

        public Replace(TypeVar a, Type t) {
            this.a = a;
            this.t = t;
        }

        public String toString() {
            return "[" + a + " |-> " + t + "]";
        }

        public Type apply(Type b) {
            return b.replace(a, t);
        }
    }

    private static final class Compose extends Substitution {
        private final Substitution f;
        private final Substitution g;

        public Compose(Substitution f, Substitution g) {
            this.f = f;
            this.g = g;
        }

        public String toString() {
            return "" + f + " o " + g ;
        }

        public Type apply(Type t) {
            return f.apply(g.apply(t));
        }
    }
}
