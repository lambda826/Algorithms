/**
 *  @author Yunxiang He
 *  @date 01/19/2019
 */

package coding.temp;

import common.TreeNode;

/*

Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.
 

Example 1:
    Input: [3,0,0]
    Output: 2
    Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

Example 2:
    Input: [0,3,0]
    Output: 3
    Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.

Example 3:
    Input: [1,0,2]
    Output: 2

Example 4:
    Input: [1,0,0,null,3]
    Output: 4
 

Note:
    1<= N <= 100
    0 <= node.val <= N

*/

public class _0979_Distribute_Coins_in_Binary_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int total = 0;

    public int distributeCoins(TreeNode root) {
        postOrder(root);
        return total;
    }

    public int postOrder(TreeNode root) {
        int step = 0;
        if (root != null) {
            int left = postOrder(root.left);
            int right = postOrder(root.right);
            step = left + right + root.val - 1;
        }
        total += Math.abs(step);
        return step;
    }
}
