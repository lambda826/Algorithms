package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*

Given the root of a binary tree, return the postorder traversal of its nodes' values.


Example 1:
    Input: root = [1,null,2,3]
    Output: [3,2,1]

Example 2:
    Input: root = []
    Output: []

Example 3:
    Input: root = [1]
    Output: [1]


Constraints:
    The number of the nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

Follow up:
    Recursive solution is trivial, could you do it iteratively?

*/
public class _0145_Binary_Tree_Postorder_Traversal {

    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            postorderTraversal(root, res);
            return res;
        }

        private void postorderTraversal(TreeNode node, List<Integer> res) {
            if (node != null) {
                postorderTraversal(node.left, res);
                postorderTraversal(node.right, res);
                res.add(node.val);
            }
        }
    }

    class Solution2 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode curr = root; // Start with the root node
            TreeNode lastProcessed = root; // Initialize lastProcessed as the root node
            while (curr != null || !stack.isEmpty()) { // Loop until there are no more nodes to process
                // If the current node is a leaf or its children have been processed
                if (curr.left == null && curr.right == null
                    || curr.left == lastProcessed && curr.right == null // If left child is processed, it implies that either right is not processed, or it is null
                    || curr.right == lastProcessed) { // If right is processed, it implies all children has been processed
                    res.add(curr.val); // Add the current node's value to the result list
                    lastProcessed = curr; // Mark the current node as processed
                    curr = stack.pollLast(); // Move to the previous node in the stack
                } else if (curr.left != lastProcessed && curr.left != null) { // If the left child hasn't been processed, move to the left child
                    stack.offerLast(curr); // Push the current node onto the stack
                    curr = curr.left; // Move to the left child
                } else if (curr.right != lastProcessed && curr.right != null) { // If the right child hasn't been processed, move to the right child
                    stack.offerLast(curr); // Push the current node onto the stack
                    curr = curr.right; // Move to the right child
                }
            }
            return res;
        }
    }

    class Solution3 {
        public List<Integer> postorderTraversal(TreeNode root) {
            LinkedList<Integer> res = new LinkedList<>();
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                if (curr != null) {
                    res.addFirst(curr.val);
                    stack.offerLast(curr);
                    curr = curr.right;
                } else {
                    curr = stack.pollLast().left;
                }
            }
            return res;
        }
    }

    class Solution4 {
        public List<Integer> postorderTraversal(TreeNode root) {
            LinkedList<Integer> res = new LinkedList<>();
            TreeNode leftMost;
            // Traverse the tree using a modified Morris Traversal
            while (root != null) {
                // Check if the current node has a right child
                if (root.right != null) {
                    // Find the leftmost node in the right subtree
                    leftMost = root.right;
                    while (leftMost.left != null && leftMost.left != root) {
                        leftMost = leftMost.left;
                    }
                    // If the leftmost node's left child is null, we haven't visited this subtree yet
                    if (leftMost.left == null) {
                        res.addFirst(root.val); // Add the current node's value to the front of the result list (reverse order)
                        leftMost.left = root; // Create a temporary link from the leftmost node to the current node
                        root = root.right; // Move to the right child
                    } else {
                        // If the leftmost node's left child is the current node, remove the temporary link
                        root = root.left;
                        leftMost.left = null;
                    }
                } else {
                    // If the current node doesn't have a right child, add its value to the front of the result list
                    res.addFirst(root.val);
                    root = root.left;
                }
            }
            return res;
        }
    }
}
