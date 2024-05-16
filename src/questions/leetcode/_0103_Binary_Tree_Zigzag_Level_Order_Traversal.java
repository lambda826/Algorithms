/**
 * @author Yunxiang He
 * @date Jan 29, 2018 9:55:28 PM
 */

package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*


Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).


For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its zigzag level order traversal as:
    [
      [3],
      [20,9],
      [15,7]
    ]

 */

public class _0103_Binary_Tree_Zigzag_Level_Order_Traversal {

    class Solution {
        /**
         * Use a queue to perform a level-order traversal.
         * Use a flag (leftToRight) to keep track of the direction of traversal for each level.
         * For each level, use a list to store the node values.
         * - If leftToRight is true, append values to the list.
         * - If leftToRight is false, insert values at the beginning of the list.
         * Toggle the leftToRight flag after processing each level.
         */
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root != null) {
                Queue<TreeNode> queue = new ArrayDeque<>();
                boolean leftToRight = true;
                queue.offer(root);
                while (!queue.isEmpty()) {
                    int levelSize = queue.size();
                    LinkedList<Integer> list = new LinkedList<>();
                    while (levelSize-- > 0) {
                        TreeNode t = queue.poll();
                        if (leftToRight) {
                            list.addLast(t.val);
                        } else {
                            list.addFirst(t.val);
                        }
                        if (t.left != null) {
                            queue.offer(t.left);
                        }
                        if (t.right != null) {
                            queue.offer(t.right);
                        }
                    }
                    res.add(list);
                    leftToRight = !leftToRight;
                }
            }
            return res;
        }
    }
}
