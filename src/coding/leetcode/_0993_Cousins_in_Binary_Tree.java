package coding.leetcode;

/*

In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
Return true if and only if the nodes corresponding to the values x and y are cousins.


Example 1:
    Input: root = [1,2,3,4], x = 4, y = 3
    Output: false

Example 2:
    Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
    Output: true

Example 3:
    Input: root = [1,2,3,null,4], x = 2, y = 3
    Output: false


Note:
    The number of nodes in the tree will be between 2 and 100.
    Each node has a unique integer value from 1 to 100.

*/

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _0993_Cousins_in_Binary_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isCousins_BFS(TreeNode root, int x, int y) {
        if (root != null) {
            TreeNode px = null;
            TreeNode py = null;
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            TreeNode node;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    node = queue.poll();
                    if (node.left != null) {
                        if (node.left.val == x) {
                            px = node;
                        }
                        if (node.left.val == y) {
                            py = node;
                        }
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        if (node.right.val == x) {
                            px = node;
                        }
                        if (node.right.val == y) {
                            py = node;
                        }
                        queue.offer(node.right);
                    }
                }
                if (px != null && py != null) {
                    return px != py;
                }
                if (px != py) {
                    return false;
                }
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Integer dx = 0;
    Integer dy = 0;
    TreeNode px = null;
    TreeNode py = null;

    public boolean isCousins_DFS(TreeNode root, int x, int y) {
        DFS(null, root, x, y, 0);
        if (px == py) {
            return false;
        } else if (dx.equals(dy)) {
            return true;
        } else {
            return false;
        }
    }

    private void DFS(TreeNode pre, TreeNode curr, int x, int y, int d) {
        if (curr != null) {
            if (curr.val == x) {
                px = pre;
                dx = d;
            }
            if (curr.val == y) {
                py = pre;
                dy = d;
            }
            DFS(curr, curr.left, x, y, d + 1);
            DFS(curr, curr.right, x, y, d + 1);
        }
    }
}
