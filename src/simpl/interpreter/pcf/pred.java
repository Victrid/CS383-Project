package simpl.interpreter.pcf;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class pred extends FunValue {

    public pred() {
        super(Env.empty, Symbol.symbol("pred_op"), new Expr() {
            @Override
            public String toString() {
                return "pred";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                var value = s.Environment.get(Symbol.symbol("pred_op"));
                if (value instanceof IntValue) {
                    return new IntValue(((IntValue) value).n - 1);
                } else {
                    throw new RuntimeError("pred: error");
                }
            }
        });
    }
}
