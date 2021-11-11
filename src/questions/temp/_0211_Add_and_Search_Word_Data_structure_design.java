package questions.temp;

/*

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:
    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
    word may contain dots '.' where dots can be matched with any letter.


Example:
    Input
        ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    Output
        [null,null,null,null,false,true,true,true]
    Explanation
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad"); // return False
        wordDictionary.search("bad"); // return True
        wordDictionary.search(".ad"); // return True
        wordDictionary.search("b.."); // return True


Constraints:
    1 <= word.length <= 500
    word in addWord consists lower-case English letters.
    word in search consist of  '.' or lower-case English letters.
    At most 50000 calls will be made to addWord and search.

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
