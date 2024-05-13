package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Example 1:
    Input: root = [1,null,2,3]
    Output: [1,3,2]
Example 2:
    Input: root = []
    Output: []
Example 3:
    Input: root = [1]
    Output: [1]


Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100


Follow up: Recursive solution is trivial, could you do it iteratively?

*/
public class _0094_Binary_Tree_Inorder_Traversal {

    // recursive
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            inorderTraversal(root, result);
            return result;
        }

        private void inorderTraversal(TreeNode node, List<Integer> list) {
            if (node != null) {
                inorderTraversal(node.left, list);
                list.add(node.val);
                inorderTraversal(node.right, list);
            }
        }
    }

    class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.offerLast(root);
            while (!stack.isEmpty()) {
                TreeNode last = stack.getLast();
                if (last.left != null) {
                    stack.offerLast(last.left);
                    last.left = null;
                } else {
                    TreeNode next = stack.pollLast();
                    result.add(next.val);
                    if (next.right != null) {
                        stack.offerLast(next.right);
                        next.right = null;
                    }
                }
            }
            return result;
        }
    }
}
