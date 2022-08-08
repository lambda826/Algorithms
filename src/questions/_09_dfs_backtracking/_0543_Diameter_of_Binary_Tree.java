package questions._09_DFS_backtracking;

import common.TreeNode;

/*

Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.


Example 1:
    Input:
        root = [1,2,3,4,5]
    Output:
        3
    Explanation:
        3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
    Input:
        root = [1,2]
    Output:
        1


Constraints:
    The number of nodes in the tree is in the range [1, 104].
    -100 <= Node.val <= 100

*/

public class _0543_Diameter_of_Binary_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS to calculate the max number of nodes of the path
    // Minus 1 to get the path length
    public int diameterOfBinaryTree_DFS(TreeNode root) {
        int[] max = new int[1];
        postOrder(root, max);
        return max[0] - 1;
    }

    private int postOrder(TreeNode node, int[] max) {
        if (node == null) {
            return 0;
        } else {
            int l = postOrder(node.left, max);
            int r = postOrder(node.right, max);
            max[0] = Math.max(max[0], 1 + l + r);
            return Math.max(l, r) + 1;
        }
    }
}
