package questions.leetcode.tree.bfs;

import common.TreeNode;

import java.util.*;

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
public class _0314_Binary_Tree_Vertical_Order_Traversal {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {

        public List<List<Integer>> verticalOrder(TreeNode root) {
            Map<TreeNode, Integer> nodeIndex = new HashMap<>();
            Map<Integer, List<Integer>> temp = new HashMap<>();
            if (root != null) {
                Queue<TreeNode> queue = new ArrayDeque<>();
                queue.add(root);
                nodeIndex.put(root, 0);
                while (!queue.isEmpty()) {
                    TreeNode node = queue.poll();
                    int index = nodeIndex.get(node);
                    temp.putIfAbsent(index, new ArrayList<>());
                    temp.get(index).add(node.val);
                    if (node.left != null) {
                        nodeIndex.put(node.left, index - 1);
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        nodeIndex.put(node.right, index + 1);
                        queue.offer(node.right);
                    }
                }
            }
            List<List<Integer>> res = new ArrayList<>();
            for (int i = -101; i < 101; ++i) {
                if (temp.containsKey(i)) {
                    res.add(temp.get(i));
                }
            }
            return res;
        }
    }
}