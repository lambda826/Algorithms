package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given the root of a binary tree, the value of a target node target, and an integer k,
return an array of the values of all nodes that have a distance k from the target node.
You can return the answer in any order.


Example 1:
    Input:
        root = [3,5,1,6,2,0,8,null,null,7,4],
        target = 5,
        k = 2
    Output:
        [7,4,1]
    Explanation:
        The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.

Example 2:
    Input:
        root = [1],
        target = 1,
        k = 3
    Output:
        []


Constraints:
    The number of nodes in the tree is in the range [1, 500].
    0 <= Node.val <= 500
    All the values Node.val are unique.
    target is the value of one of the nodes in the tree.
    0 <= k <= 1000

*/
public class _0863_All_Nodes_Distance_K_in_Binary_Tree {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // Find all the root nodes including target itself through post-order traversal
            List<TreeNode> roots = new ArrayList<>();
            boolean[] targetFound = { false };
            postOrder(root, target, targetFound, roots);

            // dfs based on the roots
            List<Integer> res = new ArrayList<>();
            Set<TreeNode> visited = new HashSet<>();
            for (int i = 0; i < roots.size(); ++i) {
                dfs(roots.get(i), k - i, visited, res);
            }
            return res;
        }

        private void postOrder(TreeNode node, TreeNode target, boolean[] targetFound, List<TreeNode> roots) {
            if (node != null) {
                if (node == target) {
                    targetFound[0] = true;
                }
                if (!targetFound[0]) {
                    postOrder(node.left, target, targetFound, roots);
                }
                if (!targetFound[0]) {
                    postOrder(node.right, target, targetFound, roots);
                }
                if (targetFound[0]) {
                    roots.add(node);
                }
            }
        }

        private void dfs(TreeNode node, int k, Set<TreeNode> visited, List<Integer> res) {
            if (visited.add(node)) {
                if (k == 0) {
                    res.add(node.val);
                } else if (k > 0) {
                    if (node.left != null) {
                        dfs(node.left, k - 1, visited, res);
                    }
                    if (node.right != null) {
                        dfs(node.right, k - 1, visited, res);
                    }
                }
            }
        }
    }
}