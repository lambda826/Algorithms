/**
 *  @author Yunxiang He
 *  @date 03/31/2019
 */

package coding.leetcode.temp;

import java.util.Arrays;
import java.util.PriorityQueue;

/*

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. 
If there is no such route, output -1.


Example 1:
    Input: 
        n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        src = 0, dst = 2, k = 1
    Output: 
        200
    Explanation: 
        The graph looks like this:
        The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

Example 2:
    Input: 
        n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        src = 0, dst = 2, k = 0
    Output: 
        500
    Explanation: 
        The graph looks like this:
        The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.


Note:
    The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
    The size of flights will be in range [0, n * (n - 1) / 2].
    The format of each flight will be (src, dst, price).
    The price of each flight will be in the range [1, 10000].
    k is in the range of [0, n - 1].
    There will not be any duplicated flights or self cycles.

*/

public class _0787_Cheapest_Flights_Within_K_Stops {

    public static void main(String[] args) {
        _0787_Cheapest_Flights_Within_K_Stops test = new _0787_Cheapest_Flights_Within_K_Stops();
        //        int[][] flights = { { 0, 1, 2 }, { 1, 2, 1 }, { 2, 0, 10 }, };
        //        int n = 3;
        //        int src = 1;
        //        int dst = 2;
        //        int k = 1;
        int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 0, 2, 500 }, };
        int n = 3;
        int src = 0;
        int dst = 2;
        int k = 1;
        System.out.println(test.findCheapestPrice(n, flights, src, dst, k));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = Integer.MAX_VALUE / 2;
        int[] pre = new int[n];
        int[] dist = new int[n];
        Arrays.fill(pre, INF);
        pre[src] = 0;
        int res = pre[dst];
        for (int i = 0; i <= k; ++i) {
            Arrays.fill(dist, INF);
            for (int[] flight : flights) {
                dist[flight[1]] = Math.min(dist[flight[1]], pre[flight[0]] + flight[2]);
            }
            pre = Arrays.copyOfRange(dist, 0, dist.length);
            res = Math.min(res, pre[dst]);
        }
        return res == INF ? -1 : res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        int[] shortest = new int[n];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        shortest[src] = 0;
        int[] stops = new int[n];
        stops[src] = -1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> shortest[a] - shortest[b]);
        minHeap.offer(src);
        while (!minHeap.isEmpty()) {
            int curr = minHeap.poll();
            for (int nei = 0; nei < n; ++nei) {
                if (graph[curr][nei] != 0 && stops[curr] + 1 <= K && shortest[curr] + graph[curr][nei] < shortest[nei]) {
                    shortest[nei] = shortest[curr] + graph[curr][nei];
                    stops[nei] = stops[curr] + 1;
                    minHeap.offer(nei);
                }
            }
        }
        return shortest[dst] != Integer.MAX_VALUE ? shortest[dst] : -1;
    }

}
