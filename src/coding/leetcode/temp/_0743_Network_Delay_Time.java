/**
 *  @author Yunxiang He
 *  @date 06/15/2018
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), 
    where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. 
How long will it take for all nodes to receive the signal? 
If it is impossible, return -1.


Note:
    N will be in the range [1, 100].
    K will be in the range [1, N].
    The length of times will be in the range [1, 6000].
    All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

*/

public class _0743_Network_Delay_Time {

    public static void main(String[] args) {
        System.out.println(new _0743_Network_Delay_Time().networkDelayTime2(new int[][] { { 1, 2, 3 }, { 2, 3, 1 }, { 1, 3, 5 } }, 3, 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. build graph
    // 2. initialize the distance to every node to ∞
    // 3. update the shortest distance matrix of the nodes that can be reached
    public int networkDelayTime(int[][] times, int N, int K) {
        // initialization
        int[][] graph = new int[N + 1][N + 1];
        for (int[] row : graph) {
            Arrays.fill(row, -1);
        }
        for (int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        int[] shortest = new int[N + 1];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        shortest[0] = 0;
        shortest[K] = 0;
        // traverse
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> shortest[a] - shortest[b]);
        minHeap.offer(K);
        while (!minHeap.isEmpty()) {
            int curr = minHeap.poll();
            for (int nei = 1; nei <= N; ++nei) {
                if (graph[curr][nei] != -1 && graph[curr][nei] + shortest[curr] < shortest[nei]) {
                    shortest[nei] = graph[curr][nei] + shortest[curr];
                    minHeap.offer(nei);
                }
            }
        }
        int max = 0;
        for (int d : shortest) {
            if (d == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, d);
        }
        return max;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int networkDelayTime2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = buildGraph(times);
        int[] shortest = new int[N + 1];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        shortest[0] = 0;
        shortest[K] = 0;
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
        minHeap.offer(new int[] { K, 0 });
        // Traverse
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            if (!visited[curr[0]]) {
                visited[curr[0]] = true;
                List<int[]> neis = graph.get(curr[0]);
                if (neis != null) {
                    for (int[] nei : neis) {
                        shortest[nei[0]] = Math.min(shortest[nei[0]], shortest[curr[0]] + nei[1]);
                        minHeap.offer(new int[] { nei[0], shortest[nei[0]] });
                    }
                }
            }
        }
        int max = 0;
        for (int d : shortest) {
            if (d == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, d);
        }
        return max;
    }

    private Map<Integer, List<int[]>> buildGraph(int[][] times) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[] { time[1], time[2] });
        }
        return graph;
    }
}
