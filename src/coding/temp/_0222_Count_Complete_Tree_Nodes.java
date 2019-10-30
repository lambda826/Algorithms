/**
 *  @author Yunxiang He
 *  @date 05/05/2018
 */

package coding.temp;

import common.TreeNode;

/*

Given a complete binary tree, count the number of nodes.


Example:
    Input: 
        1
       / \
      2   3
     / \  /
    4  5 6
    Output: 6

 */

public class _0222_Count_Complete_Tree_Nodes {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find whether the tree has the mid node of the last layer
    public int countNodes(TreeNode root) {
        TreeNode tmp = root;
        int ht = 0;
        while (tmp != null) {
            ++ht;
            tmp = tmp.left;
        }
        return count(root, ht - 1);
    }

    private int count(TreeNode node, int ht) {
        if (node == null) {
            return 0;
        } else if (node.left == null) {
            return 1;
        } else if (hasMid(node.left, ht)) {
            return (1 << ht) + count(node.right, ht - 1);
        } else {
            return (1 << (ht - 1)) + count(node.left, ht - 1);
        }
    }

    private boolean hasMid(TreeNode node, int ht) {
        while (node != null) {
            node = node.right;
            --ht;
        }
        return ht == 0;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Binary search
    // Find the height of the tree
    // The node is between [2 ^ (h - 1), 2 ^ h - 1]
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = 0;
        TreeNode tmp = root;
        while (tmp != null) {
            ++height;
            tmp = tmp.left;
        }
        int lo = 1 << (height - 1);
        int hi = (1 << height) - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isExist(mid, root)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return hi;
    }

    private boolean isExist(int mid, TreeNode root) {
        String digits = Integer.toBinaryString(mid);
        for (int i = 1; i < digits.length(); i++) {
            if (digits.charAt(i) == '0') {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root != null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // O(n)
    public int countNodes3(TreeNode root) {
        return DFS(root);
    }

    private int DFS(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + DFS(node.left) + DFS(node.right);
        }
    }
}
