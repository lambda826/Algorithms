/**
 *  @author Yunxiang He
 *  @date 01/22/2018
 */

package coding.temp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.


Example 1:
    Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
    Output: 5
    Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.

Example 2:
    Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    Output: 0
    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.


Note:
    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

*/

public class _0127_Word_Ladder {

    public static void main(String[] args) {
        new _0127_Word_Ladder().ladderLength("hit", "cog", Arrays.asList(new String[] { "hot", "dot", "dog", "lot", "log", "cog" }));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Queue<String> que = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        que.add(beginWord);
        visited.add(beginWord);
        int step = 1;
        String word;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; ++i) {
                word = que.poll();
                if (word.equals(endWord)) {
                    return step;
                }
                char[] chs = word.toCharArray();
                for (int j = 0; j < beginWord.length(); ++j) {
                    char ch = chs[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        if (ch != k) {
                            chs[j] = k;
                            String newString = String.valueOf(chs);
                            if (wordSet.contains(newString) && !visited.contains(newString)) {
                                visited.add(newString);
                                que.offer(newString);
                            }
                        }
                    }
                    chs[j] = ch;
                }
            }
            ++step;
        }
        return 0;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS Bidirection
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (wordList.contains(endWord)) {
            Set<String> wordSet = new HashSet<>(wordList);
            Set<String> beginSet = new HashSet<String>();
            Set<String> endSet = new HashSet<String>();
            HashSet<String> visited = new HashSet<String>();
            beginSet.add(beginWord);
            endSet.add(endWord);
            int step = 2;
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                if (beginSet.size() > endSet.size()) {
                    Set<String> set = beginSet;
                    beginSet = endSet;
                    endSet = set;
                }
                Set<String> temp = new HashSet<String>();
                for (String word : beginSet) {
                    char[] chs = word.toCharArray();
                    for (int i = 0; i < chs.length; i++) {
                        for (char c = 'a'; c <= 'z'; c++) {
                            char old = chs[i];
                            chs[i] = c;
                            String target = String.valueOf(chs);
                            if (endSet.contains(target)) {
                                return step;
                            }
                            if (!visited.contains(target) && wordSet.contains(target)) {
                                temp.add(target);
                                visited.add(target);
                            }
                            chs[i] = old;
                        }
                    }
                }
                beginSet = temp;
                step++;
            }
        }
        return 0;
    }
}
