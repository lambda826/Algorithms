/**
 *  @author Yunxiang He
 *  @date 03/09/2019
 */

package coding.lintcode;

/*

Design a data structure that supports the following two operations: addWord(word) and search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or ..
A . means it can represent any one letter.


Example
    Example 1:
    Input:
      addWord("a")
      search(".")
    Output:
      true

Example 2:
    Input:
      addWord("bad")
      addWord("dad")
      addWord("mad")
      search("pad")  
      search("bad")  
      search(".ad")  
      search("b..")  
    Output:
      false
      true
      true
      true
  

Notice
    You may assume that all words are consist of lowercase letters a-z.

*/

public class __0473_Add_and_Search_Word {

    Node root = new Node();

    class Node {
        Node[] next = new Node[26];
        boolean hasWord = false;
    }

    public void addWord(String word) {
        Node temp = root;
        for (char ch : word.toCharArray()) {
            int next = ch - 'a';
            if (temp.next[next] == null) {
                temp.next[next] = new Node();
            }
            temp = temp.next[next];
        }
        temp.hasWord = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int i, Node temp) {
        if (i == word.length() && temp != null && temp.hasWord) {
            return true;
        } else if (i < word.length()) {
            if (temp == null) {
                return false;
            }
            char ch = word.charAt(i);
            if (ch == '.') {
                for (Node next : temp.next) {
                    if (search(word, i + 1, next)) {
                        return true;
                    }
                }
                return false;
            } else {
                return search(word, i + 1, temp.next[ch - 'a']);
            }
        } else {
            return false;
        }
    }
}
