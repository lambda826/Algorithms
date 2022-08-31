package questions._20_unionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
You can swap the characters at any pair of indices in the given pairs any number of times.
Return the lexicographically smallest string that s can be changed to after using the swaps.


Example 1:
    Input:
        s = "dcab",
        pairs = [[0,3],[1,2]]
    Output:
        "bacd"
    Explanation:
        Swap s[0] and s[3], s = "bcad"
        Swap s[1] and s[2], s = "bacd"

Example 2:
    Input:
        s = "dcab",
        pairs = [[0,3],[1,2],[0,2]]
    Output:
        "abcd"
    Explanation:
        Swap s[0] and s[3], s = "bcad"
        Swap s[0] and s[2], s = "acbd"
        Swap s[1] and s[2], s = "abcd"

Example 3:
    Input:
        s = "cba",
        pairs = [[0,1],[1,2]]
    Output:
        "abc"
    Explanation:
        Swap s[0] and s[1], s = "bca"
        Swap s[1] and s[2], s = "bac"
        Swap s[0] and s[1], s = "abc"


Constraints:
    1 <= s.length <= 10^5
    0 <= pairs.length <= 10^5
    0 <= pairs[i][0], pairs[i][1] < s.length
    s only contains lower case English letters.

*/
public class _1202_Smallest_String_With_Swaps {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int n = s.length();
            int[] parent = new int[n];
            int[] height = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
            for (List<Integer> pair : pairs) {
                union(parent, height, pair.get(0), pair.get(1));
            }

            Map<Integer, List<Integer>> root2Comp = new HashMap<>();
            Map<Integer, List<Character>> root2Chars = new HashMap<>();
            for (int i = 0; i < parent.length; ++i) {
                int root = find(parent, i);
                root2Comp.putIfAbsent(root, new ArrayList<>());
                root2Chars.putIfAbsent(root, new ArrayList<>());
                root2Comp.get(root).add(i);
                root2Chars.get(root).add(s.charAt(i));
            }

            char[] result = new char[n];
            for (int key : root2Comp.keySet()) {
                List<Integer> index = root2Comp.get(key);
                List<Character> chars = root2Chars.get(key);
                Collections.sort(chars);
                for (int i = 0; i < index.size(); ++i) {
                    result[index.get(i)] = chars.get(i);
                }
            }
            return String.valueOf(result);
        }

        private int find(int[] parent, int i) {
            return parent[i] == i ? i : (parent[i] = find(parent, parent[i]));
        }

        private void union(int[] parent, int[] height, int i, int j) {
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
            }
        }
    }
}
