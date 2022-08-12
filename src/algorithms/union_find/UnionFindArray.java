package algorithms.union_find;

public class UnionFindArray {

    private final int[] root;
    private int[] weight;

    public UnionFindArray(int[] root) {
        this.root = root;
    }

    public UnionFindArray(int N) {
        // Initialization
        root = new int[N];
        weight = new int[N];
        for (int i = 0; i < N; ++i) {
            root[i] = i;
            weight[i] = 1;
        }
    }

    public int findRoot(int index) {
        if (root[index] == index) {
            return index;
        }
        // Path compression: all the nodes on the path will point to the root.
        return root[index] = findRoot(root[index]);
    }

    public void connect(int index1, int index2) {
        int root1 = findRoot(index1);
        int root2 = findRoot(index2);
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
