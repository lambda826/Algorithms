package leetcode;

import common.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*

Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.


Example 1:
    Input: root = [5,3,6,2,4,null,7], k = 9
    Output: true

Example 2:
    Input: root = [5,3,6,2,4,null,7], k = 28
    Output: false


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -10^4 <= Node.val <= 10^4
    root is guaranteed to be a valid binary search tree.
    -10^5 <= k <= 10^5

*/
public class _0653_Two_Sum_IV_Input_is_a_BST {

    class Solution {
        public boolean findTarget(TreeNode root, int k) {
            Set<Integer> set = new HashSet<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (set.contains(node.val)) {
                    return true;
                } else {
                    set.add(k - node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return false;
        }
    }

    class Solution2 {

        public boolean findTarget(TreeNode root, int k) {
            return dfs(root, new HashSet<>(), k);
        }

        private boolean dfs(TreeNode node, Set<Integer> visited, int k) {
            if (node == null) {
                return false;
            } else if (visited.contains(node.val)) {
                return true;
            } else {
                visited.add(k - node.val);
                return dfs(node.left, visited, k) || dfs(node.right, visited, k);
            }
        }
    }
}
