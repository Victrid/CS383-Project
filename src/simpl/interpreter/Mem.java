package simpl.interpreter;

import java.io.Serial;
import java.util.HashMap;
import java.util.HashSet;

public class Mem extends HashMap<Integer, Value> {

    @Serial
    private static final long serialVersionUID = -1155291135560730618L;
    private static final int MaxMemory =
            Integer.parseInt(System.getenv("MAX_MEMORY") == null ? "-1" : System.getenv("MAX_MEMORY"));
    private static final boolean UseGC =
            System.getenv("DISABLE_GC") != null && !Boolean.parseBoolean(System.getenv("DISABLE_GC"));

    public Value get (int i) throws RuntimeError {
        if (containsKey(i)) {
            return super.get(i);
        }
        else {
            throw new RuntimeError("Mem: error");
        }
    }

    public int getSlot (State s) throws RuntimeError {
        if (UseGC) {
            HashSet<Integer> markset = new HashSet<Integer>();
            var              env     = s.Environment;
            while (env != null) {
                if (env.Value instanceof RefValue) {
                    markset.add(((RefValue) env.Value).p);
                }
                env = env.Environment;
            }
            for (int i = 0; i < s.Memory.size(); i++) {
                if (!markset.contains(i)) return i;
            }
        }

        // Sweeped all slots, so allocate a new one
        if (MaxMemory >= 0 && size() > MaxMemory) {
            throw new RuntimeError("Out of memory");
        }
        else {
            return size();
        }
    }


}
