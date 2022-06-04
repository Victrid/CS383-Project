package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class succ extends FunValue {

    public succ() {
        super(Env.empty, Symbol.symbol("succ_op"), new Expr() {
            @Override
            public String toString() {
                return "succ";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                var value = s.Environment.get(Symbol.symbol("succ_op"));
                if (value instanceof IntValue) {
                    return new IntValue(((IntValue) value).n + 1);
                } else {
                    throw new RuntimeError("succ: error");
                }
            }
        });
    }
}
