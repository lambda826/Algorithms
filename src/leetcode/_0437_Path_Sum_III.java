package leetcode;


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

    class Solution {

        public int pathSum(TreeNode root, int targetSum) {
            return pathSum(root, targetSum, 0L, new HashMap<>());
        }

        private int pathSum(TreeNode node, int targetSum, long currSum, Map<Long, Integer> map) {
            int count = 0;
            if (node != null) {
                currSum += node.val;
                // case 1: currSum equals targetSum, meaning the pathSum from root to the current node equals target sum.
                if (currSum == targetSum) {
                    count = 1;
                }
                // case 2: Similar to 2-sum
                // currSum - targetSum is used to determine if there exists a sub-path (ending at the current node) that sums up to the targetSum.
                count += map.getOrDefault(currSum - targetSum, 0);
                // Update the map with the current sum
                map.put(currSum, map.getOrDefault(currSum, 0) + 1);
                count += pathSum(node.left, targetSum, currSum, map);
                count += pathSum(node.right, targetSum, currSum, map);
                // Decrement the current sum's count in the map to backtrack
                map.put(currSum, map.get(currSum) - 1);
            }
            return count;
        }
    }
}