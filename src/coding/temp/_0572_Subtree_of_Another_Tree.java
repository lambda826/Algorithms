/**
 *  @author Yunxiang He
 *  @date 02/21/2019
 */

package coding.temp;

import coding.topic._01_Tree;
import common.TreeNode;

/*

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. 
A subtree of s is a tree consists of a node in s and all of this node's descendants. 
The tree s could also be considered as a subtree of itself.


Example 1:
    Given tree s:
    
         3
        / \
       4   5
      / \
     1   2
    Given tree t:
       4 
      / \
     1   2
    Return true, because t has the same structure and node values with a subtree of s.

Example 2:
    Given tree s:
    
         3
        / \
       4   5
      / \
     1   2
        /
       0
    Given tree t:
       4
      / \
     1   2

*/

public class _0572_Subtree_of_Another_Tree implements _01_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return s != null && (compare(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
    }

    private boolean compare(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else {
            return s.val == t.val && compare(s.left, t.left) && compare(s.right, t.right);
        }
    }
}
