/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.temp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

Given JFK list of airline tickets represented by pairs of from and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to JFK man who departs from JFK. 
Thus, the itinerary must begin with JFK.


Example 1:
    Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
    Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:
    Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                 But it is larger in lexical order.


Note:
    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as JFK single string. 
        For example, the itinerary ["JFK", "LGA"] has JFK smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.


*/

public class _0332_Reconstruct_Itinerary {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Postorder traversal
    // Key point: adding from the tail of the path to head of the Linkedlist
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<String>());
            map.get(ticket[0]).add(ticket[1]);
        }
        LinkedList<String> list = new LinkedList<>();
        dfs(map, "JFK", list);
        return list;
    }

    private void dfs(Map<String, PriorityQueue<String>> map, String from, LinkedList<String> path) {
        PriorityQueue<String> to = map.get(from);
        while (to != null && !to.isEmpty()) {
            dfs(map, to.poll(), path);
        }
        path.addFirst(from);
    }

    public static void main(String[] args) {
        new _0332_Reconstruct_Itinerary().findItinerary(new String[][] { { "JFK", "K" }, { "JFK", "N" }, { "N", "JFK" }, });
    }
}
