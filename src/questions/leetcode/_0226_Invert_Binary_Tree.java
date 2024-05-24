package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given the root of a binary tree, invert the tree, and return its root.


Example 1:
    Input: root = [4,2,7,1,3,6,9]
    Output: [4,7,2,9,6,3,1]

Example 2:
    Input: root = [2,1,3]
    Output: [2,3,1]

Example 3:
    Input: root = []
    Output: []


Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

*/
public class _0226_Invert_Binary_Tree {

    class Solution {
        public TreeNode invertTree(TreeNode node) {
            if (node != null) {
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                invertTree(node.left);
                invertTree(node.right);
            }
            return node;
        }
    }

    class Solution2 {
        public TreeNode invertTree(TreeNode root) {
            if (root != null) {
                Deque<TreeNode> deque = new ArrayDeque<>();
                deque.offer(root);
                while (!deque.isEmpty()) {
                    TreeNode node = deque.poll();
                    TreeNode temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                    if (node.left != null) {
                        deque.offer(node.left);
                    }
                    if (node.right != null) {
                        deque.offer(node.right);
                    }
                }
            }
            return root;
        }
    }
}
