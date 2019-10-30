/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package coding.temp;

import common.TreeNode;

/*

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.


Example 1:
    Input: root = [2,1,3], p = 1
    Output: 2
    Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:
    Input: root = [5,3,6,2,4,null,null,1], p = 6
    Output: null
    Explanation: There is no in-order successor of the current node, so the answer is null.


Note:
    If the given node has no in-order successor in the tree, return null.
    It's guaranteed that the values of the tree are unique.

*/

public class _0285_Inorder_Successor_in_BST {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private TreeNode pre;
    private TreeNode res;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return res;
    }

    private void inorder(TreeNode curr, TreeNode target) {
        if (curr != null) {
            inorder(curr.left, target);
            if (pre == target) {
                res = curr;
            }
            pre = curr;
            inorder(curr.right, target);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Search from root, find the smallest val greater than node.val
    public static TreeNode successor_search_from_root(TreeNode root, TreeNode target) {
        TreeNode curr = null;
        while (root != null) {
            if (root.val > target.val) {
                curr = root;
                root = root.left;
            } else if (root.val <= target.val) {
                root = root.right;
            }
        }
        return curr;
    }
}
