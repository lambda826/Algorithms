package leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given the root of a binary tree, return the leftmost value in the last row of the tree.


Example 1:
    Input: root = [2,1,3]
    Output: 1
Example 2:
    Input: root = [1,2,3,4,null,5,6,null,null,7]
    Output: 7

Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -2^31 <= Node.val <= 2^31 - 1

*/
public class _0513_Find_Bottom_Left_Tree_Value {

    class Solution {
        public int findBottomLeftValue(TreeNode root) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            int res = root.val;
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                boolean first = true;
                while (levelSize-- > 0) {
                    TreeNode node = queue.poll();
                    if (first) {
                        res = node.val;
                        first = false;
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            return res;
        }
    }

    class Solution2 {
        public int findBottomLeftValue(TreeNode root) {
            int[] res = { 0 };
            dfs(root, 0, new int[] { -1 }, res);
            return res[0];
        }

        private void dfs(TreeNode node, int depth, int[] maxDepth, int[] res) {
            if (node != null) {
                if (depth > maxDepth[0]) {
                    res[0] = node.val;
                    maxDepth[0] = depth;
                }
                dfs(node.left, depth + 1, maxDepth, res);
                dfs(node.right, depth + 1, maxDepth, res);
            }
        }
    }
}
