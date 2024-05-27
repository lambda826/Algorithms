package questions.leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*

Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.


Example 1:
    Input: root = [1,2,3,null,5]
    Output: ["1->2->5","1->3"]
Example 2:
    Input: root = [1]
    Output: ["1"]


Constraints:
The number of nodes in the tree is in the range [1, 100].
-100 <= Node.val <= 100

*/
public class _0257_Binary_Tree_Paths {

    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            dfs(root, "", res);
            return res;
        }

        private void dfs(TreeNode node, String path, List<String> res) {
            if (node != null) {
                path += node.val;
                if (node.left == null && node.right == null) {
                    res.add(path);
                } else {
                    dfs(node.left, path + "->", res);
                    dfs(node.right, path + "->", res);
                }
            }
        }
    }
}
