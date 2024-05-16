package questions.leetcode;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).


Example 1:
    Input: root = [1,2,2,3,4,4,3]
    Output: true

Example 2:
    Input: root = [1,2,2,null,3,null,3]
    Output: false


Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    -100 <= Node.val <= 100


Follow up: Could you solve it both recursively and iteratively?

*/
public class _0101_Symmetric_Tree {

    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return isSymmetric(root, root);
        }

        private boolean isSymmetric(TreeNode left, TreeNode right) {
            if (left != null && right != null) {
                return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
            } else {
                return left == null && right == null;
            }
        }
    }

    class Solution2 {
        public boolean isSymmetric(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode t1 = queue.poll();
                TreeNode t2 = queue.poll();
                if (t1 == null && t2 == null) {
                    continue;
                }
                if (t1 == null || t2 == null || t1.val != t2.val) {
                    return false;
                }
                queue.offer(t1.left);
                queue.offer(t2.right);
                queue.offer(t1.right);
                queue.offer(t2.left);
            }
            return true;
        }
    }

}
