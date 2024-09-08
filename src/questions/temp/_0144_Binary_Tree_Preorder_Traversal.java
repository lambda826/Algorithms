/**
 * @author Yunxiang He
 * @date 05/23/2018
 */

package questions.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Given a binary tree, return the preorder traversal of its nodes' values.


Example:
    Input: [1,null,2,3]
       1
        \
         2
        /
       3
    Output: [1,2,3]


Follow up: 
    Recursive solution is trivial, could you do it iteratively?

*/

public class _0144_Binary_Tree_Preorder_Traversal {

    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            preorderTraversal(root, res);
            return res;
        }

        private void preorderTraversal(TreeNode node, List<Integer> res) {
            if (node != null) {
                res.add(node.val);
                preorderTraversal(node.left, res);
                preorderTraversal(node.right, res);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                res.add(curr.val);
                stack.offerLast(curr);
                curr = curr.left;
            } else {
                curr = stack.pollLast().right;
            }
        }
        return res;
    }
}
