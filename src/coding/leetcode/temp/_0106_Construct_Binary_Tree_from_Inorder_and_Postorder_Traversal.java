/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package coding.leetcode.temp;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*

Given inorder and postorder traversal of a tree, construct the binary tree.


For example, given
    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]
    Return the following binary tree:
        3
       / \
      9  20
        /  \
       15   7


Note:
    You may assume that duplicates do not exist in the tree.


*/

public class _0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. find root index in postorder(左右根), which is the last index in postorder
    // 2. recursively find the root of the left subtree and rightsubtree of the root
    private final Map<Integer, Integer> inorderMap = new HashMap<>();
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        for (int i = 0; i < postorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        return help(postorder.length - 1, 0, postorder.length - 1);
    }

    // r: root index in postorder
    // [from, to]: segment in inorder
    private TreeNode help(int r, int from, int to) {
        if (from > to) {
            return null;
        } else {
            TreeNode root = new TreeNode(postorder[r]);
            int idx = inorderMap.get(postorder[r]); // root index in inorder
            root.left = help(r - (to - idx + 1), from, idx - 1);
            root.right = help(r - 1, idx + 1, to);
            return root;
        }
    }
}
