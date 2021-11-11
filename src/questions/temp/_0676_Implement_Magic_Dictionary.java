/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-23 13:32
 */

package questions.temp;

import java.util.HashSet;
import java.util.Set;

/*

Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.


Example 1:
    Input: buildDict(["hello", "leetcode"]), Output: Null
    Input: search("hello"), Output: False
    Input: search("hhllo"), Output: True
    Input: search("hell"), Output: False
    Input: search("leetcoded"), Output: False


Note:
    You may assume that all the inputs are consist of lowercase letters a-z.
    For contest purpose, the test data is rather small by now. 
    You could think about highly efficient algorithm after the contest.
    Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. 
    Please see here for more details.

*/

public class _0676_Implement_Magic_Dictionary {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Common part
    class Trie {

        Node root;

        class Node {
            Node[] next = new Node[26];
            boolean hasWord;
        }

        public Trie() {
            root = new Node();
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                Node temp = root;
                int index;
                for (int i = 0; i < word.length(); i++) {
                    index = word.charAt(i) - 'a';
                    if (temp.next[index] == null) {
                        temp.next[index] = new Node();
                    }
                    temp = temp.next[index];
                }
                temp.hasWord = true;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class MagicDictionary_Trie extends Trie {

        public boolean search(String word) {
            StringBuilder sb = new StringBuilder(word);
            char ch;
            for (int i = 0; i < word.length(); i++) {
                ch = word.charAt(i);
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j != ch) {
                        sb.setCharAt(i, j);
                        if (get(sb)) {
                            return true;
                        }
                    }
                }
                sb.setCharAt(i, ch);
            }
            return false;
        }

        private boolean get(StringBuilder key) {
            Node temp = root;
            for (int i = 0; i < key.length() && temp != null; i++) {
                temp = temp.next[key.charAt(i) - 'a'];
            }
            return temp != null && temp.hasWord;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class MagicDictionary_DFS extends Trie {

        boolean isFound;

        public boolean search(String word) {
            isFound = false;
            DFS(root, word, 0, false);
            return isFound;
        }

        private void DFS(Node node, String word, int d, boolean flag) {
            if (!isFound) {
                if (flag && d == word.length() && node.hasWord) {
                    isFound = true;
                } else if (d < word.length()) {
                    char ch = word.charAt(d);
                    for (char i = 'a'; i <= 'z'; i++) {
                        if (node.next[i - 'a'] != null) {
                            if (i != ch && !flag) {
                                DFS(node.next[i - 'a'], word, d + 1, true);
                            } else if (node.next[ch - 'a'] != null) {
                                DFS(node.next[ch - 'a'], word, d + 1, flag);
                            }
                        }
                    }
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class MagicDictionary_Hashing {

        private Set<String> set = new HashSet<>();

        public void buildDict(String[] dict) {
            for (String word : dict) {
                set.add(word);
            }
        }

        public boolean search(String word) {
            char temp;
            char[] chz = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                temp = chz[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch != temp) {
                        chz[i] = ch;
                        if (set.contains(new String(chz))) {
                            return true;
                        }
                        chz[i] = temp;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        _0676_Implement_Magic_Dictionary.MagicDictionary_DFS test = new _0676_Implement_Magic_Dictionary().new MagicDictionary_DFS();
        test.buildDict(new String[] { "hello", "judge" });
        System.out.println(test.search("judge"));
    }

}
