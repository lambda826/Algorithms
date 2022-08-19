package questions.temp;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*

Given a binary tree, return all root-to-leaf paths.


Example:
    Input:
       1
     /   \
    2     3
     \
      5
    Output: ["1->2->5", "1->3"]
    Explanation: All root-to-leaf paths are: 1->2->5, 1->3


Note: 
    A leaf is a node with no children.


History:
    3/29/2020

*/

public class _0257_Binary_Tree_Paths {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode node, String path, List<String> res) {
        if (node != null) {
            path += node.val;
            if (node.left != null) {
                dfs(node.left, path + "->", res);
            }
            if (node.right != null) {
                dfs(node.right, path + "->", res);
            }
            if (node.left == null && node.right == null) {
                res.add(path);
            }
        }
    }
}
