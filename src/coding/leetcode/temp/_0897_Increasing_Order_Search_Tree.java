package coding.leetcode.temp;

/*

Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.


Example 1:
    Input:
        [5,3,6,2,4,null,8,1,null,null,null,7,9]

               5
              / \
            3    6
           / \    \
          2   4    8
         /        / \
        1        7   9
    
    Output:
        [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
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
                    \
                     7
                      \
                       8
                        \
                         9


Constraints:
    The number of nodes in the given tree will be in the range [1, 100].
    0 <= Node.val <= 1000

*/

import common.TreeNode;

public class _0897_Increasing_Order_Search_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode increasingBST(TreeNode root) {
        TreeNode[] temp = {new TreeNode()};
        TreeNode res = temp[0];
        inorder(root, temp);
        return res.right;
    }

    private void inorder(TreeNode node, TreeNode[] temp) {
        if (node != null) {
            inorder(node.left, temp);
            node.left = null;
            temp[0].right = node;
            temp[0] = node;
            inorder(node.right, temp);
        }
    }
}
