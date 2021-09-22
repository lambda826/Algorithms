/**
 *  @author Yunxiang He
 *  @date 08/06/2018
 */

package coding.leetcode.temp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*

Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  
Notice that "tars" and "arts" are in the same group even though they are not similar.  
Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  
Every string in A is an anagram of every other string in A.  
How many groups are there?


Example 1:
    Input: ["tars","rats","arts","star"]
    Output: 2


Note:
    A.length <= 2000
    A[i].length <= 1000
    A.length * A[i].length <= 20000
    All words in A consist of lowercase letters only.
    All words in A have the same length and are anagrams of each other.
    The judging time limit has been increased for this question.

*/

public class _0839_Similar_String_Groups {

    public static void main(String[] args) {
        System.out.println(new _0839_Similar_String_Groups().numSimilarGroups_DFS(new String[] { "aaaa", "aaaa", "aaaa", "aaaa" }));
    }

    private boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length() && count < 3; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count < 3;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Same words should be in the same group
    // Initially, every distinct word belongs to a single group
    // If two words can union, the # of group -1
    public int numSimilarGroups_UF(String[] A) {
        Set<String> set = new HashSet<>(Arrays.asList(A));
        A = new String[set.size()];
        int k = 0;
        for (String s : set) {
            A[k++] = s;
        }
        int[] roots = new int[A.length];
        int[] size = new int[A.length];
        for (int i = 0; i < A.length; ++i) {
            roots[i] = i;
            size[i] = 1;
        }
        int count = A.length;
        for (int i = 0; i < A.length; ++i) {
            for (int j = i + 1; j < A.length; ++j) {
                if (isSimilar(A[i], A[j])) {
                    if (union(roots, size, i, j)) {
                        count--;
                    }
                }
            }
        }
        return count;
    }

    private int find(int[] roots, int index) {
        if (roots[index] == index) {
            return index;
        }
        return roots[index] = find(roots, roots[index]);
    }

    private boolean union(int[] roots, int[] size, int index1, int index2) {
        int r1 = find(roots, index1);
        int r2 = find(roots, index2);
        if (r1 != r2) {
            if (size[r1] < size[r2]) {
                roots[r1] = r2;
                size[r2] += size[r1];
            } else {
                roots[r2] = r1;
                size[r1] += size[r2];
            }
            return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numSimilarGroups_DFS(String[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            String s = A[i];
            if (s != null) {
                A[i] = null;
                count++;
                DFS(A, s);
            }
        }
        return count;
    }

    private void DFS(String[] A, String s) {
        for (int i = 0; i < A.length; i++) {
            String t = A[i];
            if (t != null) {
                if (isSimilar(s, t)) {
                    A[i] = null;
                    DFS(A, t);
                }
            }
        }
    }
}
