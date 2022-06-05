package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeResult;

public class snd extends FunValue {

    public snd() {
        super(Env.empty, Symbol.symbol("snd_op"), new Expr() {
            @Override
            public String toString() {
                return "snd";
            }

            @Override
            public TypeResult typecheck(TypeEnv E) {
                return null;
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                var value = s.Environment.get(Symbol.symbol("snd_op"));
                if (value instanceof PairValue) {
                    return ((PairValue) value).v2;
                } else {
                    throw new RuntimeError("snd: not a pair");
                }
            }

            @Override
            public Expr substitute(Symbol t, Expr s) {
                return this;
            }
        });
    }
}
