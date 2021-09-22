/**
 *  @author Yunxiang He
 *  @date 01/11/2018
 */

package coding.leetcode._10_tree.bst;

import common.TreeNode;

/*

Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.


Example:
    root = [5,3,6,2,4,null,7]
    key = 3
    
        5
       / \
      3   6
     / \   \
    2   4   7
    Given key to delete is 3. So we find the node with value 3 and delete it.
    One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
        5
       / \
      4   6
     /     \
    2       7
    Another valid answer is [5,2,6,null,4,null,7].
    
        5
       / \
      2   6
       \   \
        4   7


Note: 
    Time complexity should be O(height of tree).
    
    
*/

public class _0450_Delete_Node_in_a_BST {

    public static void main(String[] args) {
        _0450_Delete_Node_in_a_BST test = new _0450_Delete_Node_in_a_BST();
        TreeNode root = TreeNode.array2Tree(new Integer[] { 5, 3, 6, 2, 4, null, 7 });
        int key = 3;
        TreeNode res = test.deleteNode(root, key);
        System.out.println(res);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Search for the key
    // If key < node.val, search the left subtree
    // If key > node.val, search the right subtree
    // If key == node.val, Append the left subtree to the left of min subtree of the node
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root != null) {
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    TreeNode rightMin = findMin(root.right);
                    rightMin.left = root.left;
                    return root.right;
                }
            }
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
