package questions._10_tree.bst;

import common.TreeNode;

public class _1373_Maximum_Sum_BST_in_Binary_Tree {

    int max = 0;

    public int maxSumBST(TreeNode root) {
        postOrder(root);
        return max;
    }

    private int[] postOrder(TreeNode node) {
        int val = node.val;
        int[] newNode = { 1, val, val, val }; // { isBST, SUM, MIN, MAX }
        if (node.left != null) {
            int[] left = postOrder(node.left);
            newNode[0] = newNode[0] & left[0] & (node.val > left[3] ? 1 : 0);
            if (newNode[0] == 1) {
                newNode[2] = left[2];
                newNode[1] += left[1];
            }
        }

        if (node.right != null) {
            int[] right = postOrder(node.right);
            newNode[0] = newNode[0] & right[0] & (node.val < right[2] ? 1 : 0);
            if (newNode[0] == 1) {
                newNode[3] = right[3];
                newNode[1] += right[1];
            }
        }

        if (newNode[0] == 1) {
            max = Math.max(max, newNode[1]);
        }
        return newNode;
    }


    // Alternatively
    private static class Node {
        boolean isBST = true;
        int sum;
        int min;
        int max;

        public Node(TreeNode node) {
            sum = node.val;
            min = node.val;
            max = node.val;
        }
    }
}