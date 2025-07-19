package leetcode;

import common.TreeNode;

/*

Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:
    Input: root = [1,2,5,3,4,null,6]
    Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
    Input: root = []
    Output: []

Example 3:
    Input: root = [0]
    Output: [0]


Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -100 <= Node.val <= 100


Follow up:
    Can you flatten the tree in-place (with O(1) extra space)?

*/
public class _0114_Flatten_Binary_Tree_to_Linked_List {

    class Solution {

        /**
         * Traversal and Transformation:
         *
         * - We iterate through the tree using a while loop.
         * - For each node, if it has a left child, we find the rightmost node of the left subtree.
         * - We then connect the rightmost node's right pointer to the current node's right child.
         * - We update the current node's right pointer to its left child and set the left pointer to null.
         * - Move to the next node in the right.
         *
         * This process effectively "splices" the left subtree into the right subtree while maintaining the pre-order traversal order.
         */
        public void flatten(TreeNode root) {
            TreeNode curr = root;
            while (curr != null) {
                TreeNode left = curr.left;
                if (left != null) {
                    while (left.right != null) {
                        left = left.right;
                    }
                    left.right = curr.right;
                    curr.right = curr.left;
                    curr.left = null;
                }
                curr = curr.right;
            }
        }
    }

    class Solution2 {
        public void flatten(TreeNode root) {
            TreeNode[] pre = { new TreeNode() };
            flatten(pre, root);
            while (root != null) {
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }

        private void flatten(TreeNode[] pre, TreeNode curr) {
            if (curr != null) {
                pre[0].left = curr;
                pre[0] = curr;
                flatten(pre, curr.left);
                flatten(pre, curr.right);
            }
        }
    }

    class Solution3 {
        public void flatten(TreeNode root) {
            TreeNode[] pre = { new TreeNode() };
            flatten(pre, root);
        }

        private void flatten(TreeNode[] pre, TreeNode curr) {
            if (curr != null) {
                TreeNode left = curr.left;
                TreeNode right = curr.right;
                pre[0].right = curr;
                pre[0].left = null;
                pre[0] = curr;
                flatten(pre, left);
                flatten(pre, right);
            }
        }
    }

}
