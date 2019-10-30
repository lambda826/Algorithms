/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).


For example:
    Given binary tree [3,9,20,null,null,15,7],
            3
           / \
          9  20
            /  \
           15   7
    return its level order traversal as:
        [
          [3],
          [9,20],
          [15,7]
        ]

*/

public class _0102_Binary_Tree_Level_Order_Traversal {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                List<Integer> l = new ArrayList<>();
                int size = q.size();
                for (int i = 0; i < size; ++i) {
                    TreeNode t = q.remove();
                    l.add(t.val);
                    if (t.left != null) {
                        q.add(t.left);
                    }
                    if (t.right != null) {
                        q.add(t.right);
                    }
                }
                res.add(l);
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            DFS(root, res, 0);
        }
        return res;
    }

    private void DFS(TreeNode node, List<List<Integer>> res, int depth) {
        if (node != null) {
            if (res.size() == depth) {
                res.add(new ArrayList<>());
            }
            List<Integer> list = res.get(depth);
            list.add(node.val);
            DFS(node.left, res, depth + 1);
            DFS(node.right, res, depth + 1);
        }
    }

}
