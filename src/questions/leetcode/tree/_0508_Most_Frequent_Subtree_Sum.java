package questions.leetcode.tree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.

The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).


Example 1:
    Input: root = [5,2,-3]
    Output: [2,-3,4]

Example 2:
    Input: root = [5,2,-5]
    Output: [2]


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -10^5 <= Node.val <= 10^5

*/
public class _0508_Most_Frequent_Subtree_Sum {

    class Solution {

        public int[] findFrequentTreeSum(TreeNode root) {
            Map<Integer, Integer> sumFreq = new HashMap<Integer, Integer>();
            dfs(root, sumFreq);
            int maxFreq = 0;
            for (Map.Entry<Integer, Integer> entry : sumFreq.entrySet()) {
                maxFreq = Math.max(maxFreq, entry.getValue());
            }
            List<Integer> res = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : sumFreq.entrySet()) {
                if (entry.getValue() == maxFreq) {
                    res.add(entry.getKey());
                }
            }
            return res.stream().mapToInt(i -> i).toArray();
        }

        private int dfs(TreeNode node, Map<Integer, Integer> sumFreq) {
            int sum = 0;
            if (node != null) {
                sum += dfs(node.left, sumFreq);
                sum += dfs(node.right, sumFreq);
                sum += node.val;
                sumFreq.put(sum, sumFreq.getOrDefault(sum, 0) + 1);
            }
            return sum;
        }

    }
}
