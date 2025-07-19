package leetcode;

import common.TreeNode;

/*

Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.


Example 1:
    Input: root = [1,2,3,4,5,6]
    Output: 6
Example 2:
    Input: root = []
    Output: 0
Example 3:
    Input: root = [1]
    Output: 1


Constraints:
    The number of nodes in the tree is in the range [0, 5 * 104].
    0 <= Node.val <= 5 * 104
    The tree is guaranteed to be complete.

*/
public class _0222_Count_Complete_Tree_Nodes {

    class Solution {
        public int countNodes(TreeNode root) {
            int height = 0;
            TreeNode node = root;
            while (node != null) {
                ++height;
                node = node.left;
            }
            int[] num = { 0 };
            dfs(root, height, num, new boolean[] { false });
            return (1 << height) - 1 - num[0];
        }

        private void dfs(TreeNode node, int height, int[] num, boolean[] isStop) {
            if (!isStop[0]) {
                if (node == null) {
                    if (height == 0) {
                        isStop[0] = true;
                    } else {
                        num[0]++;
                    }
                } else {
                    dfs(node.right, height - 1, num, isStop);
                    dfs(node.left, height - 1, num, isStop);
                }
            }
        }
    }

    class Solution2 {

        public int countNodes(TreeNode root) {
            int height = 0;
            TreeNode node = root;
            // Calculate the height of the tree by traversing the leftmost path.
            while (node != null) {
                ++height;
                node = node.left;
            }
            int sum = 0;
            node = root;
            // Traverse the tree and count nodes.
            while (node != null) {
                height--;
                int h = getHeight(node.left);
                // Check if the left subtree is a perfect binary tree.
                // If h == height, it means the left subtree is a perfect binary tree (full).
                // Thus, move to the right subtree (node = node.right) because all nodes in the left subtree are counted.
                if (h == height) {
                    node = node.right;
                } else {
                    node = node.left;
                }
                // Add the number of nodes in the perfect subtree.
                sum += (1 << h);
            }

            return sum;
        }

        private int getHeight(TreeNode node) {
            int height = 0;
            // Traverse the rightmost path to calculate the height.
            while (node != null) {
                ++height;
                node = node.right;
            }
            return height;
        }
    }
}
