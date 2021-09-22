package coding.leetcode._10_tree.lowest_common_ancesstor;


/*

Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes.
All the nodes will exist in the tree, and all values of the tree's nodes are unique.


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        nodes = [4,7]
    Output:
        2
    Explanation:
        The lowest common ancestor of nodes 4 and 7 is node 2.

Example 2:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        nodes = [1]
    Output:
        1
    Explanation:
        The lowest common ancestor of a single node is the node itself.

Example 3:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        nodes = [7,6,2,4]
    Output:
        5
    Explanation:
        The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.

Example 4:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        nodes = [0,1,2,3,4,5,6,7,8]
    Output:
        3
    Explanation:
        The lowest common ancestor of all the nodes is the root node.


Constraints:
    The number of nodes in the tree is in the range [1, 10000].
    -1000000000 <= Node.val <= 1000000000
    All Node.val are unique.
    All nodes[i] will exist in the tree.
    All nodes[i] are distinct.

*/

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class _1676_Lowest_Common_Ancestor_of_a_Binary_Tree_IV {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_PostOrder {
        public TreeNode lowestCommonAncestor_recursion(TreeNode root, TreeNode[] nodes) {
            Set<Integer> valSet = new HashSet<>();
            for (TreeNode node : nodes) {
                valSet.add(node.val);
            }
            return postOrder(root, valSet);
        }

        private TreeNode postOrder(TreeNode node, Set<Integer> valSet) {
            if (node == null || valSet.contains(node.val)) {
                return node;
            } else {
                TreeNode left = postOrder(node.left, valSet);
                TreeNode right = postOrder(node.right, valSet);
                if (left != null & right != null) {
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