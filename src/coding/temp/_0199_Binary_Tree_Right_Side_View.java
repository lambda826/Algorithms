/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---


*/

public class _0199_Binary_Tree_Right_Side_View {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res, 1);
        return res;
    }

    private void DFS(TreeNode node, List<Integer> res, int h) {
        if (node != null) {
            if (res.size() < h) {
                res.add(node.val);
            }
            DFS(node.right, res, h + 1);
            DFS(node.left, res, h + 1);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            LinkedList<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                res.add(q.getLast().val);
                int size = q.size();
                while (size-- > 0) {
                    TreeNode n = q.remove();
                    if (n.left != null) {
                        q.add(n.left);
                    }
                    if (n.right != null) {
                        q.add(n.right);
                    }
                }
            }
        }
        return res;
    }
}
