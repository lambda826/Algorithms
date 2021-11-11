/**
 *  @author Yunxiang He
 *  @date 03/09/2019
 */

package questions.lintcode;

/*

Implement a Trie with insert, search, and startsWith methods.


Example
    Example 1:
        Input:
          insert("lintcode")
          search("lint")
          startsWith("lint")
        Output:
          false
          true
    
    Example 2:
        Input:
          insert("lintcode")
          search("code")
          startsWith("lint")
          startsWith("linterror")
          insert("linterror")
          search("lintcode)
          startsWith("linterror")
        Output:
          false
          true
          false
          true
          true


Notice
    You may assume that all inputs are consist of lowercase letters a-z.

*/

public class __0442_Implement_Trie_Prefix_Tree {
    public class Trie {

        class Node {
            Node[] next = new Node[26];
            boolean hasWord = false;
        }

        private Node root;

        public Trie() {
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
            return temp != null && (temp.hasWord || temp.next != null);
        }

        private Node get(String word) {
            Node temp = root;
            for (char ch : word.toCharArray()) {
                if (temp == null) {
                    break;
                }
                temp = temp.next[ch - 'a'];
            }
            return temp;
        }
    }
}
