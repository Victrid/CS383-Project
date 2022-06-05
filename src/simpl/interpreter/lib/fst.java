package simpl.interpreter.lib;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeResult;

public class fst extends FunValue {

    public fst() {
        super(Env.empty, Symbol.symbol("fst_op"), new Expr() {
            @Override
            public String toString() {
                return "fst";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                var value = s.Environment.get(Symbol.symbol("fst_op"));
                if (value instanceof PairValue) {
                    return ((PairValue) value).v1;
                } else {
                    throw new RuntimeError("fst: not a pair");
                }
            }

            @Override
            public Expr substitute(Symbol t, Expr s) {
                return this;
            }
        });
    }
}
