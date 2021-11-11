/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package questions._10_tree.bst;

import common.TreeNode;

/*

Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.


Example 1:
    Input: [1,3,null,null,2]
       1
      /
     3
      \
       2
    Output: [3,1,null,null,2]
       3
      /
     1
      \
       2

Example 2:
    Input: [3,1,4,null,null,2]
      3
     / \
    1   4
       /
      2
    Output: [2,1,4,null,null,3]
      2
     / \
    1   4
       /
      3


Follow up:
    A solution using O(n) space is pretty straight forward.
    Could you devise a constant space solution?

*/

public class _0099_Recover_Binary_Search_Tree {
    public static void main(String[] args) {

        Integer[] nums = { 1, 3, null, null, 2 };
        TreeNode root = TreeNode.array2Tree(nums);
        _0099_Recover_Binary_Search_Tree test = new _0099_Recover_Binary_Search_Tree();
        test.recoverTree(root);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void recoverTree(TreeNode root) {
        TreeNode[] t = { null, null, new TreeNode(Integer.MIN_VALUE) };
        inorder(root, t);
        swap(t[0], t[1]);
    }

    private void inorder(TreeNode node, TreeNode[] t) {
        if (node != null) {
            inorder(node.left, t);
            // visit
            if (node.val < t[2].val) {
                if (t[0] == null) {
                    t[0] = t[2];
                    t[1] = node;
                } else {
                    t[1] = node;
                }
            }
            t[2] = node;
            inorder(node.right, t);
        }
    }

    private void swap(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }

}
