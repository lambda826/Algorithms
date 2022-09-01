package questions._20_unionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*

You are given a list of equivalent string pairs synonyms where synonyms[i] = [si, ti] indicates that si and ti are equivalent strings. You are also given a sentence text.
Return all possible synonymous sentences sorted lexicographically.


Example 1:
    Input:
        synonyms = [["happy","joy"],
                    ["sad","sorrow"],
                    ["joy","cheerful"]],
        text = "I am happy today but was sad yesterday"
    Output:
        ["I am cheerful today but was sad yesterday",
        "I am cheerful today but was sorrow yesterday",
        "I am happy today but was sad yesterday",
        "I am happy today but was sorrow yesterday",
        "I am joy today but was sad yesterday",
        "I am joy today but was sorrow yesterday"]

Example 2:
    Input:
        synonyms = [["happy","joy"],
                    ["cheerful","glad"]],
        text = "I am happy today but was sad yesterday"
    Output:
        ["I am happy today but was sad yesterday",
        "I am joy today but was sad yesterday"]


Constraints:
    0 <= synonyms.length <= 10
    synonyms[i].length == 2
    1 <= si.length, ti.length <= 10
    si != ti
    text consists of at most 10 words.
    All the pairs of synonyms are unique.
    The words of text are separated by single spaces.

*/
public class _1258_Synonymous_Sentences {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {

        public List<String> generateSentences(List<List<String>> synonyms, String text) {
            Map<String, String> parent = new HashMap<>();
            Set<String> wordSet = new HashSet<>();
            for (List<String> synonym : synonyms) {
                union(parent, synonym.get(0), synonym.get(1));
                wordSet.add(synonym.get(0));
                wordSet.add(synonym.get(1));
            }
            Map<String, List<String>> root2Comp = new HashMap<>();
            for (String word : wordSet) {
                String root = find(parent, word);
                root2Comp.putIfAbsent(root, new ArrayList<>());
                root2Comp.get(root).add(word);
            }
            String[] words = text.split(" ");
            List<String> res = new ArrayList<>();
            dfs(words, root2Comp, wordSet, 0, res, parent);
            Collections.sort(res);
            return res;
        }

        private void dfs(String[] words, Map<String, List<String>> root2Comp, Set<String> wordSet, int i, List<String> res, Map<String, String> parent) {
            if (i == words.length) {
                res.add(String.join(" ", words));
            }
            if (i < words.length) {
                if (wordSet.contains(words[i])) {
                    for (String word : root2Comp.get(find(parent, words[i]))) {
                        words[i] = word;
                        dfs(words, root2Comp, wordSet, i + 1, res, parent);
                    }
                } else {
                    dfs(words, root2Comp, wordSet, i + 1, res, parent);
                }
            }
        }


        private String find(Map<String, String> parent, String key) {
            if (!parent.containsKey(key)) {
                parent.put(key, key);
            }
            if (parent.get(key).equals(key)) {
                return key;
            }
            return parent.compute(key, (k, v) -> find(parent, parent.get(key)));
        }

        private void union(Map<String, String> parent, String k1, String k2) {
            String v1 = find(parent, k1);
            String v2 = find(parent, k2);
            if (!v1.equals(v2)) {
                parent.put(v1, v2);
            }
        }
    }
}
