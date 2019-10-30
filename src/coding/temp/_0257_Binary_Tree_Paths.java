/**
 *  @author Yunxiang He
 *  @date 04/22/2018
 */

package coding.temp;

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

*/

public class _0257_Binary_Tree_Paths {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        DFS(root, "", res);
        return res;
    }

    private void DFS(TreeNode node, String temp, List<String> res) {
        if (node != null) {
            temp += node.val;
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    DFS(node.left, temp + "->", res);
                }
                if (node.right != null) {
                    DFS(node.right, temp + "->", res);
                }
            } else {
                res.add(temp);
            }
        }
    }
}
