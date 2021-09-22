/**
 *  @author Yunxiang He
 *  @date 01/09/2019
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.List;

/*

In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  
These characters divide the square into contiguous regions.
(Note that backslash characters are escaped, so a \ is represented as "\\".)
Return the number of regions.


Example 1:
    Input:
    [
      " /",
      "/ "
    ]
    Output: 2
    Explanation: The 2x2 grid is as follows:

Example 2:
    Input:
    [
      " /",
      "  "
    ]
    Output: 1
    Explanation: The 2x2 grid is as follows:

Example 3:
    Input:
    [
      "\\/",
      "/\\"
    ]
    Output: 4
    Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
    The 2x2 grid is as follows:

Example 4:
    Input:
    [
      "/\\",
      "\\/"
    ]
    Output: 5
    Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
    The 2x2 grid is as follows:

Example 5:
    Input:
    [
      "//",
      "/ "
    ]
    Output: 3
    Explanation: The 2x2 grid is as follows:


Note:
    1 <= grid.length == grid[0].length <= 30
    grid[i][j] is either '/', '\', or ' '.

*/

public class _0959_Regions_Cut_By_Slashes {

    public static void main(String[] args) {
        System.out.println(new _0959_Regions_Cut_By_Slashes().regionsBySlashes_DFS(new String[] { " /", "/ " }));
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Divide each grid into 4 triangles, up, right, bot, left (0, 1, 2, 3)
    // 2. If the slash is /, union up-left, bot-right
    // 3. If the slash is \, union up-right, bot-left
    // 4. Union adjacent grid, bot-top, left-right
    private int componentsCount = 0;
    private int[] root;
    private int[] size;

    public int regionsBySlashes_UF(String[] grid) {
        int N = grid.length;
        componentsCount = 4 * N * N;
        // Initialization
        root = new int[componentsCount];
        size = new int[componentsCount];
        for (int i = 0; i < componentsCount; ++i) {
            root[i] = i;
            size[i] = 1;
        }
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                int index = 4 * (r * N + c);
                char val = grid[r].charAt(c);
                // index + offset
                if (val == '/') {
                    union(index + 1, index + 2);
                    union(index + 0, index + 3);
                } else if (val == '\\') {
                    union(index + 0, index + 1);
                    union(index + 2, index + 3);
                } else {
                    union(index + 0, index + 1);
                    union(index + 1, index + 2);
                    union(index + 2, index + 3);
                }
                // up-bot
                if (r + 1 < N) {
                    union(index + 2, (index + 4 * N) + 0);
                }
                // left-right
                if (c + 1 < N) {
                    union(index + 1, (index + 4) + 3);
                }
            }
        }
        return componentsCount;
    }

    private int find(int index) {
        if (root[index] == index) {
            return index;
        }
        return root[index] = find(root[index]);
    }

    private void union(int x, int y) {
        int r1 = find(x);
        int r2 = find(y);
        if (r1 != r2) {
            componentsCount--;
            if (size[r1] < size[r2]) {
                size[r2] += size[r1];
                root[r1] = r2;
            } else {
                size[r1] += size[r2];
                root[r2] = r1;
            }
        }
    }

}
