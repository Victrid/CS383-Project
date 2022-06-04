package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class FunValue extends Value {

    public final Env Environment;
    public final Symbol Symbol;
    public final Expr Expression;

    public FunValue(Env Environment, Symbol Symbol, Expr Expression) {
        this.Environment = Environment;
        this.Symbol = Symbol;
        this.Expression = Expression;
    }

    public String toString() {
        return "fun";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        return false;
    }
}
