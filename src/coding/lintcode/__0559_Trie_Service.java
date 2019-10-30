/**
 *  @author Yunxiang He
 *  @date 03/09/2019
 */

package coding.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/*

Build tries from a list of <word, freq> pairs. Save top 10 for each node.


Example
    Example1
        Input:  
         <"abc", 2>
         <"ac", 4>
         <"ab", 9>
        Output:<a[9,4,2]<b[9,2]<c[2]<>>c[4]<>>> 
        Explanation:
                    Root
                     / 
                   a(9,4,2)
                  /    \
                b(9,2) c(4)
               /
             c(2)
    Example2
        Input:  
        <"a", 10>
        <"c", 41>
        <"b", 50>
        <"abc", 5>
        Output: <a[10,5]<b[5]<c[5]<>>>b[50]<>c[41]<>>

*/

public class __0559_Trie_Service {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class TrieNode {
        public NavigableMap<Character, TrieNode> children;
        public List<Integer> top10;

        public TrieNode() {
            children = new TreeMap<Character, TrieNode>();
            top10 = new ArrayList<Integer>();
        }
    }

    private TrieNode root = null;

    public __0559_Trie_Service() {
        root = new TrieNode();
    }

    public void insert(String word, int frequency) {
        TrieNode temp = root;
        for (char ch : word.toCharArray()) {
            temp.children.putIfAbsent(ch, new TrieNode());
            temp = temp.children.get(ch);
            insertSort(temp.top10, frequency);
        }
    }

    private void insertSort(List<Integer> top10, int frequency) {
        top10.add(frequency);
        int i = top10.size() - 1;
        while (i > 0 && frequency > top10.get(i - 1)) {
            --i;
        }
        int j = top10.size() - 1;
        while (j > i) {
            top10.set(j, top10.get(j - 1));
            --j;
        }
        top10.set(i, frequency);
        if (top10.size() > 10) {
            top10.remove(top10.size() - 1);
        }
    }
}
