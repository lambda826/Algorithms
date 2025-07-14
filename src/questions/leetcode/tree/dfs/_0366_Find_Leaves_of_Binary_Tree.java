package questions.leetcode.tree.dfs;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given the root of a binary tree, collect a tree's nodes as if you were doing this:

Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty.


Example 1:
    Input: root = [1,2,3,4,5]
    Output: [[4,5,3],[2],[1]]
    Explanation:
    [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.

Example 2:
    Input: root = [1]
    Output: [[1]]


Constraints:
    The number of nodes in the tree is in the range [1, 100].
    -100 <= Node.val <= 100

*/
public class _0366_Find_Leaves_of_Binary_Tree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<List<Integer>> findLeaves(TreeNode root) {
            Map<Integer, List<Integer>> temp = new HashMap<>();
            dfs(root, temp);

            List<List<Integer>> res = new ArrayList<>();
            for (int i = 1; i <= temp.size(); ++i) {
                res.add(temp.get(i));
            }
            return res;
        }

        private int dfs(TreeNode node, Map<Integer, List<Integer>> temp) {
            if (node == null) {
                return 0;
            } else {
                int left = dfs(node.left, temp);
                int right = dfs(node.right, temp);
                int index = Math.max(left, right) + 1;
                temp.putIfAbsent(index, new ArrayList<>());
                temp.get(index).add(node.val);
                return index;
            }
        }
    }
}
