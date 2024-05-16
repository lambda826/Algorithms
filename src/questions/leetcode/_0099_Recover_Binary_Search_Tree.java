package questions.leetcode;

import common.TreeNode;

/*

You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.


Example 1:
    Input: root = [1,3,null,null,2]
    Output: [3,1,null,null,2]
    Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
Example 2:
    Input: root = [3,1,4,null,null,2]
    Output: [2,1,4,null,null,3]
    Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.


Constraints:
    The number of nodes in the tree is in the range [2, 1000].
    -2^31 <= Node.val <= 2^(31 - 1)


Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?

*/
public class _0099_Recover_Binary_Search_Tree {

    /**
     * To solve the problem of recovering a binary search tree (BST) where exactly two nodes were swapped by mistake,
     * you can use an in-order traversal to identify the two nodes and then swap them back.
     * <p>
     * Here's the step-by-step approach:
     * 1. In-order Traversal: Perform an in-order traversal of the BST. This should yield the node values in sorted order for a valid BST.
     * 2. Identify the Swapped Nodes: As you traverse, identify the two nodes that are out of order. There are two cases to consider:
     * 2.a. The two nodes are adjacent in the in-order sequence.
     * 2.b. The two nodes are not adjacent in the in-order sequence.
     * 3. Swap the Values: Once you have identified the two nodes, swap their values to recover the BST.
     */
    class Solution {

        private TreeNode firstElement = null;
        private TreeNode secondElement = null;
        private TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {
            // In-order traverse the tree and find the two nodes
            traverse(root);
            // Swap the values of the two nodes
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }

        private void traverse(TreeNode currElement) {
            if (currElement != null) {
                traverse(currElement.left);
                // Find the first element that is out of order
                if (firstElement == null && prevElement.val >= currElement.val) {
                    firstElement = prevElement;
                }
                // Find the second element that is out of order
                if (firstElement != null && prevElement.val >= currElement.val) {
                    secondElement = currElement;
                }
                prevElement = currElement;
                traverse(currElement.right);
            }
        }
    }

}
