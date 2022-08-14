package algorithms.union_find;

public class UnionFindArray {

    private final int[] parent;
    private int[] height;

    public UnionFindArray(int[] parent) {
        this.parent = parent;
    }

    public UnionFindArray(int N) {
        // Initialization
        parent = new int[N];
        height = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
            height[i] = 1;
        }
    }

    public int findRoot(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path compression: all the nodes on the path will point to the root.
        return parent[i] = findRoot(parent[i]);
    }

    public void connect(int m, int n) {
        int mm = findRoot(m);
        int nn = findRoot(n);
        if (mm != nn) {
            // Smaller root point to larger one
            if (height[mm] < height[nn]) {
                parent[mm] = nn;
            } else {
                parent[nn] = mm;
                if (height[mm] == height[nn]) {
                    ++height[mm];
                }
            }
        }
    }

}
