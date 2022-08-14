package algorithms.union_find;

import java.util.HashMap;
import java.util.Map;

public class UnionFindMap {

    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> height = new HashMap<>();

    public String findRoot(String i) {
        // Initialization
        if (parent.get(i) == null) {
            parent.put(i, i);
            height.put(i, 1);
        }
        if (parent.get(i).equals(i)) {
            return i;
        }
        // Path compression: all the nodes on the path will point to the root.
        return parent.compute(i, (k, v) -> findRoot(parent.get(i)));
    }

    public void connect(String m, String n) {
        String mm = findRoot(m);
        String nn = findRoot(n);
        if (!mm.equals(nn)) {
            // Smaller root point to larger
            if (height.get(mm) < height.get(nn)) {
                parent.put(mm, nn);
            } else {
                parent.put(nn, mm);
                if (height.get(mm) == height.get(nn)) {
                    height.put(mm, height.get(mm) + 1);
                }
            }
        }
    }
}
