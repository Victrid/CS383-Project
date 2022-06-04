package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class FunValue extends Value {

    public final Env env;
    public final Symbol symbol;
    public final Expr expr;

    public FunValue(Env env, Symbol Symbol, Expr Expression) {
        this.env = env;
        this.symbol = Symbol;
        this.expr = Expression;
    }

    public String toString() {
        return "fun";
    }

    @Override
    public boolean equals(Object other) {
        // ???
        return false;
    }
}
