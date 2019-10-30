/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/*

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).


For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its bottom-up level order traversal as:
    [
      [15,7],
      [9,20],
      [3]
    ]

*/

public class _0107_Binary_Tree_Level_Order_Traversal_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> que = new ArrayDeque<>();
            que.offer(root);
            List<Integer> level;
            TreeNode node;
            while (!que.isEmpty()) {
                int size = que.size();
                level = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    node = que.poll();
                    level.add(node.val);
                    if (node.left != null) {
                        que.offer(node.left);
                    }
                    if (node.right != null) {
                        que.offer(node.right);
                    }
                }
                res.add(0, level);
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> levelOrderBottom_DFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        DFS(root, res, 0);
        Collections.reverse(res);
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
