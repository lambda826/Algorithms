package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).


Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[15,7],[9,20],[3]]

Example 2:
    Input: root = [1]
    Output: [[1]]

Example 3:
    Input: root = []
    Output: []


Constraints:
    The number of nodes in the tree is in the range [0, 2000].

*/
public class _0107_Binary_Tree_Level_Order_Traversal_II {

    class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            LinkedList<List<Integer>> res = new LinkedList<>();
            if (root != null) {
                Queue<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                while (!queue.isEmpty()) {
                    int levelSize = queue.size();
                    List<Integer> list = new ArrayList<>();
                    while (levelSize-- > 0) {
                        TreeNode node = queue.poll();
                        if (node.left != null) {
                            queue.offer(node.left);
                        }
                        if (node.right != null) {
                            queue.offer(node.right);
                        }
                        list.add(node.val);
                    }
                    res.addFirst(list);
                }
            }
            return res;
        }
    }

}
