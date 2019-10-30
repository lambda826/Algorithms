/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

For an undirected graph with tree characteristics, we can choose any node as the root. 
The result graph is then a rooted tree. 
Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. 
You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :
    Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
    
            0
            |
            1
           / \
          2   3 
    
    Output: [1]

Example 2 :
    Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
    
         0  1  2
          \ | /
            3
            |
            4
            |
            5 
    
    Output: [3, 4]


Note:
    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
        In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

*/

public class _0310_Minimum_Height_Trees {

    public static void main(String[] args) {
        System.out.println(new _0310_Minimum_Height_Trees().findMinHeightTrees(7, new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 2, 4 }, { 3, 5 }, { 4, 6 } }));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Topological variation
    // The nodes with only one neighbor is the left nodes, which can be safely deleted
    // Delete the nodes with 1 degree
    // We don't need visited because if we put the neighbors of the nodes with degree 1 into the queue,
    //     nodes with degree 1 are the neighbors of their neighbors,
    //     after degree--, their degrees become 0, thus won't be offered into the queue again
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        List<Integer>[] graph = new List[n];
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 1) {
                que.add(i);
            }
        }
        int count = n;
        int temp;
        int size;
        while (count > 2) {
            size = que.size();
            for (int i = 0; i < size; ++i) {
                count--;
                temp = que.remove();
                for (int nei : graph[temp]) {
                    if (--degree[nei] == 1) {
                        que.add(nei);
                    }
                }
            }
        }
        return res = new ArrayList<>(que);
    }
}
