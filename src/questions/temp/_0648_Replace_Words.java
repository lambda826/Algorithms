/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-25 11:35
 */

package questions.temp;

import java.util.Arrays;
import java.util.List;

/*

In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. 
For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. 
You need to replace all the successor in the sentence with the root forming it. 
If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.


Example 1:
    Input: dict = ["cat", "bat", "rat"]
    sentence = "the cattle was rattled by the battery"
    Output: "the cat was rat by the bat"


Note:
    The input will only have lower-case letters.
    1 <= dict words number <= 1000
    1 <= sentence words number <= 1000
    1 <= root length <= 100
    1 <= sentence words length <= 1000

*/

public class _0648_Replace_Words {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class Node {
        Node[] next = new Node[26];
        boolean hasWord = false;
    }

    public String replaceWords_Trie(List<String> dict, String sentence) {
        // Build
        Node root = new Node();
        for (String word : dict) {
            Node temp = root;
            for (char ch : word.toCharArray()) {
                if (temp.next[ch - 'a'] == null) {
                    temp.next[ch - 'a'] = new Node();
                }
                temp = temp.next[ch - 'a'];
            }
            temp.hasWord = true;
        }
        // Search
        StringBuilder res = new StringBuilder();
        StringBuilder prefix;
        Node temp;
        outter:
        for (String word : sentence.split(" ")) {
            prefix = new StringBuilder();
            temp = root;
            for (char ch : word.toCharArray()) {
                temp = temp.next[ch - 'a'];
                if (temp == null) {
                    break;
                } else {
                    prefix.append(ch);
                    if (temp.hasWord) {
                        res.append(prefix).append(" ");
                        continue outter;
                    }
                }
            }
            res.append(word).append(" ");
        }
        return res.deleteCharAt(res.length() - 1).toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String replaceWords(List<String> dict, String sentence) {
        StringBuilder sb = new StringBuilder();
        for (String word : sentence.split(" ")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); i++) {
                prefix = word.substring(0, i);
                if (dict.contains(prefix)) {
                    break;
                }
            }
            sb.append(" " + prefix);
        }
        return sb.deleteCharAt(0).toString();
    }

    public static void main(String[] args) {
        _0648_Replace_Words test = new _0648_Replace_Words();
        String res = test.replaceWords_Trie(Arrays.asList(new String[] { "cat", "bat", "rat" }), "the cattle was rattled by the battery");
        System.out.println(res);
    }
}
