package algorithms.union_find;

import java.util.HashMap;
import java.util.Map;

public class UnionFind_Map {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, String> rootMap = new HashMap<>();
    private Map<String, Integer> sizeMap = new HashMap<>();

    public String find(String index) {
        if (rootMap.get(index) == null) {
            rootMap.put(index, index);
            sizeMap.put(index, 1);
        }
        if (rootMap.get(index).equals(index)) {
            return index;
        }
        String temp = find(rootMap.get(index));
        rootMap.put(index, temp);
        return temp;
    }

    public void union_map(String index1, String index2) {
        String root1 = find(index1);
        String root2 = find(index2);
        if (!root1.equals(root2)) {
            // Smaller root point to larger
            if (sizeMap.get(root1) < sizeMap.get(root2)) {
                sizeMap.put(root2, sizeMap.get(root1) + sizeMap.get(root2));
                rootMap.put(root1, root2);
            } else {
                sizeMap.put(root1, sizeMap.get(root1) + sizeMap.get(root2));
                rootMap.put(root2, root1);
            }
        }
    }
}
