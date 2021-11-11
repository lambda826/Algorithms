/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions._10_tree.bst;

import common.TreeNode;

/*

Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.


Example :
    Input: root = [4,2,6,1,3,null,null]
    Output: 1
    Explanation:
    Note that root is a TreeNode object, not an array.
    
    The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
    
              4
            /   \
          2      6
         / \    
        1   3  
    
    while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.


Note:
    The size of the BST will be between 2 and 100.
    The BST is always valid, each node's value is an integer, and each node's value is different.

*/

public class _0783_Minimum_Distance_Between_BST_Nodes {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int min = Integer.MAX_VALUE;
    private Integer pre = null;

    public int minDiffInBST_InOrder(TreeNode root) {
        if (root != null) {
            minDiffInBST_InOrder(root.left);
            if (pre != null) {
                min = Math.min(min, Math.abs(root.val - pre));
            }
            pre = root.val;
            minDiffInBST_InOrder(root.right);
        }
        return min;
    }

}
