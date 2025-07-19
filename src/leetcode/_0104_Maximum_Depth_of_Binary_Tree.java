package leetcode;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*

Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.


Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 3

Example 2:
    Input: root = [1,null,2]
    Output: 2


Constraints:
    The number of nodes in the tree is in the range [0, 104].
    -100 <= Node.val <= 100

*/
public class _0104_Maximum_Depth_of_Binary_Tree {

    class Solution {
        public int maxDepth(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode node, int depth) {
            if (node == null) {
                return depth;
            } else {
                return Math.max(dfs(node.left, depth + 1), dfs(node.right, depth + 1));
            }
        }
    }

    class Solution2 {
        public int maxDepth(TreeNode root) {
            int depth = 0;
            if (root != null) {
                Queue<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                while (!queue.isEmpty()) {
                    int levelSize = queue.size();
                    while (levelSize-- > 0) {
                        TreeNode t = queue.poll();
                        if (t.left != null) {
                            queue.offer(t.left);
                        }
                        if (t.right != null) {
                            queue.offer(t.right);
                        }
                    }
                    ++depth;
                }
            }
            return depth;
        }
    }
}