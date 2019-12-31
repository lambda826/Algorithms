package coding.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSymmetric_BFS(TreeNode root) {
        if (root != null) {
            Queue<TreeNode> que = new ArrayDeque<>();
            que.offer(root);
            que.offer(root);
            while (!que.isEmpty()) {
                TreeNode n1 = que.poll();
                TreeNode n2 = que.poll();
                if (n1 == null && n2 == null) {
                    continue;
                } else if (n1 == null || n2 == null || n1.val != n2.val) {
                    return false;
                }
                que.offer(n1.left);
                que.offer(n2.right);
                que.offer(n1.right);
                que.offer(n2.left);
            }
        }
        return true;
    }

}
