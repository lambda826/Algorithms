/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.leetcode.temp;

/*

In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. 
The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. 
Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. 
If there are multiple answers, return the answer that occurs last in the given 2D-array. 
The answer edge [u, v] should be in the same format, with u < v.


Example 1:
    Input: [[1,2], [1,3], [2,3]]
    Output: [2,3]
    Explanation: The given undirected graph will be like this:
      1
     / \
    2 - 3

Example 2:
    Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
    Output: [1,4]
    Explanation: The given undirected graph will be like this:
    5 - 1 - 2
        |   |
    4 - 3


Note:
    The size of the input 2D-array will be between 3 and 1000.
    Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

*/

public class _0684_Redundant_Connection {

    public static void main(String[] args) {
        new _0684_Redundant_Connection().findRedundantConnection_UF(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] findRedundantConnection_UF(int[][] edges) {
        int[] root = new int[edges.length + 1];
        int[] size = new int[edges.length + 1];
        // Initialization
        for (int i = 1; i < edges.length + 1; ++i) {
            root[i] = i;
            size[i] = 1;
        }
        int[] result = null;
        int r1;
        int r2;
        for (int[] edge : edges) {
            r1 = find(root, edge[0]);
            r2 = find(root, edge[1]);
            if (r1 == r2) {
                result = edge;
                break;
            } else if (size[r1] < size[r2]) {
                size[r2] += size[r1];
                root[r1] = r2;
            } else {
                size[r1] += size[r2];
                root[r2] = r1;
            }
        }
        return result;
    }

    private int find(int[] root, int index) {
        if (root[index] == index) {
            return index;
        }
        return root[index] = find(root, root[index]);
    }

}
