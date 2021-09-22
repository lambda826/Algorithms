/**
 *  @author Yunxiang He
 *  @date 06/22/2018
 */

package coding.leetcode.temp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*

Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. 
Reconstruction means building a shortest common supersequence of the sequences in seqs 
    (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.


Example 1:
    Input:
    org: [1,2,3], seqs: [[1,2],[1,3]]
    Output:
    false
    Explanation:
    [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

Example 2:
    Input:
    org: [1,2,3], seqs: [[1,2]]
    Output:
    false
    Explanation:
    The reconstructed sequence can only be [1,2].

Example 3:
    Input:
    org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
    Output:
    true
    Explanation:
    The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

Example 4:
    Input:
    org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
    Output:
    true


UPDATE (2017/1/8):
    The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). 
    Please reload the code definition to get the latest changes.

*/

public class _0444_Sequence_Reconstruction {

    public static void main(String[] args) {
        List<List<Integer>> l = new ArrayList<>();
        l.add(Arrays.asList(new Integer[] { 1 }));
        l.add(Arrays.asList(new Integer[] { 1 }));
        l.add(Arrays.asList(new Integer[] { 1 }));
        System.out.println(new _0444_Sequence_Reconstruction().sequenceReconstruction(new int[] { 1 }, l));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();
        for (List<Integer> list : seqs) {
            if (list.size() != 0) {
                nodes.add(list.get(0));
                graph.putIfAbsent(list.get(0), new ArrayList<>());
                for (int i = 0; i < list.size() - 1; ++i) {
                    graph.putIfAbsent(list.get(i), new ArrayList<>());
                    graph.get(list.get(i)).add(list.get(i + 1));
                    indegree.put(list.get(i + 1), indegree.getOrDefault(list.get(i + 1), 0) + 1);
                    nodes.add(list.get(i + 1));
                }
            }

        }
        if (org.length != nodes.size()) {
            return false;
        }
        Queue<Integer> oIndegree = new ArrayDeque<>();
        for (int key : graph.keySet()) {
            if (!indegree.containsKey(key)) {
                oIndegree.offer(key);
            }
        }
        int k = 0;
        List<Integer> temp;
        while (!oIndegree.isEmpty()) {
            if (oIndegree.size() != 1 || oIndegree.peek() != org[k]) {
                return false;
            }
            temp = graph.get(oIndegree.remove());
            if (temp != null) {
                for (int nei : temp) {
                    indegree.put(nei, indegree.get(nei) - 1);
                    if (indegree.get(nei) == 0) {
                        oIndegree.add(nei);
                    }
                }
            }
            k++;
        }
        return k == org.length;
    }
}
