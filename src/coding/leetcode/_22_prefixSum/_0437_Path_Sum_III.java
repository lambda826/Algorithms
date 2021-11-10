package coding.leetcode._22_prefixSum;


import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*

Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).


Example 1:
    Input:
        root = [10,5,-3,3,2,null,11,3,-2,null,1],
        targetSum = 8
    Output:
        3
    Explanation:
        The paths that sum to 8 are shown.

Example 2:
    Input:
        root = [5,4,8,11,null,13,4,7,2,null,null,5,1],
        targetSum = 22
    Output:
        3


Constraints:
    The number of nodes in the tree is in the range [0, 1000].
    -10^9 <= Node.val <= 10^9
    -1000 <= targetSum <= 1000

*/
public class _0437_Path_Sum_III {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_PrefixSum {
        public int pathSum(TreeNode root, int targetSum) {
            Map<Integer, Integer> map = new HashMap<>();
            int[] count = { 0 };
            preOrder(root, 0, targetSum, map, count);
            return count[0];
        }

        private void preOrder(TreeNode node, int currentSum, int targetSum, Map<Integer, Integer> map, int[] count) {
            if (node != null) {
                currentSum += node.val;
                if (currentSum == targetSum) {
                    ++count[0];
                }
                count[0] += map.getOrDefault(currentSum - targetSum, 0);
                map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
                preOrder(node.left, currentSum, targetSum, map, count);
                preOrder(node.right, currentSum, targetSum, map, count);
                map.put(currentSum, map.get(currentSum) - 1);
            }
        }

    }
}