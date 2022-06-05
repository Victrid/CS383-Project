package simpl.interpreter.lib;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeResult;

public class tl extends FunValue {

    public tl() {
        super(Env.empty, Symbol.symbol("tl_op"), new Expr() {
            @Override
            public String toString() {
                return "tl";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                var value = s.Environment.get(Symbol.symbol("tl_op"));
                if (value instanceof ConsValue) {
                    return ((ConsValue) value).v2;
                } else {
                    throw new RuntimeError("tl: not a cons");
                }
            }

            @Override
            public Expr substitute(Symbol t, Expr s) {
                return this;
            }
        });
    }
}
