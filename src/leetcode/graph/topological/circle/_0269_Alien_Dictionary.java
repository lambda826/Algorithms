package leetcode.graph.topological.circle;

import java.util.ArrayDeque;
import java.util.Queue;

public class _0269_Alien_Dictionary {

    /**
     * <h2>269. Alien Dictionary — Kahn Topological Sort (array graph)</h2>
     *
     * <h3>Approach</h3>
     * Build a directed graph over at most 26 letters. For every adjacent pair of words, scan from left to right
     * and add a single directed edge {@code u -> v} at the first differing character (the letter in the earlier
     * word must precede the letter in the later word). If no difference is found and the first word is longer,
     * the ordering is invalid (return empty string). Finally, run Kahn's algorithm over only the letters that
     * actually appear in {@code words}.
     * <ul>
     *   <li>We store indegree in column 0: {@code indegree(x) = graph[x][0]}.</li>
     *   <li>We store edges in columns 1..26: {@code graph[u][v+1] = 1} means {@code u -> v}.</li>
     *   <li>Before adding an edge, check if it already exists to avoid double-counting indegrees.</li>
     * </ul>
     *
     * <h3>Complexity</h3>
     * Let {@code L = sum(|words[i]|)} be the total number of characters across all words, and {@code A <= 26}
     * the alphabet size.
     * <ul>
     *   <li>Building edges by comparing adjacent words scans each character at most once &rarr; {@code O(L)}.</li>
     *   <li>Kahn's algorithm touches at most {@code A} nodes and {@code A^2} edges &rarr; {@code O(A^2)} (constant in practice).</li>
     *   <li><b>Total time:</b> {@code O(L + A^2)} &approx; {@code O(L)}.</li>
     *   <li><b>Space:</b> {@code O(A^2 + A)} for the 26x26 adjacency/indegree arrays and bookkeeping &rarr; constant w.r.t. input size.</li>
     * </ul>
     *
     * <h3>Edge Cases</h3>
     * <ul>
     *   <li>Invalid prefix: previous word longer while sharing the entire next word as a prefix &rarr; return "".</li>
     *   <li>Single word: no constraints; output the set of its unique letters in ascending (alphabet) order.</li>
     *   <li>Multiple valid orders: any topological ordering that respects the edges is acceptable.</li>
     * </ul>
     */
    static class Solution {
        public String alienOrder(String[] words) {
            // graph[u][0] = indegree(u); graph[u][v+1] == 1 表示 u -> v
            int[][] graph = new int[26][27];
            boolean[] vocab = new boolean[26];

            // 记录出现过的字符
            for (String w : words) {
                for (int i = 0; i < w.length(); i++) {
                    int c = w.charAt(i) - 'a';
                    vocab[c] = true;
                }
            }

            // 比较相邻单词，在首个不同字符处建边；顺便处理非法前缀
            for (int i = 1; i < words.length; i++) {
                String a = words[i - 1];
                String b = words[i];

                int minLen = Math.min(a.length(), b.length());
                boolean foundDiff = false;

                for (int j = 0; j < minLen; j++) {
                    int u = a.charAt(j) - 'a';
                    int v = b.charAt(j) - 'a';

                    if (u != v) {
                        // 只在首次新增边时增加入度，避免重复计数
                        if (graph[u][v + 1] == 0) {
                            graph[u][v + 1] = 1;
                            graph[v][0]++;
                        }
                        foundDiff = true;
                        break; // 只在首个不同处建边
                    }
                }

                // 未找到不同字符且前一个更长 → 非法前缀，直接无解
                if (!foundDiff && a.length() > b.length()) {
                    return "";
                }
            }

            // Kahn 拓扑
            Queue<Integer> q = new ArrayDeque<>();
            int total = 0;
            for (int c = 0; c < 26; c++) {
                if (vocab[c]) {
                    total++;
                    if (graph[c][0] == 0) {
                        q.offer(c);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                int u = q.poll();
                sb.append((char) ('a' + u));
                for (int v = 0; v < 26; v++) {
                    if (graph[u][v + 1] == 1) {
                        graph[v][0]--;
                        if (graph[v][0] == 0) {
                            q.offer(v);
                        }
                    }
                }
            }
            return sb.length() == total ? sb.toString() : "";
        }
    }

}