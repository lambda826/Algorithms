/**
 *  @author Yunxiang He
 *  @date 01/06/2018
 */

package coding.temp;

import common.TreeNode;

/*

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the root.


Example 1:
    Input: [1,2,3]
    
           1
          / \
         2   3
    
    Output: 6

Example 2:
    Input: [-10,9,20,null,null,15,7]
    
       -10
       / \
      9  20
        /  \
       15   7
    Output: 42

*/

public class _0124_Binary_Tree_Maximum_Path_Sum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int max = Integer.MIN_VALUE;

    public int maxPathSum_Tree(TreeNode root) {
        max(root);
        return max;
    }

    private int max(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, max(node.left));
        int right = Math.max(0, max(node.right));
        max = Math.max(max, node.val + left + right);
        return node.val + Math.max(left, right);
    }
}
