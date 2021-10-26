package coding.leetcode;

/*

Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.


Example 1:
    Input:
        root = [3,9,20,null,null,15,7]
    Output:
        [[9],[3,15],[20],[7]]

Example 2:
    Input:
        root = [3,9,8,4,0,1,7]
    Output:
        [[4],[9],[3,0,1],[8],[7]]

Example 3:
    Input:
        root = [3,9,8,4,0,1,7,null,null,null,2,5]
    Output:
        [[4],[9,5],[3,0,1],[8,2],[7]]

Example 4:
    Input:
        root = []
    Output:
        []


Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

*/

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class _0314_Binary_Tree_Vertical_Order_Traversal {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_BFS {

        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            int[] range = { Integer.MAX_VALUE, Integer.MIN_VALUE };
            Map<Integer, List<Integer>> map = new HashMap<>();
            BFS(root, range, map);
            for (int i = range[0]; i <= range[1]; ++i) {
                res.add(map.get(i));
            }
            return res;
        }

        public void BFS(TreeNode root, int[] range, Map<Integer, List<Integer>> map) {
            if (root != null) {
                Queue<TreeNode> queue = new ArrayDeque<>();
                Queue<Integer> queue2 = new ArrayDeque<>();
                queue.offer(root);
                queue2.offer(0);
                while (!queue.isEmpty()) {
                    TreeNode node = queue.poll();
                    int index = queue2.poll();
                    range[0] = Math.min(range[0], index);
                    range[1] = Math.max(range[1], index);
                    map.putIfAbsent(index, new ArrayList<>());
                    map.get(index).add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                        queue2.offer(index - 1);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                        queue2.offer(index + 1);
                    }
                }
            }
        }
    }
}