package coding.leetcode._06_sorting;

/*

Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".


Example 1:
    Input:
        s = "abcde",
        words = ["a","bb","acd","ace"]
    Output:
        3
    Explanation:
        There are three strings in words that are a subsequence of s: "a", "acd", "ace".

Example 2:
    Input:
        s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
    Output:
        2


Constraints:
    1 <= s.length <= 5 * 10^4
    1 <= words.length <= 5000
    1 <= words[i].length <= 50
    s and words[i] consist of only lowercase English letters.

*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

public class _0792_Number_of_Matching_Subsequences {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_BucketSorting {

        private class Item {
            String word;
            int index;

            public Item(String word, int index) {
                this.word = word;
                this.index = index;
            }
        }

        public int numMatchingSubseq(String s, String[] words) {
            Deque<Item>[] buckets = new Deque[26];
            for (int i = 0; i < buckets.length; ++i) {
                buckets[i] = new ArrayDeque<>();
            }
            for (String word : words) {
                buckets[word.charAt(0) - 'a'].offerLast(new Item(word, 0));
            }
            int count = 0;
            for (char ch : s.toCharArray()) {
                if (buckets[ch - 'a'] != null) {
                    Deque<Item> queue = buckets[ch - 'a'];
                    int size = queue.size();
                    while (size-- > 0) {
                        Item item = queue.pollFirst();
                        if (item.word.charAt(item.index) == ch) {
                            item.index += 1;
                        }
                        if (item.index == item.word.length()) {
                            ++count;
                        } else {
                            buckets[item.word.charAt(item.index) - 'a'].offerLast(item);
                        }
                    }
                }
            }
            return count;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Sorting {
        public int numMatchingSubseq(String s, String[] words) {
            TreeSet<Integer>[] buckets = new TreeSet[26];
            for (int i = 0; i < s.length(); ++i) {
                int index = s.charAt(i) - 'a';
                if (buckets[index] == null) {
                    buckets[index] = new TreeSet<>();
                }
                buckets[index].add(i);
            }

            int count = 0;
            word:
            for (String word : words) {
                Integer lastIndex = -1;
                for (char ch : word.toCharArray()) {
                    int index = ch - 'a';
                    if (buckets[index] == null || buckets[index].size() == 0) {
                        continue word;
                    } else {
                        lastIndex = buckets[index].higher(lastIndex);
                        if (lastIndex == null) {
                            continue word;
                        }
                    }
                }
                ++count;
            }
            return count;
        }
    }
}