package questions._20_unionFind;

import java.util.ArrayList;
import java.util.List;

/*

An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
These characters divide the square into contiguous regions.

Given the grid represented as a string array, return the number of regions.
Note that backslash characters are escaped, so a '\' is represented as '\\'.


Example 1:
    Input:
        grid = [" /",
                "/ "]
    Output:
        2

Example 2:
    Input:
        grid = [" /",
                "  "]
    Output:
        1

Example 3:
    Input:
        grid = ["/\\",
                "\\/"]
    Output:
        5
    Explanation:
        Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.


Constraints:
    n == grid.length == grid[i].length
    1 <= n <= 30
    grid[i][j] is either '/', '\', or ' '.

*/
public class _0959_Regions_Cut_By_Slashes {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Union endpoints of the slashes:
    // When the endpoints are already connected, merging them resulting cutting a new face.
    class Solution_UF {
        public int regionsBySlashes(String[] grid) {
            int n = grid.length;
            int[] parent = new int[(n + 1) * (n + 1)];
            int[] height = new int[(n + 1) * (n + 1)];
            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
            }

            // Union surroundings
            for (int i = 0; i < n; ++i) {
                union(parent, height, 0 * (n + 1) + i, 0 * (n + 1) + i + 1);
                union(parent, height, n * (n + 1) + i, n * (n + 1) + i + 1);
                union(parent, height, i * (n + 1), (i + 1) * (n + 1));
                union(parent, height, i * (n + 1) + n, (i + 1) * (n + 1) + n);
            }

