package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PairValue) {
            return v1.equals(((PairValue) other).v1) && v2.equals(((PairValue) other).v2);
        } else {
            return false;
        }
    }
}
