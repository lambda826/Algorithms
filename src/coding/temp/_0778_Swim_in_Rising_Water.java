/**
 *  @author Yunxiang He
 *  @date 08/05/2018
 */

package coding.temp;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/*

On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. 
At time t, the depth of the water everywhere is t. 
You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. 
You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). 
What is the least time until you can reach the bottom right square (N-1, N-1)?


Example 1:
    Input: [[0,2],[1,3]]
    Output: 3
    Explanation:
    At time 0, you are in grid location (0, 0).
    You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
    You cannot reach point (1, 1) until time 3.
    When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:
    Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
    Output: 16
    Explanation:
     0  1  2  3  4
    24 23 22 21  5
    12 13 14 15 16
    11 17 18 19 20
    10  9  8  7  6
    The final route is marked in bold.
    We need to wait until time 16 so that (0, 0) and (4, 4) are connected.


Note:
    2 <= N <= 50.
    grid[i][j] is a permutation of [0, ..., N*N - 1].

*/

public class _0778_Swim_in_Rising_Water {
    public static void main(String[] args) {
        int[][] grid = { { 0, 1, 2, 3, 4 }, { 24, 23, 22, 21, 5 }, { 12, 13, 14, 15, 16 }, { 11, 17, 18, 19, 20 }, { 10, 9, 8, 7, 6 } };
        System.out.println(new _0778_Swim_in_Rising_Water().swimInWater_BinarySearch_DFS(grid));
        System.out.println(new _0778_Swim_in_Rising_Water().swimInWater_UF(grid));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int N;
    private final int[] dirs = { -1, 0, 1, 0, -1 };

    public int swimInWater_BinarySearch_DFS(int[][] grid) {
        this.N = grid.length;
        // lo is initiallized to the initial element
        int lo = grid[0][0];
        int hi = N * N;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (!DFS(grid, mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private boolean DFS(int[][] grid, int mid) {
        boolean[] visited = new boolean[N * N];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        while (!deque.isEmpty()) {
            int k = deque.removeLast();
            int r = k / N;
            int c = k % N;
            if (r == N - 1 && c == N - 1) {
                return true;
            }
            if (!visited[r * N + c]) {
                visited[r * N + c] = true;
                for (int i = 0; i < 4; i++) {
                    int nei_r = r + dirs[i];
                    int nei_c = c + dirs[i + 1];
                    int nei = nei_r * N + nei_c;
                    if (nei_r >= 0 && nei_c >= 0 && nei_r < N && nei_c < N && !visited[nei] && grid[nei_r][nei_c] <= mid) {
                        deque.addLast(nei);
                    }
                }
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int swimInWater_Heap(int[][] grid) {
        int t = 0;
        int N = grid.length;
        boolean[] visited = new boolean[N * N];
        PriorityQueue<Integer> pque = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer I1, Integer I2) {
                return grid[I1 / N][I1 % N] - grid[I2 / N][I2 % N];
            }
        });
        pque.add(0);
        while (true) {
            int n = pque.remove();
            int r = n / N;
            int c = n % N;
            t = Math.max(t, grid[r][c]);
            if (r == N - 1 && c == N - 1) {
                break;
            }
            if (!visited[r * N + c]) {
                visited[r * N + c] = true;
                for (int k = 0; k < 4; k++) {
                    int nei_r = r + dirs[k];
                    int nei_c = c + dirs[k + 1];
                    int nei = nei_r * N + nei_c;
                    if (nei_r >= 0 && nei_c >= 0 && nei_r < N && nei_c < N && !visited[nei]) {
                        pque.add(nei);
                    }
                }
            }
        }
        return t;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int swimInWater_UF(int[][] grid) {
        this.N = grid.length;
        UnionFind uf = new UnionFind(grid);
        // Record the cord of every element
        int index[] = new int[N * N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                index[grid[r][c]] = r * N + c;
            }
        }
        int t = 0;
        while (t < N * N) {
            int r = index[t] / N;
            int c = index[t] % N;
            for (int k = 0; k < 4; k++) {
                int nei_r = r + dirs[k];
                int nei_c = c + dirs[k + 1];
                if (nei_r >= 0 && nei_c >= 0 && nei_r < N && nei_c < N && grid[nei_r][nei_c] <= t) {
                    uf.union(r * N + c, nei_r * N + nei_c);
                }
            }
            if (uf.isConnected()) {
                break;
            }
            t++;
        }
        return t;
    }

    class UnionFind {
        int[] entry;

        UnionFind(int[][] grid) {
            entry = new int[N * N];
            for (int i = 0; i < N * N; i++) {
                entry[i] = i;
            }
        }

        public int find(int index) {
            if (entry[index] == index) {
                return index;
            }
            return entry[index] = find(entry[index]);
        }

        public void union(int index1, int index2) {
            int root1 = find(index1);
            int root2 = find(index2);
            if (root1 != root2) {
                entry[root1] = root2;
            }
        }

        public boolean isConnected() {
            return find(0) == find(entry.length - 1);
        }
    }

}
