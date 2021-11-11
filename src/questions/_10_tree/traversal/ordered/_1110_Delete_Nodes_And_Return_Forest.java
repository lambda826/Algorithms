package questions._10_tree.traversal.ordered;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given the root of a binary tree, each node in the tree has a distinct value.
After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
Return the roots of the trees in the remaining forest. You may return the result in any order.


Example 1:
    Input:
        root = [1,2,3,4,5,6,7],
        to_delete = [3,5]
    Output:
        [[1,2,null,4],[6],[7]]

Example 2:
    Input:
        root = [1,2,4,null,3],
        to_delete = [3]
    Output:
        [[1,2,4]]


Constraints:
    The number of nodes in the given tree is at most 1000.
    Each node has a distinct value between 1 and 1000.
    to_delete.length <= 1000
    to_delete contains distinct values between 1 and 1000.

*/
public class _1110_Delete_Nodes_And_Return_Forest {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_PostOrder {
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            Set<Integer> toDelete = new HashSet<>();
            for (int del : to_delete) {
                toDelete.add(del);
            }
            List<TreeNode> res = new ArrayList<>();
            if (postOrder(root, toDelete, res) != null) {
                res.add(root);
            }
            return res;
        }

        private TreeNode postOrder(TreeNode node, Set<Integer> toDelete, List<TreeNode> res) {
            if (node != null) {
                node.left = postOrder(node.left, toDelete, res);
                node.right = postOrder(node.right, toDelete, res);
                if (toDelete.contains(node.val)) {
                    if (node.left != null) {
                        res.add(node.left);
                    }
                    if (node.right != null) {
                        res.add(node.right);
                    }
                    node = null;
                }
            }
            return node;
        }

    }
}