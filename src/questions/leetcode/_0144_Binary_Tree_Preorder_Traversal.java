package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Given the root of a binary tree, return the preorder traversal of its nodes' values.


Example 1:
    Input: root = [1,null,2,3]
    Output: [1,2,3]

Example 2:
    Input: root = []
    Output: []

Example 3:
    Input: root = [1]
    Output: [1]


Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100


Follow up:
    Recursive solution is trivial, could you do it iteratively?

*/
public class _0144_Binary_Tree_Preorder_Traversal {

    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            preorderTraversal(root, res);
            return res;
        }

        private void preorderTraversal(TreeNode node, List<Integer> res) {
            if (node != null) {
                res.add(node.val);
                preorderTraversal(node.left, res);
                preorderTraversal(node.right, res);
            }
        }
    }

    class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode curr = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (curr != null) {
                res.add(curr.val);
                if (curr.right != null) {
                    stack.offerLast(curr.right);
                }
                if (curr.left == null) {
                    curr = stack.pollLast();
                } else {
                    curr = curr.left;
                }
            }
            return res;
        }
    }

    class Solution3 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode curr = root;
            TreeNode rightMost;
            while (curr != null) {
                if (curr.left != null) {
                    rightMost = curr.left;
                    while (rightMost.right != null && rightMost.right != curr) {
                        rightMost = rightMost.right;
                    }
                    if (rightMost.right == null) {
                        res.add(curr.val);
                        rightMost.right = curr;
                        curr = curr.left;
                    } else {
                        curr = curr.right;
                        rightMost.right = null;
                    }
                } else {
                    res.add(curr.val);
                    curr = curr.right;
                }
            }
            return res;
        }
    }
}
