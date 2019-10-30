/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.


Example:
    Given binary tree [3,9,20,null,null,15,7],
    
        3
       / \
      9  20
        /  \
       15   7
    return its minimum depth = 2.
    
*/

public class _0111_Minimum_Depth_of_Binary_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            if (left == 0) {
                return right + 1;
            } else if (right == 0) {
                return left + 1;
            } else {
                return 1 + Math.min(left, right);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minDepth_BFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);
        int depth = 0;
        while (!que.isEmpty()) {
            depth++;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = que.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    que.add(node.left);
                }
                if (node.right != null) {
                    que.add(node.right);
                }
            }
        }
        return depth;
    }

}
