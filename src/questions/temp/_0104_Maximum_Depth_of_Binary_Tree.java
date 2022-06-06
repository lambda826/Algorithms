package questions.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.


Example:
    Given binary tree [3,9,20,null,null,15,7],
    
        3
       / \
      9  20
        /  \
       15   7
    return its depth = 3.


Note: 
    A leaf is a node with no children.


History:
    3/28/2020

*/

public class _0104_Maximum_Depth_of_Binary_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxDepth_DFS(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth_DFS(root.left), maxDepth_DFS(root.right));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxDepth_BFS(TreeNode root) {
        int depth = 0;
        if (root != null) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                depth++;
                int size = queue.size();
                while(size-- > 0) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return depth;
    }
}
