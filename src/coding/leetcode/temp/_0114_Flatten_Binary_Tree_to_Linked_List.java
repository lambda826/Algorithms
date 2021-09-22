/**
 *  @author Yunxiang He
 *  @date 03/23/2018
 */

package coding.leetcode.temp;

import common.TreeNode;

/*

Given a binary tree, flatten it to a linked list in-place.


For example, given the following tree:
        1
       / \
      2   5
     / \   \
    3   4   6
    The flattened tree should look like:
    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6

*/

public class _0114_Flatten_Binary_Tree_to_Linked_List {

    public static void main(String[] args) {
        _0114_Flatten_Binary_Tree_to_Linked_List test = new _0114_Flatten_Binary_Tree_to_Linked_List();
        TreeNode root = TreeNode.array2Tree(new Integer[] { 1, 2, 5, 3, 4, null, 6 });
        test.flatten(root);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. append the left child of the root to the right, set left child to be null
    // 2. append the right child of the root to the right child of the preorder of the left subtree
    public void flatten(TreeNode root) {
        if (root != null) {
            preorder(root);
        }
    }

    private TreeNode preorder(TreeNode node) {
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left != null) {
            node.left = null; // 1
            node.right = left; // 1
            left = preorder(left); // 2
            left.right = right; // 2
            node = left;
        }
        if (right != null) {
            node = preorder(right);
        }
        return node;
    }
}
