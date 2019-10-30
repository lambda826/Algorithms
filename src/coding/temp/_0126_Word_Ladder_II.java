/**
 *  @author Yunxiang He
 *  @date 02/20/2019
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    public static void main(String[] args) {
        System.out.println(new _0126_Word_Ladder_II().findLadders("red", "tax", Arrays.asList(new String[] { "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" })));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> list = new ArrayList<>();
        if (!wordSet.contains(endWord)) {
            return list;
        }
        Map<String, List<StringBuilder>> beginMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Set<String> tempVisit;
        List<StringBuilder> l1 = new ArrayList<>();
        l1.add(new StringBuilder(beginWord));
        beginMap.put(beginWord, l1);
        visited.add(beginWord);
        boolean isFound = false;
        while (!isFound && !beginMap.isEmpty()) {
            Map<String, List<StringBuilder>> temp = new HashMap<>();
            tempVisit = new HashSet<>();
            for (String word : beginMap.keySet()) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String newWord = String.valueOf(chs);
                        if (newWord.equals(endWord)) {
                            for (StringBuilder sb : beginMap.get(word)) {
                                list.add(new ArrayList<>(Arrays.asList(sb.append("|").append(endWord).toString().split("\\|"))));
                            }
                            isFound = true;
                        }
                        if (!visited.contains(newWord) && wordSet.contains(newWord)) {
                            temp.putIfAbsent(newWord, new ArrayList<>());
                            for (StringBuilder sb : beginMap.get(word)) {
                                temp.get(newWord).add(new StringBuilder(sb).append("|").append(newWord));
                            }
                            tempVisit.add(newWord);
                        }
                        chs[i] = old;
                    }
                }
            }
            visited.addAll(tempVisit);
            beginMap = new HashMap<>(temp);
            System.out.println(beginMap);
        }
        return list;
    }
}
