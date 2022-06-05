package simpl.interpreter.lib;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeResult;

public class hd extends FunValue {

    public hd() {
        super(Env.empty, Symbol.symbol("hd_op"), new Expr() {
            @Override
            public String toString() {
                return "hd";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                var value = s.Environment.get(Symbol.symbol("hd_op"));
                if (value instanceof ConsValue) {
                    return ((ConsValue) value).v1;
                } else {
                    throw new RuntimeError("hd: not a cons");
                }
            }

            @Override
            public Expr substitute(Symbol t, Expr s) {
                return this;
            }
        });
    }
}
