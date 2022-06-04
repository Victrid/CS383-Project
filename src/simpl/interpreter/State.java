package simpl.interpreter;

public class State {

    public final Env Environment;
    public final Mem Memory;
    public final Int MemoryIndex;

    protected State(Env Environment, Mem Memory, Int MemoryIndex) {
        this.Environment = Environment;
        this.Memory = Memory;
        this.MemoryIndex = MemoryIndex;
    }

    public static State of(Env E, Mem M, Int p) {
        return new State(E, M, p);
    }
}
