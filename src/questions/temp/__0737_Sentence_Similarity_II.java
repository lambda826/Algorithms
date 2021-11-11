/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*

Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. 
For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. 
For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. 
For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. 
So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].


Note:
The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

*/

public class __0737_Sentence_Similarity_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean areSentencesSimilarTwo_UF(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, String> roots = new HashMap<>();
        for (String[] pair : pairs) {
            union(roots, pair[0], pair[1]);
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            String root1 = find(roots, words1[i]);
            String root2 = find(roots, words2[i]);
            if (!root1.equals(root2)) {
                return false;
            }
        }
        return true;

    }

    private String find(Map<String, String> roots, String word) {
        if (roots.get(word) == null) {
            roots.put(word, word);
            return word;
        }
        if (roots.get(word) == word) {
            return word;
        }
        return find(roots, roots.get(word));
    }

    private String find2(Map<String, String> roots, String word) {
        if (roots.get(word) == null) {
            roots.put(word, word);
            return word;
        }
        while (!word.equals(roots.get(word))) {
            roots.put(word, roots.get(roots.get(word)));
            word = roots.get(word);
        }
        return word;
    }

    private void union(Map<String, String> roots, String word1, String word2) {
        String root1 = find(roots, word1);
        String root2 = find(roots, word2);
        if (!root1.equals(root2)) {
            roots.put(root1, root2);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean areSentencesSimilarTwo_DFS(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, List<String>> graph = new HashMap<>();
        buildGraph(pairs, graph);
        for (int i = 0; i < words1.length; ++i) {
            String w1 = words1[i];
            String w2 = words2[i];
            Deque<String> deque = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            deque.addLast(w1);
            outter:
            {
                while (!deque.isEmpty()) {
                    String word = deque.removeLast();
                    if (!visited.contains(word)) {
                        visited.add(word);
                        if (word.equals(w2)) {
                            break outter;
                        } else if (graph.containsKey(word)) {
                            for (String nei : graph.get(word)) {
                                deque.addLast(nei);
                            }
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private void buildGraph(String[][] pairs, Map<String, List<String>> graph) {
        for (String[] pair : pairs) {
            if (!graph.containsKey(pair[0])) {
                graph.put(pair[0], new ArrayList<>());
            }
            if (!graph.containsKey(pair[1])) {
                graph.put(pair[1], new ArrayList<>());
            }
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
    }

    public static void main(String[] args) {
        __0737_Sentence_Similarity_II test = new __0737_Sentence_Similarity_II();
        String[] words1 = { "a", "very", "delicious", "meal" };
        String[] words2 = { "one", "really", "delicious", "dinner" };
        String[][] pairs = { { "great", "good" }, { "extraordinary", "good" }, { "well", "good" }, { "wonderful", "good" }, { "excellent", "good" }, { "fine", "good" }, { "nice", "good" }, { "any", "one" }, { "some", "one" }, { "unique", "one" },
                { "the", "one" }, { "an", "one" }, { "single", "one" }, { "a", "one" }, { "truck", "car" }, { "wagon", "car" }, { "automobile", "car" }, { "auto", "car" }, { "vehicle", "car" }, { "entertain", "have" }, { "drink", "have" },
                { "eat", "have" }, { "take", "have" }, { "fruits", "meal" }, { "brunch", "meal" }, { "breakfast", "meal" }, { "food", "meal" }, { "dinner", "meal" }, { "super", "meal" }, { "lunch", "meal" }, { "possess", "own" }, { "keep", "own" },
                { "have", "own" }, { "extremely", "very" }, { "actually", "very" }, { "really", "very" }, { "super", "very" } };
        boolean result = test.areSentencesSimilarTwo_UF(words1, words2, pairs);
        System.out.println(result);
    }
}
