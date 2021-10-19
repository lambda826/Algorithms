/**
 *  @author Yunxiang He
 *  @date 02/08/2018
 */

package algorithms.union_find;

import java.util.HashMap;
import java.util.Map;

public class UnionFind_WeightedPathCompression {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] root;
    private int[] size;

    public UnionFind_WeightedPathCompression(int N) {
        root = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
            size[i] = 1;
        }
    }

    public int find(int index) {
        if (root[index] == index) {
            return index;
        }
        return root[index] = find(root[index]);
    }

    public void union(int index1, int index2) {
        int root1 = find(index1);
        int root2 = find(index2);
        if (root1 != root2) {
            // Smaller root point to larger
            if (size[root1] < size[root2]) {
                root[root1] = root2;
                size[root2] += size[root1];
            } else {
                root[root2] = root1;
                size[root1] += size[root2];
            }
        }
    }

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
