/**
 *  @author: Yunxiang He
 *  @date  : 2018-08-02 02:43
 */

package questions.temp;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1

Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7

Note: You may assume the tree (i.e., the given root node) is not NULL.

*/

public class _0513_Find_Bottom_Left_Tree_Value {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findBottomLeftValue_BFS(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int leftMost = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = que.remove();
                if (i == 0) {
                    leftMost = node.val;
                }
                if (node.left != null) {
                    que.add(node.left);
                }
                if (node.right != null) {
                    que.add(node.right);
                }
            }
        }
        return leftMost;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int leftMost = 0;
    private int d = -1;

    public int findBottomLeftValue_DFS_Recursion(TreeNode root) {
        DFS(root, 0);
        return leftMost;
    }

    private void DFS(TreeNode node, int depth) {
        if (node != null) {
            if (depth > d) {
                leftMost = node.val;
                d = depth;
            }
            DFS(node.left, depth + 1);
            DFS(node.right, depth + 1);
        }
    }
}
