package leetcode;

import common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*

Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.


Example 1:
    Input: root = [1,3,2,5,3,null,9]
    Output: 4
    Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).

Example 2:
    Input: root = [1,3,2,5,null,null,9,6,null,7]
    Output: 7
    Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).

Example 3:
    Input: root = [1,3,2,5]
    Output: 2
    Explanation: The maximum width exists in the second level with length 2 (3,2).


Constraints:
    The number of nodes in the tree is in the range [1, 3000].
    -100 <= Node.val <= 100

*/
public class _0662_Maximum_Width_of_Binary_Tree {

    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            int diff = 0;
            Deque<TreeNode> queue = new LinkedList<>();
            root.val = 1;
            queue.offer(root);
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                // calculate diff first to avoid overflow
                diff = Math.max(diff, queue.getLast().val - queue.getFirst().val);
                while (levelSize-- > 0) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        node.left.val = node.val * 2;
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        node.right.val = node.val * 2 + 1;
                        queue.offer(node.right);
                    }
                }
            }
            return diff + 1;
        }
    }

    class Solution2 {
        private class Pair {
            TreeNode node;
            long index;

            public Pair(TreeNode node, long index) {
                this.node = node;
                this.index = index;
            }
        }

        public int widthOfBinaryTree(TreeNode root) {
            int diff = 0;
            Queue<Pair> queue = new LinkedList<>();
            Pair pair = new Pair(root, 1);
            queue.offer(pair);
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;
                while (levelSize-- > 0) {
                    pair = queue.poll();
                    min = Math.min(min, pair.index);
                    max = Math.max(max, pair.index);
                    if (pair.node.left != null) {
                        queue.offer(new Pair(pair.node.left, pair.index * 2));
                    }
                    if (pair.node.right != null) {
                        queue.offer(new Pair(pair.node.right, pair.index * 2 + 1));
                    }
                }
                diff = Math.max(diff, (int) (max - min + 1));
            }
            return diff;
        }
    }
}
