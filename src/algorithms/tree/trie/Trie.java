package algorithms.tree.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie<Value> {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Basic
    private static final int R = 26;

    private static class Node {
        private Node[] next = new Node[R];
        private Object val;
    }

    private Node root = new Node();

    public Node get(String key) {
        Node temp = root;
        for (char ch : key.toCharArray()) {
            if (temp == null) {
                break;
            }
            temp = temp.next[ch - 'a'];
        }
        return temp;
    }

    public void put(String key, Value val) {
        Node temp = root;
        int nextIndex;
        for (char ch : key.toCharArray()) {
            nextIndex = ch - 'a';
            if (temp.next[nextIndex] == null) {
                temp.next[nextIndex] = new Node();
            }
            temp = temp.next[nextIndex];
        }
        temp.val = val;
    }

    // The first step needed to delete a key-value pair from a trie is to use a normal search to find the node corresponding to the key and set the corresponding value to null
    // If that node has a non-null link to a child, then no more work is required
    // If all the links are null, we need to remove the node from the data structure. 
    // If doing so leaves all the links null in its parent, we need to remove that node, and so forth
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node temp, String key, int d) {
        if (temp == null) {
            return null;
        }
        if (d == key.length()) {
            temp.val = null;
        } else {
            int nextIndex = key.charAt(d) - 'a';
            temp.next[nextIndex] = delete(temp.next[nextIndex], key, d + 1);
        }
        if (temp.val != null) {
            return temp;
        }
        for (char c = 0; c < R; c++) {
            if (temp.next[c] != null) {
                return temp;
            }
        }
        return null;
    }

    // Get the node of prefix string
    // DFS or BFS to collect_DFS
    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        Node node = get(prefix);
        if (node != null) {
            prefix_Collect_DFS(node, new StringBuilder(prefix), list);
        }
        return list;
    }

    private void prefix_Collect_DFS(Node node, StringBuilder prefix, List<String> list) {
        if (node != null) {
            if (node.val != null) {
                list.add(prefix.toString());
            }
            for (char nextChar = 'a'; nextChar <= 'z'; nextChar++) {
                prefix.append(nextChar);
                prefix_Collect_DFS(node.next[nextChar - 'a'], prefix, list);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    // All the keys in the symbol table that match that string
    // In the sense that a period (.) in the argument string matches any character
    public Iterable<String> keysThatMatch(String pattern) {
        List<String> list = new ArrayList<>();
        keysThatMatch_Collect_DFS(root, new StringBuilder(""), pattern, list);
        return list;
    }

    private void keysThatMatch_Collect_DFS(Node node, StringBuilder prefix, String pattern, List<String> list) {
        if (node != null) {
            if (prefix.length() == pattern.length() && node.val != null) {
                list.add(prefix.toString());
            } else {
                char nextChar = pattern.charAt(prefix.length());
                prefix.append(nextChar);
                if (nextChar == '.') {
                    for (nextChar = 0; nextChar < R; nextChar++) {
                        keysThatMatch_Collect_DFS(node.next[nextChar], prefix, pattern, list);
                    }
                } else {
                    keysThatMatch_Collect_DFS(node.next[nextChar - 'a'], prefix, pattern, list);
                }
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    // The longest prefix of the string
    public String longestPrefixOf(String s) {
        if (s == null) {
            throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        }
        int length = longestPrefixOf(root, s, 0, -1);
        if (length == -1) {
            return null;
        } else {
            return s.substring(0, length);
        }
    }

    // Returns the length of the longest string key in the subtrie rooted at node that is a prefix of the query string,
    // Assuming the first charIndex character match and we have already found a prefix match of given length (-1 if no such match)
    private int longestPrefixOf(Node node, String query, int charIndex, int length) {
        if (node == null) {
            return length;
        } else {
            if (node.val != null) {
                length = charIndex;
            }
            if (charIndex == query.length()) {
                return length;
            }
            return longestPrefixOf(node.next[query.charAt(charIndex)], query, charIndex + 1, length);
        }
    }

    public int size() {
        return size_DFS(root);
    }

    private int size_DFS(Node node) {
        int cnt = 0;
        if (node != null) {
            if (node.val != null) {
                cnt++;
            }
            for (char c = 0; c < R; c++) {
                cnt += size_DFS(node.next[c]);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Trie<String> trie = new Trie<>();
        String[] strings = { "sea", "shell", "shells", "see", "suc" };
        for (int i = 0; i < strings.length; i++) {
            trie.put(strings[i], strings[i]);
        }
        System.out.println(trie.keysWithPrefix("se"));
        System.out.println(trie.keysThatMatch("se"));
    }
}
