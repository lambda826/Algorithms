/**
 *  @author Yunxiang He
 *  @date 01/02/2018
 */

package coding.temp;

import common.TreeNode;

/*

Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
You need to merge them into a new binary tree. 

The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. 
Otherwise, the NOT null node will be used as the node of new tree.


Example 1:
    Input: 
        Tree 1                     Tree 2                  
              1                         2                             
             / \                       / \                            
            3   2                     1   3                        
           /                           \   \                      
          5                             4   7                  
    Output: 
    Merged tree:
             3
            / \
           4   5
          / \   \ 
         5   4   7


Note: 
    The merging process must start from the root nodes of both trees.

*/

public class _0617_Merge_Two_Binary_Trees {
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode t = null;
        if (t1 != null || t2 != null) {
            int sum = 0;
            if (t1 != null) {
                sum += t1.val;
            }
            if (t2 != null) {
                sum += t2.val;
            }
            t = new TreeNode(sum);
            t.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
            t.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        }
        return t;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return t1;
        }
        if (t1 == null) {
            return t2;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees2(t1.left, t2.left);
        t1.right = mergeTrees2(t1.right, t2.right);
        return t1;
    }

}
