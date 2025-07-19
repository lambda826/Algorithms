package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).


Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]

Example 2:
    Input: root = [1]
    Output: [[1]]

Example 3:
    Input: root = []
    Output: []


Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -1000 <= Node.val <= 1000

*/

public class _0102_Binary_Tree_Level_Order_Traversal {

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> list = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode t = queue.poll();
                    list.add(t.val);
                    if (t.left != null) {
                        queue.offer(t.left);
                    }
                    if (t.right != null) {
                        queue.offer(t.right);
                    }
                }
                res.add(list);
            }
            return res;
        }
    }

    class Solution2 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, res, 0);
            return res;
        }

        private void dfs(TreeNode node, List<List<Integer>> res, int depth) {
            if (node != null) {
                if (res.size() == depth) {
                    res.add(new ArrayList<>());
                }
                res.get(depth).add(node.val);
                dfs(node.left, res, depth + 1);
                dfs(node.right, res, depth + 1);
            }
        }
    }

}
