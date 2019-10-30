/**
 *  @author Yunxiang He
 *  @date 08/02/2018
 */

package coding.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).


Note:
    The depth of the tree is at most 1000.
    The total number of nodes is at most 5000.

*/

public class _0429_N_ary_Tree_Level_Order_Traversal {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> levelOrder_BFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> que = new ArrayDeque<>();
            que.offer(root);
            TreeNode node;
            List<Integer> list;
            while (!que.isEmpty()) {
                int size = que.size();
                list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    node = que.poll();
                    list.add(node.val);
                    if (node.children != null) {
                        que.addAll(node.children);
                    }
                }
                res.add(list);
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> levelOrder_DFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            DFS(root, res, 0);
        }
        return res;
    }

    private void DFS(TreeNode node, List<List<Integer>> res, int depth) {
        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }
        List<Integer> list = res.get(depth);
        list.add(node.val);
        for (TreeNode child : node.children) {
            DFS(child, res, depth + 1);
        }
    }

}
