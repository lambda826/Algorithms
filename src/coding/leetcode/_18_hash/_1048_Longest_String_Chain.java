package coding.leetcode._18_hash;

/*

You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on.
A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.


Example 1:
    Input:
        words = ["a","b","ba","bca","bda","bdca"]
    Output:
        4
    Explanation:
        One of the longest word chains is ["a","ba","bda","bdca"].

Example 2:
    Input:
        words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
    Output:
        5
    Explanation:
        All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].

Example 3:
    Input: words = ["abcd","dbqca"]
    Output: 1
    Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
    ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.


Constraints:
    1 <= words.length <= 1000
    1 <= words[i].length <= 16
    words[i] only consists of lowercase English letters.

 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _1048_Longest_String_Chain {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Check each pair of words who have 1 difference in length
    class Solution1 {

        public int longestStrChain(String[] words) {
            Map<String, Integer> memo = new HashMap<>();
            Set<String>[] list = new Set[17];
            for (String word : words) {
                int len = word.length();
                if (list[len] == null) {
                    list[len] = new HashSet<>();
                }
                list[len].add(word);
                memo.put(word, 1);
            }

            int max = 1;
            for (int len = 1; len <= 16; ++len) {
                if (list[len] != null && list[len - 1] != null) {
                    for (String word2 : list[len]) {
                        for (String word1 : list[len - 1]) {
                            if (isPredecessor(word1, word2)) {
                                memo.put(word2, Math.max(memo.get(word2), (1 + memo.get(word1))));
                            }
                            if (memo.get(word2) == word2.length()) {
                                break;
                            }
                        }
                        max = Math.max(max, memo.get(word2));
                    }
                }
            }
            return max;
        }

        private boolean isPredecessor(String word1, String word2) {
            int i = 0;
            int j = 0;
            boolean flag = false;
            while (i < word1.length() && j < word2.length()) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    ++i;
                    ++j;
                } else if (flag) {
                    return false;
                } else {
                    flag = true;
                    ++j;
                }
            }
            return true;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utilize set to check whether the word is present after deleting a character
    class Solution2 {

        public int longestStrChain(String[] words) {
            Map<String, Integer> memo = new HashMap<>();
            Set<String>[] set = new Set[17];
            for (String word : words) {
                int len = word.length();
                if (set[len] == null) {
                    set[len] = new HashSet<>();
                }
                set[len].add(word);
                memo.put(word, 1);
            }
            int max = 1;
            for (int len = 1; len < 17; ++len) {
                if (set[len] != null && set[len - 1] != null) {
                    for (String word : set[len]) {
                        StringBuilder sb = new StringBuilder(word);
                        for (int pos = 0; pos < sb.length(); ++pos) {
                            sb.deleteCharAt(pos);
                            String newWord = sb.toString();
                            if (set[len - 1].contains(newWord)) {
                                memo.put(word, Math.max(memo.get(word), memo.get(newWord) + 1));
                            }

                            if (memo.get(word) == word.length()) {
                                break;
                            }
                            sb.insert(pos, word.charAt(pos));
                        }
                        max = Math.max(max, memo.get(word));
                    }
                }
            }
            return max;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Backtracking_Memo {

        public int longestStrChain(String[] words) {
            Set<String> wordSet = new HashSet<>();
            for (String word : words) {
                wordSet.add(word);
            }
            int max = 1;
            Map<String, Integer> memo = new HashMap<>();
            for (String word : wordSet) {
                max = Math.max(max, helper(wordSet, memo, word));
            }
            return max;
        }

        private int helper(Set<String> wordSet, Map<String, Integer> memo, String word) {
            if (word.length() == 0 || word.length() == 1) {
                return word.length();
            }
            if (memo.containsKey(word)) {
                return memo.get(word);
            }
            int max = 1;
            StringBuilder sb = new StringBuilder(word);
            for (int i = 0; i < sb.length(); ++i) {
                sb.deleteCharAt(i);
                String newWord = sb.toString();
                if (wordSet.contains(newWord)) {
                    max = Math.max(max, 1 + helper(wordSet, memo, newWord));
                }
                sb.insert(i, word.charAt(i));
            }
            memo.put(word, max);
            return max;
        }
    }

}