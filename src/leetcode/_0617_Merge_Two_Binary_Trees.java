package leetcode;

import common.TreeNode;

/*

You are given two binary trees root1 and root2.
Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
You need to merge the two trees into a new binary tree.
The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node.
Otherwise, the NOT null node will be used as the node of the new tree.
Return the merged tree.
Note: The merging process must start from the root nodes of both trees.


Example 1:
    Input:
        root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
    Output:
        [3,4,5,5,4,null,7]

Example 2:
    Input:
        root1 = [1], root2 = [1,2]
    Output:
        [2,2]


Constraints:
The number of nodes in both trees is in the range [0, 2000].
-10^4 <= Node.val <= 10^4

 */
public class _0617_Merge_Two_Binary_Trees {
    class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2;
            } else if (root2 == null) {
                return root1;
            } else {
                TreeNode t = new TreeNode(root1.val + root2.val);
                t.left = mergeTrees(root1.left, root2.left);
                t.right = mergeTrees(root1.right, root2.right);
                return t;
            }
        }
    }
}
