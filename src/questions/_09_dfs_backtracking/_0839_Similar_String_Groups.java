package questions._09_dfs_backtracking;

/*

Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
Notice that "tars" and "arts" are in the same group even though they are not similar.
Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?


Example 1:
    Input:
        strs = ["tars",
                "rats",
                "arts",
                "star"]
    Output:
        2

Example 2:
    Input:
        strs = ["omv",
                "ovm"]
    Output:
        1


Constraints:
    1 <= strs.length <= 300
    1 <= strs[i].length <= 300
    strs[i] consists of lowercase letters only.
    All words in strs have the same length and are anagrams of each other.

*/
public class _0839_Similar_String_Groups {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Use DFS to find all connected strings given a string.
    class Solution_DFS {

        public int numSimilarGroups(String[] strs) {
            boolean[] found = new boolean[strs.length];
            int count = 0;
            for (int i = 0; i < strs.length; ++i) {
                if (!found[i]) {
                    ++count;
                    dfs(strs, found, i);
                }
            }
            return count;
        }

        private void dfs(String[] strs, boolean[] found, int i) {
            found[i] = true;
            for (int j = 0; j < strs.length; ++j) {
                if (!found[j] && isSimilar(strs[i], strs[j])) {
                    dfs(strs, found, j);
                }
            }
        }

        private boolean isSimilar(String str1, String str2) {
            int diff = 0;
            for (int i = 0; i < str1.length(); ++i) {
                if (str1.charAt(i) != str2.charAt(i) && ++diff > 2) {
                    return false;
                }
            }
            return true;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {

        public int numSimilarGroups(String[] strs) {
            int[] parent = new int[strs.length];
            int[] height = new int[strs.length];
            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
            }
            int n = parent.length;
            for (int i = 0; i < strs.length; ++i) {
                for (int j = i + 1; j < strs.length; ++j) {
                    if (isSimilar(strs[i], strs[j]) && union(parent, height, i, j)) {
                        --n;
                    }
                }
            }
            return n;
        }

        private boolean isSimilar(String str1, String str2) {
            int diff = 0;
            for (int i = 0; i < str1.length(); ++i) {
                if (str1.charAt(i) != str2.charAt(i) && ++diff > 2) {
                    return false;
                }
            }
            return true;
        }

        private int find(int[] parent, int i) {
            return parent[i] == i ? i : (parent[i] = find(parent, parent[i]));
        }

        private boolean union(int[] parent, int[] height, int i, int j) {
            int ii = find(parent, i);
            int jj = find(parent, j);
            if (ii != jj) {
                if (height[ii] < height[jj]) {
                    parent[ii] = jj;
                } else {
                    parent[jj] = ii;
                    if (height[ii] == height[jj]) {
                        ++height[ii];
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

}
