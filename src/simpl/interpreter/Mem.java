package simpl.interpreter;

import java.io.Serial;
import java.util.HashMap;
import java.util.HashSet;

public class Mem extends HashMap<Integer, Value> {

    @Serial
    private static final long serialVersionUID = -1155291135560730618L;
    private static final int MaxMemory =
            Integer.parseInt(System.getenv("MAX_MEMORY") == null ? "-1" : System.getenv("MAX_MEMORY"));
    private static final boolean UseGC = (System.getenv("USE_GC") != null && Boolean.parseBoolean(System.getenv("USE_GC")));

    private final HashSet<Integer> free_list = new HashSet<>();

    public Value get (int i) throws RuntimeError {
        if (containsKey(i) && !free_list.contains(i)) return super.get(i);
        else throw new RuntimeError("Mem: error");
    }

    public int getSlot (State s) throws RuntimeError {
        if (UseGC) {
            if (!free_list.isEmpty()) {
                // get the first free slot and remove it from the free list
                int i = free_list.iterator().next();
                free_list.remove(i);
                return i;
            }
            // Free list is empty, utilize mark-and-sweep to free up slots
            // mark
            HashSet<Integer> markset = new HashSet<>();
            Env              env     = s.Environment;
            while (env != null) {
                if (env.Value instanceof RefValue) markset.add(((RefValue) env.Value).p);
                env = env.Environment;
            }
            // sweep
            int first_empty = -1;
            for (int i = 0; i < s.Memory.size(); i++) {
                if (!markset.contains(i)) {
                    if (first_empty == -1) first_empty = i;
                    else free_list.add(i);
                }
            }
            // if found a free slot, return it
            if (first_empty != -1) return first_empty;
        }
        // Sweeped all slots but all in use, so allocate a new one
        if (MaxMemory >= 0 && size() > MaxMemory) throw new RuntimeError("Out of memory");
        else return size();

    }


}
