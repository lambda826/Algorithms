package coding.leetcode.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.


Example:
    Given the below binary tree and sum = 22,

          5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1
    return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.


History:
    3/29/2020

*/

public class _0112_Path_Sum {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean hasPathSum_BackTracking(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return root.val == sum;
        } else {
            return hasPathSum_BackTracking(root.left, sum - root.val) || hasPathSum_BackTracking(root.right, sum - root.val);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean hasPathSum_DFS(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.removeLast();
            if (node.left == null && node.right == null && node.val == sum) {
                return true;
            }
            if (node.left != null) {
                node.left.val += node.val;
                deque.addLast(node.left);
            }
            if (node.right != null) {
                node.right.val += node.val;
                deque.addLast(node.right);
            }
        }
        return false;
    }
}
