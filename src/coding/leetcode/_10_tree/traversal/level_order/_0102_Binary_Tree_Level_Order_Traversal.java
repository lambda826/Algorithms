package coding.leetcode._10_tree.traversal.level_order;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).


Example 1:
    Input:
        root = [3,9,20,null,null,15,7]
    Output:
        [[3],[9,20],[15,7]]

Example 2:
    Input:
        root = [1]
    Output:
        [[1]]

Example 3:
    Input:
        root = []
    Output:
        []


Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -1000 <= Node.val <= 1000

*/

public class _0102_Binary_Tree_Level_Order_Traversal {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> levelOrder_BFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                int size = q.size();
                while (size-- >0) {
                    TreeNode t = q.poll();
                    list.add(t.val);
                    if (t.left != null) {
                        q.offer(t.left);
                    }
                    if (t.right != null) {
                        q.offer(t.right);
                    }
                }
                result.add(list);
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
    public List<List<Integer>> levelOrder_DFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            dfs(root, res, 0);
        }
        return res;
    }

    private void dfs(TreeNode node, List<List<Integer>> res, int depth) {
        if (node != null) {
            if (res.size() == depth) {
                res.add(new ArrayList<>());
            }
            List<Integer> list = res.get(depth);
            list.add(node.val);
            dfs(node.left, res, depth + 1);
            dfs(node.right, res, depth + 1);
        }
    }

}
