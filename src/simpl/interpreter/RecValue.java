package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class RecValue extends Value {

    public final Env E;
    public final Symbol x;
    public final Expr e;

    public RecValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof RecValue) {
            return x.equals(((RecValue) other).x) && e.equals(((RecValue) other).e);
        } else {
            return false;
        }
    }
}
