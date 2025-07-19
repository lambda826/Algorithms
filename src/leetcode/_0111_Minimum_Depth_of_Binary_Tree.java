package leetcode;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.


Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 2
Example 2:
    Input: root = [2,null,3,null,4,null,5,null,6]
    Output: 5


Constraints:
    The number of nodes in the tree is in the range [0, 10^5].

*/
public class _0111_Minimum_Depth_of_Binary_Tree {

    class Solution {
        public int minDepth(TreeNode root) {
            int minDepth = 0;
            if (root != null) {
                Queue<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                while (!queue.isEmpty()) {
                    minDepth++;
                    int levelSize = queue.size();
                    while (levelSize-- > 0) {
                        TreeNode t = queue.poll();
                        if (t.left == null && t.right == null) {
                            return minDepth;
                        }
                        if (t.left != null) {
                            queue.offer(t.left);
                        }
                        if (t.right != null) {
                            queue.offer(t.right);
                        }
                    }
                }
            }
            return minDepth;
        }
    }

    class Solution2 {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                int left = minDepth(root.left);
                int right = minDepth(root.right);
                if (left == 0) {
                    return 1 + right;
                } else if (right == 0) {
                    return 1 + left;
                } else {
                    return 1 + Math.min(left, right);
                }
            }
        }
    }
}
