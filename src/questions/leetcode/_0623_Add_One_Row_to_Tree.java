package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.

Note that the root node is at depth 1.

The adding rule is:
    Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
    cur's original left subtree should be the left subtree of the new left subtree root.
    cur's original right subtree should be the right subtree of the new right subtree root.
    If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the
    new root's left subtree.


Example 1:
    Input: root = [4,2,6,3,1,5], val = 1, depth = 2
    Output: [4,1,1,2,null,null,6,3,1,5]

Example 2:
    Input: root = [4,2,null,3,1], val = 1, depth = 3
    Output: [4,2,null,1,1,3,null,null,1]


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    The depth of the tree is in the range [1, 10^4].
    -100 <= Node.val <= 100
    -10^5 <= val <= 10^5
    1 <= depth <= the depth of tree + 1

*/
public class _0623_Add_One_Row_to_Tree {

    class Solution {
        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                TreeNode newNode = new TreeNode(val);
                newNode.left = root;
                return newNode;
            }
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                if (--depth == 1) {
                    break;
                }
                int levelSize = queue.size();
                while (levelSize-- > 0) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                TreeNode newNode = new TreeNode(val);
                TreeNode tempLeft = node.left;
                node.left = newNode;
                newNode.left = tempLeft;

                TreeNode newNode2 = new TreeNode(val);
                TreeNode tempRight = node.right;
                node.right = newNode2;
                newNode2.right = tempRight;
            }

            return root;
        }
    }
}
