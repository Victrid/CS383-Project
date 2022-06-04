package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private final TypeEnv E;

    public DefaultTypeEnv() {
        E = new TypeEnv() {
            @Override
            public Type get(Symbol x) throws TypeError {
                throw new TypeError("undefined type variable " + x);
            }
        };
    }

    @Override
    public Type get(Symbol x) throws TypeError {
        return E.get(x);
    }
}
