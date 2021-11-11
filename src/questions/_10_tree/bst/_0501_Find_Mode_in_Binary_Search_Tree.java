/**
 *  @author Yunxiang He
 *  @date 02/19/2019
 */

package questions._10_tree.bst;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*

Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
Assume a BST is defined as follows:
    The left subtree of a node contains only nodes with keys less than or equal to the node's key.
    The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
    Both the left and right subtrees must also be binary search trees.
 

For example:
    Given BST [1,null,2,2],
    
       1
        \
         2
        /
       2
    return [2].


Note: 
    If a tree has more than one mode, you can return them in any order.


Follow up: 
    Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

*/

public class _0501_Find_Mode_in_Binary_Search_Tree {

    public static void main(String[] args) {
        new _0501_Find_Mode_in_Binary_Search_Tree().findMode(new TreeNode(Integer.MAX_VALUE));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int count = 0;
    private int max = 0;
    private Integer pre = null;
    private List<Integer> list = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);
        int[] res = new int[list.size()];
        int n = 0;
        for (int i : list) {
            res[n++] = i;
        }
        return res;
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            if (pre == null || pre != root.val) {
                count = 1;
            } else if (pre == root.val) {
                count++;
            }
            if (count > max) {
                max = count;
                list.clear();
                list.add(root.val);
            } else if (count == max) {
                list.add(root.val);
            }
            pre = root.val;
            inorder(root.right);
        }
    }
}
