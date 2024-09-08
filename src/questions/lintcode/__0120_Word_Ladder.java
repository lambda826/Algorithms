/**
 * @author Yunxiang He
 * @date 02/16/2019
 */

package questions.lintcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*

Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
Only one letter can be changed at a time
Each intermediate word must exist in the dictionary


Example
    Given:
    start = "hit"
    end = "cog"
    dict = ["hot","dot","dog","lot","log"]
    As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.


Notice
    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.

*/

public class __0120_Word_Ladder {

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        Set<String> dict = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        System.out.println(new __0120_Word_Ladder().ladderLength(start, end, dict));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int ladderLength(String start, String end, Set<String> dict) {
        int len = start.length();
        int step = 0;
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        dict.add(end);
        visited.add(start);
        q.add(start);
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                String temp = q.poll();
                if (temp.equals(end)) {
                    return step;
                }
                // Transform
                char[] chs = temp.toCharArray();
                for (int c = 0; c < len; ++c) {
                    char ch = chs[c];
                    for (char j = 'a'; j <= 'z'; ++j) {
                        if (ch != j) {
                            chs[c] = j;
                            String str = String.valueOf(chs);
                            if (dict.contains(str) && !visited.contains(str)) {
                                visited.add(str);
                                q.offer(str);
                            }
                        }
                    }
                    chs[c] = ch;
                }
            }
        }
        return 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int ladderLength_biDirection(String start, String end, Set<String> dict) {
        int len = start.length();
        int step = 1;
        Set<String> visited = new HashSet<>();
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        startSet.add(start);
        endSet.add(end);
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            // Ensure to iterate the smaller set
            Set<String> set = new HashSet<>();
            if (startSet.size() > endSet.size()) {
                Set<String> tempSet = startSet;
                startSet = endSet;
                endSet = tempSet;
            }
            for (String temp : startSet) {
                if (endSet.contains(temp)) {
                    return step;
                } else if (!visited.contains(temp)) {
                    visited.add(temp);
                    char[] chs = temp.toCharArray();
                    // Transform
                    for (int c = 0; c < len; ++c) {
                        char ch = chs[c];
                        for (char j = 'a'; j <= 'z'; ++j) {
                            if (ch != j) {
                                chs[c] = j;
                                String str = String.valueOf(chs);
                                if (dict.contains(str)) {
                                    set.add(str);
                                }
                            }
                        }
                        chs[c] = ch;
                    }
                }
            }
            startSet = set;
            step++;
        }
        return 0;
    }
}
