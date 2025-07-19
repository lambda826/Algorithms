package leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.


Example 1:
    Input: root = [3,1,4,null,2], k = 1
    Output: 1

Example 2:
    Input: root = [5,3,6,2,4,null,null,1], k = 3
    Output: 3


Constraints:
    The number of nodes in the tree is n.
    1 <= k <= n <= 10^4
    0 <= Node.val <= 10^4


Follow up:
    If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?

*/
public class _0230_Kth_Smallest_Element_in_a_BST {

    class Solution {
        public int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                if (curr != null) {
                    stack.offerLast(curr);
                    curr = curr.left;
                } else {
                    curr = stack.pollLast();
                    if (--k == 0) {
                        return curr.val;
                    }
                    curr = curr.right;
                }
            }
            return -1;
        }
    }

    class Solution2 {
        public int kthSmallest(TreeNode root, int k) {
            int[] res = { -1 };
            inorder(root, new int[] { k }, res);
            return res[0];
        }

        private void inorder(TreeNode node, int[] k, int[] res) {
            if (node != null) {
                inorder(node.left, k, res);
                if (--k[0] == 0) {
                    res[0] = node.val;
                }
                inorder(node.right, k, res);
            }
        }
    }
}
