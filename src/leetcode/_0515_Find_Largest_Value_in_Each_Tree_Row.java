package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).


Example 1:
    Input: root = [1,3,2,5,3,null,9]
    Output: [1,3,9]
Example 2:
    Input: root = [1,2,3]
    Output: [1,3]


Constraints:
    The number of nodes in the tree will be in the range [0, 10^4].
    -2^31 <= Node.val <= 2^31 - 1

*/
public class _0515_Find_Largest_Value_in_Each_Tree_Row {

    class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root != null) {
                Queue<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                while (!queue.isEmpty()) {
                    int levelSize = queue.size();
                    int maxVal = Integer.MIN_VALUE;
                    while (levelSize-- > 0) {
                        TreeNode node = queue.poll();
                        maxVal = Math.max(maxVal, node.val);
                        if (node.left != null) {
                            queue.offer(node.left);
                        }
                        if (node.right != null) {
                            queue.offer(node.right);
                        }
                    }
                    res.add(maxVal);
                }
            }
            return res;
        }
    }

    class Solution2 {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(root, res, 0);
            return res;
        }

        private void dfs(TreeNode node, List<Integer> res, int depth) {
            if (node != null) {
                if (res.size() == depth) {
                    res.add(node.val);
                } else {
                    res.set(depth, Math.max(res.get(depth), node.val));
                }
                dfs(node.left, res, depth + 1);
                dfs(node.right, res, depth + 1);
            }
        }
    }
}