            int count = 1;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    int mm;
                    int nn;
                    if (grid[i].charAt(j) == '/') {
                        mm = i * (n + 1) + j + 1;
                        nn = (i + 1) * (n + 1) + j;
                    } else if (grid[i].charAt(j) == '\\') {
                        mm = i * (n + 1) + j;
                        nn = (i + 1) * (n + 1) + (j + 1);
                    } else {
                        continue;
                    }
                    if (union(parent, height, mm, nn)) {
                        ++count;
                    }
                }
            }
            return count;
        }

        private int find(int[] parent, int i) {
            return parent[i] == i ? i : (parent[i] = find(parent, parent[i]));
        }

        private boolean union(int[] parent, int[] height, int i, int j) {
            int ii = find(parent, i);
            int jj = find(parent, j);
            if (ii == jj) {
                return true;
            } else {
                if (height[ii] < height[jj]) {
                    parent[ii] = jj;
                } else {
                    parent[jj] = ii;
                    if (height[ii] == height[jj]) {
                        ++height[ii];
                    }
                }
                return false;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Divide each grid into 4 triangles, up, right, bot, left (0, 1, 2, 3)
    // 2. If the slash is /, union up-left, bot-right
    // 3. If the slash is \, union up-right, bot-left
    // 4. Union adjacent grid, top-bottom, left-right
    // 5. Count connected component
    class Solution_UF2 {

        public int regionsBySlashes(String[] grid) {
            int n = grid.length;
            int[] parent = new int[4 * n * n];
            int[] height = new int[4 * n * n];
            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
            }
            int[] count = { 4 * n * n };
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    char ch = grid[i].charAt(j);
                    int k = (i * n + j) * 4;
                    if (ch == '\\') { // union (0, 1), (2, 3)
                        union(parent, height, count, k + 0, k + 1);
                        union(parent, height, count, k + 2, k + 3);
                    } else if (ch == '/') { // union (0, 3), (1, 2)
                        union(parent, height, count, k + 0, k + 3);
                        union(parent, height, count, k + 1, k + 2);
                    } else { // union (0, 1, 2, 3)
                        union(parent, height, count, k + 0, k + 1);
                        union(parent, height, count, k + 1, k + 2);
                        union(parent, height, count, k + 2, k + 3);
                    }
                    // Union left to right
                    if (j + 1 < n) {
                        union(parent, height, count, k + 1, k + 4 + 3);
                    }
                    // Union up to down
                    if (i + 1 < n) {
                        union(parent, height, count, k + 2, k + 4 * n);
                    }
                }
            }
            return count[0];
        }

        private int find(int[] parent, int i) {
            return (i == parent[i]) ? i : (parent[i] = find(parent, parent[i]));
        }

        private void union(int[] parent, int[] height, int[] count, int i, int j) {
            int ii = find(parent, i);
            int jj = find(parent, j);
            if (ii != jj) {
                if (height[ii] < height[jj]) {
                    parent[ii] = jj;
                } else {
                    parent[jj] = ii;
                    if (height[ii] == height[jj]) {
                        ++height[ii];
                    }
                }
                --count[0];
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the number of circles in the graph
    // 1. Represent the grids as a graph (adjacent list)
    //    1) Count the vertices (n + 1) * (n + 1)
    //    2) Connect the edges
    // 2. DFS to find the circles
    //    1) Visited statues
    //       1> If not visited (visited[i] == 0): to visit
    //       2> If visiting (visited[i] == 1), skip
    //       3> If visited(visited[i] == 2), circle found, count++
    private int count = 0;

    public int regionsBySlashes_DFS(String[] grid) {
        int n = grid.length + 1;
        int nn = n * n;
        List<Integer>[] graph = new List[nn];
        for (int i = 0; i < nn; i++) {
            graph[i] = new ArrayList<>();
        }
        // connect first row & last row & first col & last col
        for (int i = 0; i < n - 1; i++) {
            connect(i, i + 1, graph);
            connect(i + nn - n, i + 1 + nn - n, graph);
            connect(i * n, (i + 1) * n, graph);
            connect(i * n + n - 1, (i + 1) * n + n - 1, graph);
        }
        // connect other vertices
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == '/') {
                    connect((i + 1) * n + j, i * n + j + 1, graph);
                } else if (grid[i].charAt(j) == '\\') {
                    connect(i * n + j, (i + 1) * n + j + 1, graph);
                }
            }
        }
        // Find the circles
        int[] visited = new int[nn];
        for (int i = 0; i < nn; i++) {
            if (visited[i] == 0) {
                DFS(graph, visited, i);
            }
        }
        return count;
    }

    private void DFS(List<Integer>[] graph, int[] visited, int i) {
        if (visited[i] == 0) {
            visited[i] = 1;
            for (int next : graph[i]) {
                DFS(graph, visited, next);
            }
            visited[i] = 2;
        } else if (visited[i] == 2) {
            count++;
        }
    }

    private void connect(int n1, int n2, List<Integer>[] graph) {
        graph[n1].add(n2);
        graph[n2].add(n1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // https://zxi.mytechroad.com/blog/graph/leetcode-959-regions-cut-by-slashes/
    // 1. Divide each original grid into 3 * 3 grids
    //    1) Use diagonal grids to represent the slashes
    //    2) The diagonal divide the new 3 * 3 grids into 2 connected components
    // 2. DFS to find the connected components
    public int regionsBySlashes_DFS_Up3Scales(String[] grid) {
        int[][] scaled = new int[grid.length * 3][grid.length * 3];
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            String curr = grid[i];
            for (int j = 0; j < curr.length(); j++) {
                int r;
                int c;
                if (curr.charAt(j) == '/') {
                    r = i * 3;
                    c = (j + 1) * 3 - 1;
                    for (int k = 3; k > 0; k--) {
                        scaled[r++][c--] = 1;
                    }
                } else if (curr.charAt(j) == '\\') {
                    r = i * 3;
                    c = j * 3;
                    for (int k = 3; k > 0; k--) {
                        scaled[r++][c++] = 1;
                    }
                }
            }
        }
        // DFS to find all connected components
        for (int i = 0; i < scaled.length; i++) {
            for (int j = 0; j < scaled[i].length; j++) {
                if (scaled[i][j] == 0) {
                    DFS(scaled, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void DFS(int[][] grid, int i, int j) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid.length && grid[i][j] == 0) {
            grid[i][j] = 1;
            DFS(grid, i + 1, j);
            DFS(grid, i - 1, j);
            DFS(grid, i, j + 1);
            DFS(grid, i, j - 1);
        }
    }

}
