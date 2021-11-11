/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package questions.temp;

import java.util.Arrays;

/*

In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, 
plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. 
The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. 
Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. 
If there are multiple answers, return the answer that occurs last indegree the given 2D-array.


Example 1:
    Input: [[1,2], [1,3], [2,3]]
    Output: [2,3]
    Explanation: The given directed graph will be like this:
      1
     / \
    v   v
    2-->3

Example 2:
    Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
    Output: [4,1]
    Explanation: The given directed graph will be like this:
    5 <- 1 -> 2
         ^    |
         |    v
         4 <- 3
     
     
Note:
    The size of the input 2D-array will be between 3 and 1000.
    Every integer represented indegree the 2D-array will be between 1 and N, where N is the size of the input array.

*/

public class _0685_Redundant_Connection_II {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _0685_Redundant_Connection_II().findRedundantDirectedConnection_UF(new int[][] { { 1, 2 }, { 1, 3 }, { 3, 4 }, { 2, 4 } })));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Case 1:
    //     The indegree of every node is 1
    //     There must be a circle indegree the graph, the last edge that constructs the circle is the redundant edge
    // Case 2:
    //     There is a node whose indegree is 2
    //         1. The two incoming edges are both on the circle, the latter one is the redundant edge
    //         2. The two incoming edges, one is indegree the circle, one is not, the one which is inside the circle is the redundant edge
    // Note:
    //     A circle of n nodes can union at most n - 1 times
    //     If union n - 1 times, all nodes is rooted to same node
    //     If union n - 2 times, there are two distict root nodes
    public int[] findRedundantDirectedConnection_UF(int[][] edges) {
        // Union-find initialization
        int[] indegree = new int[edges.length + 1];
        int[] roots = new int[edges.length + 1];
        int[] size = new int[edges.length + 1];
        int x = 0;
        for (int i = 0; i < edges.length; i++) {
            indegree[edges[i][1]]++;
            roots[i + 1] = i + 1;
            size[i + 1] = 1;
            if (indegree[edges[i][1]] == 2) {
                x = edges[i][1];
            }
        }
        int _1st_incoming = -1;
        int _2nd_incoming = -1;
        // Key point:
        // When we meet the edge whose outgoing node is the node whose indegree is 2, we don't union the edge
        // This is because, in case 2, 
        //     If two edges are both on the circle
        //         Then they could union after the for iteration because there two edges are excluded to union, (the latter is the redundant)
        //     If only one edge on the circle, 
        //         After the for iteration, the root of the nodes on the circle union to the same node
        //         Then the edge on the circle could not union (which is the redundant), however the other one which is outside of the circle could union
        //     Merge, if the 1st edge could union, then return the 2nd one
        for (int i = 0; i < edges.length; i++) {
            if (_1st_incoming == -1 && edges[i][1] == x) {
                _1st_incoming = i;
            } else if (_1st_incoming != -1 && edges[i][1] == x) {
                _2nd_incoming = i;
            } else if (!union(roots, size, edges[i][0], edges[i][1])) {
                // Case 1, indegree of every node is 1
                return edges[i];
            }
        }
        // This is Case 2
        if (union(roots, size, edges[_1st_incoming][0], edges[_1st_incoming][1])) {
            return edges[_2nd_incoming];
        } else {
            return edges[_1st_incoming];
        }
    }

    private int find(int[] roots, int index) {
        if (roots[index] == index) {
            return index;
        }
        return roots[index] = find(roots, roots[index]);
    }

    private boolean union(int[] roots, int[] size, int index1, int index2) {
        int r1 = find(roots, index1);
        int r2 = find(roots, index2);
        if (r1 != r2) {
            if (size[r1] < size[r2]) {
                roots[r1] = r2;
                size[r2] += size[r1];
            } else {
                roots[r2] = r1;
                size[r1] += size[r2];
            }
            return true;
        }
        return false;
    }
}
