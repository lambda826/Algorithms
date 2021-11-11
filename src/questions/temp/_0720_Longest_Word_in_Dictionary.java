/**
 *  @author Yunxiang He
 *  @date 2018-07-20 17:30
 */

package questions.temp;

import java.util.HashSet;
import java.util.Set;

/*

Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. 
If there is more than one possible answer, return the longest word with the smallest lexicographical order.
If there is no answer, return the empty string.


Example 1:
    Input: 
    words = ["w","wo","wor","worl", "world"]
    Output: "world"
    Explanation: 
    The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".

Example 2:
    Input: 
    words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
    Output: "apple"
    Explanation: 
    Both "apply" and "apple" can be built from other words in the dictionary. 
    However, "apple" is lexicographically smaller than "apply".


Note:
    All the strings in the input will only contain lowercase letters.
    The length of words will be in the range [1, 1000].
    The length of words[i] will be in the range [1, 30].

*/

public class _0720_Longest_Word_in_Dictionary {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class Node {
        Node[] next = new Node[26];
        boolean hasWord = false;
    }

    private Node root = new Node();
    private String result = "";

    public String longestWord_Trie(String[] words) {
        for (String word : words) {
            put(root, word);
        }
        StringBuilder sb = new StringBuilder();
        getLongest(root, sb);
        return result.toString();
    }

    private void put(Node root, String key) {
        Node temp = root;
        int index;
        for (int i = 0; i < key.length(); i++) {
            index = key.charAt(i) - 'a';
            if (temp.next[index] == null) {
                temp.next[index] = new Node();
            }
            temp = temp.next[index];
        }
        temp.hasWord = true;
    }

    private void getLongest(Node node, StringBuilder sb) {
        if (sb.length() > result.length()) {
            result = sb.toString();
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            Node nextNode = node.next[ch - 'a'];
            if (nextNode != null && nextNode.hasWord) {
                sb.append(ch);
                getLongest(nextNode, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String longestWord_Hash(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        String result = "";
        for (String word : words) {
            if (word.length() > result.length() || word.length() == result.length() && word.compareTo(result) < 0) {
                boolean flag = true;
                for (int i = 1; i < word.length(); i++) {
                    if (!set.contains(word.substring(0, i))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    result = word;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new _0720_Longest_Word_in_Dictionary().longestWord_Trie(new String[] { "w", "wo", "wor", "worl", "world" }));
    }
}
