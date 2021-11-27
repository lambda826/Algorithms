package algorithms.union_find;

public class UnionFind_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] root;
    private int[] weight;

    public UnionFind_Array(int N) {
        // Initialization
        root = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; ++i) {
            root[i] = i;
            weight[i] = 1;
        }
    }

    public int find(int index) {
        if (root[index] == index) {
            return index;
        }
        // Path compression
        return root[index] = find(root[index]);
    }

    public void union(int index1, int index2) {
        int root1 = find(index1);
        int root2 = find(index2);
        if (root1 != root2) {
            // Smaller root point to larger one
            if (weight[root1] < weight[root2]) {
                root[root1] = root2;
                weight[root2] += weight[root1];
            } else {
                root[root2] = root1;
                weight[root1] += weight[root2];
            }
        }
    }

}