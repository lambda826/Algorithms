package coding.leetcode.temp;

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


Constraints:
    The number of nodes in both trees is in the range [0, 2000].
    -10^4 <= Node.val <= 10^4

*/

public class _0617_Merge_Two_Binary_Trees {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode mergeTrees_DFS(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        } else if (root2 == null) {
            return root1;
        } else {
            TreeNode node = new TreeNode(root1.val + root2.val);
            node.left = mergeTrees_DFS(root1.left, root2.left);
            node.right = mergeTrees_DFS(root1.right, root2.right);
            return node;
        }
    }

}