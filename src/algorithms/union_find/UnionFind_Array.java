package algorithms.union_find;

public class UnionFind_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] root;
    private int[] size;

    public UnionFind_Array(int N) {
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

}