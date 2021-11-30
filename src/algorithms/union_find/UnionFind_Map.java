package algorithms.union_find;

import java.util.HashMap;
import java.util.Map;

public class UnionFind_Map {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, String> root
            = new HashMap<>();
    private Map<String, Integer> weight = new HashMap<>();

    public String find(String index) {
        // Initialization
        if (root.get(index) == null) {
            root.put(index, index);
            weight.put(index, 1);
        }
        if (root.get(index).equals(index)) {
            return index;
        }
        // Path compression
        return root.compute(index, (k, v)-> find(root.get(index)));
    }

    public void union_map(String index1, String index2) {
        String root1 = find(index1);
        String root2 = find(index2);
        if (!root1.equals(root2)) {
            // Smaller root point to larger
            if (weight.get(root1) < weight.get(root2)) {
                weight.put(root2, weight.get(root1) + weight.get(root2));
                root.put(root1, root2);
            } else {
                weight.put(root1, weight.get(root1) + weight.get(root2));
                root.put(root2, root1);
            }
        }
    }
}
