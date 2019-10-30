/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-25 16:37
 */

package coding.temp;

/*

Design a data structure that supports the following two operations:
    void addWord(word)
    bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.


Example:
    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true


Note:
    You may assume that all words are consist of lowercase letters a-z.

*/

public class _0211_Add_and_Search_Word_Data_structure_design {

    class Node {
        Node[] next = new Node[26];
        boolean hasWord = false;
    }

    private Node root = new Node();

    public void addWord(String word) {
        Node temp = root;
        int nextIndex;
        for (char ch : word.toCharArray()) {
            nextIndex = ch - 'a';
            if (temp.next[nextIndex] == null) {
                temp.next[nextIndex] = new Node();
            }
            temp = temp.next[nextIndex];
        }
        temp.hasWord = true;
    }

    boolean isFound;

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        isFound = false;
        DFS(root, 0, word);
        return isFound;
    }

    private void DFS(Node node, int len, String word) {
        if (!isFound) {
            if (node != null) {
                if (len == word.length() && node.hasWord) {
                    isFound = true;
                } else if (len < word.length()) {
                    char ch = word.charAt(len);
                    if (ch == '.') {
                        for (ch = 'a'; ch <= 'z'; ch++) {
                            DFS(node.next[ch - 'a'], len + 1, word);
                        }
                    } else {
                        DFS(node.next[ch - 'a'], len + 1, word);
                    }
                }
            }
        }
    }

}
