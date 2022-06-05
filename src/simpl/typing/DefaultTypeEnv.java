package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private final TypeEnv E;

    public DefaultTypeEnv() {
        TypeEnv E1 = TypeEnv.empty;
        // Add the built-in functions to the environment
        {
            TypeVar a = new TypeVar(true);
            TypeVar b = new TypeVar(true);
            E1 = of(E1, Symbol.symbol("fst"), new ArrowType(new PairType(a, b), a));
        }
        {
            TypeVar a = new TypeVar(true);
            TypeVar b = new TypeVar(true);
            E1 = of(E1, Symbol.symbol("snd"), new ArrowType(new PairType(a, b), b));
        }
        {
            TypeVar a = new TypeVar(true);
            E1 = of(E1, Symbol.symbol("hd"), new ArrowType(new ListType(a), a));
        }
        {
            TypeVar a = new TypeVar(true);
            E1 = of(E1, Symbol.symbol("tl"), new ArrowType(new ListType(a), new ListType(a)));
        }
        {
            E1 = of(E1, Symbol.symbol("iszero"), new ArrowType(Type.INT, Type.BOOL));
        }
        {
            E1 = of(E1, Symbol.symbol("succ"), new ArrowType(Type.INT, Type.INT));
        }
        {
            E1 = of(E1, Symbol.symbol("pred"), new ArrowType(Type.INT, Type.INT));
        }
        E = E1;
    }

    public String toString() {
        return "" + E;
    }

    @Override
    public Type get(Symbol x) throws TypeError {
        return E.get(x);
    }
}
