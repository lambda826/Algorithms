package coding.leetcode._10_tree.lowest_common_ancesstor;

import common.TreeNode;

/*

Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.
The distance between two nodes is the number of edges on the path from one to the other.


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 0
    Output:
        3
    Explanation:
        There are 3 edges between 5 and 0: 5-3-1-0.

Example 2:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 7
    Output:
        2
    Explanation:
        There are 2 edges between 5 and 7: 5-2-7.

Example 3:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        p = 5,
        q = 5
    Output:
        0
    Explanation:
        The distance between a node and itself is 0.


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -10^5 <= Node.val <= 10^5
    All Node.val are unique.
    p and q are values in the tree.

 */

public class _1740_Find_Distance_in_a_Binary_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findDistance(TreeNode root, int p, int q) {
        int[] dis = new int[1];
        if (root != null) {
            dfs(lca(root, p, q), p, q, 0, dis);
        }
        return dis[0];
    }

    private TreeNode lca(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        } else {
            TreeNode left = lca(root.left, p, q);
            TreeNode right = lca(root.right, p, q);
            if (left != null && right != null) {
                return root;
            } else if (left != null) {
                return left;
            } else {
                return right;
            }
        }
    }

    private void dfs(TreeNode node, int p, int q, int depth, int[] dis) {
        if (node != null) {
            if (node.val == p || node.val == q) {
                dis[0] += depth;
            }
            dfs(node.left, p, q, depth + 1, dis);
            dfs(node.right, p, q, depth + 1, dis);
        }
    }

}
