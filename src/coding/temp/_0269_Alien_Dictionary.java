/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.temp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.


Example 1:
    Input:
    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]
    Output: "wertf"

Example 2:
    Input:
    [
      "z",
      "x"
    ]
    Output: "zx"

Example 3:
    Input:
    [
      "z",
      "x",
      "z"
    ] 
    Output: "" 
    Explanation: The order is invalid, so return "".


Note:
    You may assume all letters are in lowercase.
    You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.

*/

public class _0269_Alien_Dictionary {

    public static void main(String[] args) {
        _0269_Alien_Dictionary test = new _0269_Alien_Dictionary();
        System.out.println(test.alienOrder_Topological(new String[] { "wrt", "wrf", "er", "ett", "rftt", }));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String alienOrder_Topological(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        Set<Character> nodes = new HashSet<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                nodes.add(ch);
            }
        }
        // build graph & build indegree map
        for (int i = 0; i < words.length - 1; i++) {
            build(words[i], words[i + 1], graph, indegree);
        }
        StringBuilder order = new StringBuilder();
        Queue<Character> o_indegree = new LinkedList<>();
        for (char ch : nodes) {
            if (!indegree.containsKey(ch)) {
                o_indegree.add(ch);
            }
        }
        while (!o_indegree.isEmpty()) {
            char ch = o_indegree.remove();
            order.append(ch);
            if (graph.get(ch) != null) {
                for (char next : graph.get(ch)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        o_indegree.add(next);
                    }
                }
            }
        }
        return order.length() == nodes.size() ? order.toString() : "";
    }

    private void build(String word1, String word2, Map<Character, Set<Character>> graph, Map<Character, Integer> indegree) {
        int minLen = Math.min(word1.length(), word2.length());
        for (int i = 0; i < minLen; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                graph.putIfAbsent(word1.charAt(i), new HashSet<>());
                if (graph.get(word1.charAt(i)).add(word2.charAt(i))) {
                    indegree.put(word2.charAt(i), indegree.getOrDefault(word2.charAt(i), 0) + 1);
                }
                break;
            }
        }
    }
}
