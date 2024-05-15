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
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode curr = root;
            while (!stack.isEmpty() || curr != null) {
                if (curr != null) {
                    stack.offerLast(curr);
                    curr = curr.left;
                } else {
                    curr = stack.pollLast();
                    result.add(curr.val);
                    curr = curr.right;
                }
            }
            return result;
        }
    }
}
