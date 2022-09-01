package algorithms.union_find;

import java.util.HashMap;
import java.util.Map;

public class UnionFindMap {

    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> height = new HashMap<>();

    public String find(String i) {
        // Initialization
        if (parent.get(i) == null) {
            parent.put(i, i);
            height.put(i, 1);
        }
        if (parent.get(i).equals(i)) {
            return i;
        }
        // Path compression: all the nodes on the path will point to the root.
        return parent.compute(i, (k, v) -> find(parent.get(i)));
    }

    public void union(String m, String n) {
        String mm = find(m);
        String nn = find(n);
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
