/**
 *  @author Yunxiang He
 *  @date 02/14/2019
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
    Input: ["abcd","dcba","lls","s","sssll"]
    Output: [[0,1],[1,0],[3,2],[2,4]] 
    Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:
    Input: ["bat","tab","cat"]
    Output: [[0,1],[1,0]] 
    Explanation: The palindromes are ["battab","tabbat"]

*/

public class _0336_Palindrome_Pairs {

    public static void main(String[] args) {
        _0336_Palindrome_Pairs test = new _0336_Palindrome_Pairs();
        test.palindromePairs(new String[] { "", "a" });
    }

    private boolean isPalindrome(String str, int left, int right) {
        boolean isPalindrome = true;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Map to store the reversed words
    // Iterate the words, split the word into left and right halves
    // If the left half is palindrome, check whether the map contains the right half
    // If the right half is palindrome, check whether the map contains the left half
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words.length > 1) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < words.length; ++i) {
                map.put(new StringBuilder(words[i]).reverse().toString(), i);
            }
            for (int i = 0; i < words.length; ++i) {
                for (int j = 0; j <= words[i].length(); ++j) {
                    String left = words[i].substring(0, j);
                    String right = words[i].substring(j);
                    if (isPalindrome(left, 0, left.length() - 1)) {
                        if (map.containsKey(right) && map.get(right) != i) {
                            res.add(Arrays.asList(new Integer[] { map.get(right), i }));
                        }
                    }
                    if (isPalindrome(right, 0, right.length() - 1)) {
                        if (map.containsKey(left) && map.get(left) != i && right.length() != 0) {
                            res.add(Arrays.asList(new Integer[] { i, map.get(left) }));
                        }
                    }
                }
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Build trie with the reversed word
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        // Build the trie
        for (int i = 0; i < words.length; ++i) {
            put(new StringBuilder(words[i]).reverse().toString(), i);
        }
        for (int i = 0; i < words.length; ++i) {
            get(words[i], i, res);
        }
        return res;
    }

    private Node root = new Node();

    private class Node {
        Node[] next = new Node[26];
        int val = -1;
    }

    private void get(String str, int index, List<List<Integer>> res) {
        Node temp = root;
        // If the root is empty string ""
        if (temp.val >= 0 && temp.val != index && isPalindrome(str, 0, str.length() - 1)) {
            res.add(Arrays.asList(new Integer[] { index, temp.val }));
        }
        for (int i = 0; i < str.length(); ++i) {
            temp = temp.next[str.charAt(i) - 'a'];
            if (temp == null) {
                break;
            }
            if (temp.val >= 0 && temp.val != index && isPalindrome(str, i + 1, str.length() - 1)) {
                res.add(Arrays.asList(new Integer[] { index, temp.val }));
            }
        }
        if (temp != null) {
            DFS(temp, new StringBuilder(), index, res);
        }
    }

    private void DFS(Node node, StringBuilder path, int index, List<List<Integer>> res) {
        if (node.val != -1 && node.val != index && path.length() > 0 && isPalindrome(path.toString(), 0, path.length() - 1)) {
            res.add(Arrays.asList(new Integer[] { index, node.val }));
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            if (node.next[ch - 'a'] != null) {
                path.append(ch);
                DFS(node.next[ch - 'a'], path, index, res);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    private void put(String str, int index) {
        Node temp = root;
        int nextIndex;
        for (char ch : str.toCharArray()) {
            nextIndex = ch - 'a';
            if (temp.next[nextIndex] == null) {
                temp.next[nextIndex] = new Node();
            }
            temp = temp.next[nextIndex];
        }
        temp.val = index;
    }

}
