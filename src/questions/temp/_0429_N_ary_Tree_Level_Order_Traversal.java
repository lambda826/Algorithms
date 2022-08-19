package questions.temp;

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

    /**
     * BFS
     * 1. Level traverse
     * 2. Note for the data structure
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> list = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode node = queue.poll();
                    list.add(node.val);
                    for (TreeNode child : node.children) {
                        queue.offer(child);
                    }
                }
                result.add(list);
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> levelOrder_DFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            dfs(root, res, 0);
        }
        return res;
    }

    private void dfs(TreeNode node, List<List<Integer>> res, int depth) {
        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }
        List<Integer> list = res.get(depth);
        list.add(node.val);
        for (TreeNode child : node.children) {
            dfs(child, res, depth + 1);
        }
    }

}
