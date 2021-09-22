/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package coding.leetcode.temp;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*

Given preorder and inorder traversal of a tree, construct the binary tree.


For example, given
    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]
    Return the following binary tree:
    
        3
       / \
      9  20
        /  \
       15   7


Note:
    You may assume that duplicates do not exist in the tree.

*/

public class _0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // preorder: find root info
    //  inorder: find left and right subtree
    private final Map<Integer, Integer> inorderMap = new HashMap<>();
    private int[] preorder;
    private int end;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        end = preorder.length;
        for (int i = 0; i < end; ++i) {
            inorderMap.put(inorder[i], i);
        }
        return build(0, 0, end - 1);
    }

    // r: root index in preorder
    // [from, to]: segment in inorder
    private TreeNode build(int r, int from, int to) {
        if (from > to) {
            return null;
        } else {
            TreeNode node = new TreeNode(preorder[r]);
            int idx = inorderMap.get(preorder[r]); // root index in inorder
            node.left = build(r + 1, from, idx - 1);
            node.right = build(r + (idx - from + 1), idx + 1, to); // idx - from + 1: the len of left subtree + root; 
            return node;
        }
    }
}
