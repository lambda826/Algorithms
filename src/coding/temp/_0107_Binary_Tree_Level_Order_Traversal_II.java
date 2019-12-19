package coding.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
    public List<List<Integer>> levelOrderBottom_BFS(TreeNode root) {
        LinkedList<List<Integer>> list = new LinkedList<>();
        if (root != null) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            TreeNode node;
            List<Integer> l;
            while (!queue.isEmpty()) {
                int size = queue.size();
                l = new ArrayList();
                while (size-- > 0) {
                    node = queue.poll();
                    l.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                list.addFirst(l);
            }
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> levelOrderBottom_DFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        DFS(root, res, 0);
        Collections.reverse(res);
        return res;
    }

    private void DFS(TreeNode node, List<List<Integer>> list, int d) {
        if (node != null) {
            if (list.size() == d) {
                list.add(new ArrayList<>());
            }
            list.get(d).add(node.val);
            DFS(node.left, list, d + 1);
            DFS(node.right, list, d + 1);
        }
    }
}
