/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-25 14:40
 */

package coding.temp;

/*

Implement a trie with insert, search, and startsWith methods.


Example:
    Trie trie = new Trie();
    trie.insert("apple");
    trie.search("apple");   // returns true
    trie.search("app");     // returns false
    trie.startsWith("app"); // returns true
    trie.insert("app");   
    trie.search("app");     // returns true


Note:
    You may assume that all inputs are consist of lowercase letters a-z.
    All inputs are guaranteed to be non-empty strings.

*/

public class _0208_Implement_Trie_Prefix_Tree {

    class Node {
        Node[] next = new Node[26];
        boolean hasWord = false;
    }

    private Node root;

    public _0208_Implement_Trie_Prefix_Tree() {
        root = new Node();
    }

    public void insert(String word) {
        Node temp = root;
        for (char ch : word.toCharArray()) {
            int nextIndex = ch - 'a';
            if (temp.next[nextIndex] == null) {
                temp.next[nextIndex] = new Node();
            }
            temp = temp.next[nextIndex];
        }
        temp.hasWord = true;
    }

    public boolean search(String word) {
        Node temp = get(word);
        return temp != null && temp.hasWord;
    }

    public boolean startsWith(String prefix) {
        Node temp = get(prefix);
        return temp != null && temp.hasWord && temp.next != null;
    }


    private Node get(String word) {
        Node temp = root;
        for (char ch : word.toCharArray()) {
            int nextIndex = ch - 'a';
            if (temp.next[nextIndex] == null) {
                break;
            }
            temp = temp.next[nextIndex];
        }
        return temp;
    }

}
