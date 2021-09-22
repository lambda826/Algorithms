package coding.leetcode._10_tree.bst;

/*



 */

import common.TreeNode;

public class _0333_Largest_BST_Subtree {
    int max = 0;

    public int largestBSTSubtree(TreeNode root) {
        if (root != null) {
            postOrder(root);
        }
        return max;
    }

    private int[] postOrder(TreeNode node) {
        int[] newNode = { 1, node.val, node.val }; // bst/num, min, max
        if (node.left != null) {
            int[] left = postOrder(node.left);
            if (left[0] > 0 && node.val > left[2]) {
                newNode[1] = left[1];
                newNode[0] += left[0];
            } else {
                newNode[0] = -1;
            }
        }

        if (node.right != null) {
            int[] right = postOrder(node.right);
            if (right[0] > 0 && node.val < right[1]) {
                newNode[2] = right[2];
                newNode[0] += right[0];
            } else {
                newNode[0] = -1;
            }
        }

        if (newNode[0] > 0) {
            max = Math.max(max, newNode[0]);
        }
        return newNode;
    }
}
