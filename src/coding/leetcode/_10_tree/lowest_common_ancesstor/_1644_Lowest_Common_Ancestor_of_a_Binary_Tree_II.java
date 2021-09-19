package coding.leetcode._10_tree.lowest_common_ancesstor;

import common.TreeNode;

/*

Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 1
    Output:
        3
    Explanation:
        The LCA of nodes 5 and 1 is 3.

Example 2:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 4
    Output:
        5
    Explanation:
        The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.

Example 3:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 10
    Output:
        null
    Explanation:
        Node 10 does not exist in the tree, so return null.


Constraints:
    The number of nodes in the tree is in the range [1, 10000].
    -1000000000 <= Node.val <= 1000000000
    All Node.val are unique.
    p != q

*/

public class _1644_Lowest_Common_Ancestor_of_a_Binary_Tree_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class Solution_PostOrder {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            int[] status = new int[1];
            TreeNode lca = postOrder(root, p, q, status);
            return status[0] == 2 ? lca : null;
        }

        private TreeNode postOrder(TreeNode node, TreeNode p, TreeNode q, int[] status) {
            if (node == null) {
                return null;
            } else {
                TreeNode left = postOrder(node.left, p, q, status);
                TreeNode right = postOrder(node.right, p, q, status);
                if (left != null && right != null) {
                    return node;
                } else if (node.val == p.val || node.val == q.val) {
                    status[0]++;
                    return node;
                } else if (left != null) {
                    return left;
                } else if (right != null) {
                    return right;
                } else {
                    return null;
                }
            }
        }
    }
}