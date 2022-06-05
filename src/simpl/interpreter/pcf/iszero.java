package simpl.interpreter.pcf;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeResult;

public class iszero extends FunValue {

    public iszero() {
        super(Env.empty, Symbol.symbol("iszero_op"), new Expr() {
            @Override
            public String toString() {
                return "iszero";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                var value = s.Environment.get(Symbol.symbol("iszero_op"));
                if (value instanceof IntValue) {
                    return new BoolValue(((IntValue) value).n == 0);
                } else {
                    throw new RuntimeError("iszero: error");
                }
            }

            @Override
            public Expr substitute(Symbol t, Expr s) {
                return this;
            }
        });
    }
}
