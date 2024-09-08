/**
 * @author Yunxiang He
 * @date 03/09/2019
 */

package questions.lintcode;

import java.util.NavigableMap;
import java.util.TreeMap;

/*

Serialize and deserialize a trie (prefix tree, search on internet for more details).

You can specify your own serialization algorithm, the online judge only cares about whether you can successfully deserialize the output from your own serialize function.


Example
    str = serialize(old_trie)
    >> str can be anything to represent a trie
    new_trie = deserialize(str)
    >> new_trie should have the same structure and values with old_trie
    An example of test data: trie tree <a<b<e<>>c<>d<f<>>>>, denote the following structure:
         root
          /
         a
       / | \
      b  c  d
     /       \
    e         f


Notice
    You don't have to serialize like the test data, you can design your own format.

*/

public class __0527_Trie_Serialization {

    public static void main(String[] args) {
        __0527_Trie_Serialization test = new __0527_Trie_Serialization();
        TrieNode root = new TrieNode();
        root.children.put('a', new TrieNode());
        root.children.get('a').children.put('b', new TrieNode());
        root.children.get('a').children.put('c', new TrieNode());
        root.children.get('a').children.put('d', new TrieNode());
        String data = test.serialize(root);
        test.deserialize(data);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String serialize(TrieNode root) {
        StringBuilder sb = new StringBuilder();
        if (root != null) {
            serialize(root, sb);
        }
        return sb.toString();
    }

    // dfs
    private void serialize(TrieNode node, StringBuilder sb) {
        sb.append('[');
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            if (node.children.get(ch) != null) {
                sb.append(ch);
                serialize(node.children.get(ch), sb);
            }
        }
        sb.append(']');
    }

    public TrieNode deserialize(String data) {
        TrieNode root = new TrieNode();
        deserialize(data, root, 0, data.length());
        return root;
    }

    private void deserialize(String sb, TrieNode node, int start, int end) {
        int count = 0;
        int j = start + 1;
        char letter = ' ';
        for (int i = start + 1; i < end - 1; ++i) {
            char ch = sb.charAt(i);
            if (ch == '[') {
                ++count;
            } else if (ch == ']') {
                --count;
            }
            if (count == 0) {
                if (i == j) {
                    node.children.put(ch, new TrieNode());
                    letter = ch;
                } else {
                    deserialize(sb, node.children.get(letter), j + 1, i + 1);
                    j = i + 1;
                }
            }
        }
    }
}

class TrieNode {

    public NavigableMap<Character, TrieNode> children;

    public TrieNode() {
        children = new TreeMap<Character, TrieNode>();
    }
}