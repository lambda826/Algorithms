/**
 * @author Yunxiang He
 * @date 02/20/2019
 */

package coding.leetcode.temp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*

Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
    Only one letter can be changed at a time
    Each transformed word must exist in the word list. 
    Note that beginWord is not a transformed word.


Example 1:
    Input:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]
    Output:
        [
          ["hit","hot","dot","dog","cog"],
          ["hit","hot","lot","log","cog"]
        ]

Example 2:
    Input:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]
    Output: 
        []
    Explanation: 
        The endWord "cog" is not in wordList, therefore no possible transformation.


Note:
    Return an empty list if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

*/

public class _0126_Word_Ladder_II {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.contains(endWord)) {
            boolean isFound = false;
            Deque<List<String>> queue = new ArrayDeque<>();
            queue.offer(new ArrayList<>() {{
                add(beginWord);
            }});
            while (!isFound && !queue.isEmpty()) {
                int size = queue.size();
                Set<String> currVisited = new HashSet<>(); // Only record used words for current layer and remove them for next layer;
                while (size-- > 0) { // Level traversal;
                    List<String> currList = queue.poll();
                    String currWord = currList.get(currList.size() - 1);
                    currVisited.add(currWord);
                    if (currWord.equals(endWord)) {
                        res.add(currList);
                        isFound = true;
                    }
                    char[] chs = currWord.toCharArray();
                    for (int i = 0; i < chs.length; ++i) {
                        char ch = chs[i];
                        for (char k = 'a'; k <= 'z'; ++k) {
                            chs[i] = k;
                            String newString = String.valueOf(chs);
                            if (wordSet.contains(newString)) {
                                queue.offer(new ArrayList<>(currList) {{
                                    add(newString);
                                }});
                            }
                        }
                        chs[i] = ch;
                    }
                }
                wordSet.removeAll(currVisited); // Remove visited words
            }
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int min = Integer.MAX_VALUE;

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordSet) {
        List<List<String>> res = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        path.addLast(beginWord);
        if (wordSet.contains(endWord)) {
            helper(endWord, wordSet, res, path, 0);
        }
        return res;
    }

    private void helper(String endWord, Set<String> wordSet, List<List<String>> res, LinkedList<String> path, int step) {
        if (step > min) {
            return;
        }
        String currWord = path.getLast();
        if (currWord.equals(endWord) && step <= min) {
            if (step < min) {
                min = step;
                res.clear();
            }
            res.add(new ArrayList<>(path));
        } else {
            wordSet.remove(currWord);
            char[] chs = currWord.toCharArray();
            for (int i = 0; i < chs.length; ++i) {
                char ch = chs[i];
                for (char k = 'a'; k <= 'z'; ++k) {
                    chs[i] = k;
                    String newString = String.valueOf(chs);
                    if (wordSet.contains(newString)) {
                        path.addLast(newString);
                        helper(endWord, wordSet, res, path, step + 1);
                        path.removeLast();

                    }
                }
                chs[i] = ch; // Revert
            }
            wordSet.add(currWord);
        }
    }
}