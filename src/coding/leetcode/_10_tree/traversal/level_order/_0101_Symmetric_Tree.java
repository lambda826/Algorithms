package coding.leetcode._10_tree.traversal.level_order;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).


For example, 
    this binary tree [1,2,2,3,4,4,3] is symmetric:
            1
           / \
          2   2
         / \ / \
        3  4 4  3
    
    But the following [1,2,2,null,3,null,3] is not:
            1
           / \
          2   2
           \   \
           3    3


Note:
    Bonus points if you could solve it both recursively and iteratively.


*/

public class _0101_Symmetric_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode n1, TreeNode n2) {
        if (n1 != null && n2 != null) {
            return n1.val == n2.val && isSymmetric(n1.left, n2.right) && isSymmetric(n1.right, n2.left);
        } else {
            return n1 == null && n2 == null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Solution 2: BFS
    public boolean isSymmetric_BFS(TreeNode root) {
        boolean isSymmetric = true;
        if (root != null) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node1 = queue.poll();
                TreeNode node2 = queue.poll();
                if (node1.val != node2.val
                        || !offer(node1.left, node2.right, queue)
                        || !offer(node1.right, node2.left, queue)) {
                    isSymmetric = false;
                    break;
                }
            }
        }
        return isSymmetric;
    }

    private boolean offer(TreeNode node1, TreeNode node2, Queue<TreeNode> queue) {
        if (node1 != null && node2 != null) {
            queue.offer(node1);
            queue.offer(node2);
            return true;
        }
        return node1 == node2;
    }

}
