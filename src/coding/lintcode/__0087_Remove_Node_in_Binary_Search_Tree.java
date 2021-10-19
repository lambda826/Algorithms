/**
 *  @author Yunxiang He
 *  @date 02/19/2019
 */

package coding.lintcode;

import common.TreeNode;

/*

Given a root of Binary Search Tree with unique value for each node. Remove the node with given value. 
If there is no such a node with given value in the binary search tree, do nothing. 
You should keep the tree still a binary search tree after removal.


Example
    Given binary search tree:
        5
       / \
      3   6
     / \
    2   4
    Remove 3, you can either return:
    
        5
       / \
      2   6
       \
        4
    or
    
        5
       / \
      4   6
     /
    2

*/

public class __0087_Remove_Node_in_Binary_Search_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode removeNode(TreeNode root, int value) {
        if (root != null) {
            if (root.val > value) {
                root.left = removeNode(root.left, value);
            } else if (root.val < value) {
                root.right = removeNode(root.right, value);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    TreeNode leftMax = findMax(root.right);
                    leftMax.left = root.left;
                    return root.right;
                }
            }
        }
        return root;
    }

    private TreeNode findMax(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
