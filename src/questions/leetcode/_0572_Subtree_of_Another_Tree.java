package questions.leetcode;

import common.TreeNode;

/*

Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.


Example 1:
    Input: root = [3,4,5,1,2], subRoot = [4,1,2]
    Output: true
Example 2:
    Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
    Output: false


Constraints:
    The number of nodes in the root tree is in the range [1, 2000].
    The number of nodes in the subRoot tree is in the range [1, 1000].
    -10^4 <= root.val <= 10^4
    -10^4 <= subRoot.val <= 10^4

*/
public class _0572_Subtree_of_Another_Tree {

    class Solution {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            return root != null && (compare(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
        }

        private boolean compare(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) {
                return true;
            } else if (t1 != null && t2 != null) {
                return t1.val == t2.val && compare(t1.left, t2.left) && compare(t1.right, t2.right);
            } else {
                return false;
            }
        }
    }
}
