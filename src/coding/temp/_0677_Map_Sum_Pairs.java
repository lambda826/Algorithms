/**
 *  @author Yunxiang He
 *  @date 2018-07-22 17:29
 */

package coding.temp;

import java.util.HashMap;
import java.util.Map;

/*

Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). 
    The string represents the key and the integer represents the value. 
    If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.


Example 1:
    Input: insert("apple", 3), Output: Null
    Input: sum("ap"), Output: 3
    Input: insert("app", 2), Output: Null
    Input: sum("ap"), Output: 5

*/

public class _0677_Map_Sum_Pairs {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class MapSum_Trie {
        private Trie root = new Trie();

        private class Trie {
            Trie[] next = new Trie[26];
            int value = 0;
        }

        public int sum(String prefix) {
            return sumAux(get(prefix));
        }

        private int sumAux(Trie node) {
            if (node == null) {
                return 0;
            } else {
                int sum = node.value;
                for (int i = 0; i < node.next.length; i++) {
                    sum += sumAux(node.next[i]);
                }
                return sum;
            }
        }

        private Trie get(String key) {
            Trie temp = root;
            for (int i = 0; i < key.length() && temp != null; i++) {
                temp = temp.next[key.charAt(i) - 'a'];
            }
            return temp;
        }

        public void insert(String key, int val) {
            Trie temp = root;
            int index;
            for (int i = 0; i < key.length(); i++) {
                index = key.charAt(i) - 'a';
                if (temp.next[index] == null) {
                    temp.next[index] = new Trie();
                }
                temp = temp.next[index];
            }
            temp.value = val;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class MapSum_Hash {
        Map<String, Integer> map;

        public MapSum_Hash() {
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int sum = 0;
            for (String key : map.keySet()) {
                if (key.startsWith(prefix)) {
                    sum += map.get(key);
                }
            }
            return sum;
        }
    }

}
