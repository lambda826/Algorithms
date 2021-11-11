/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package questions.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Given a binary tree, return the inorder traversal of its nodes' values.


Example:
    Input: [1,null,2,3]
       1
        \
         2
        /
       3
    Output: [1,3,2]


Follow up: 
    Recursive solution is trivial, could you do it iteratively?

*/

public class _0094_Binary_Tree_Inorder_Traversal {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // recursive
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {
            inorderTraversal(node.left, list);
            list.add(node.val);
            inorderTraversal(node.right, list);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // iterative + stack
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.offerLast(curr);
                curr = curr.left;
            } else {
                curr = stack.pollLast();
                list.add(curr.val);
                curr = curr.right;
            }
        }
        return list;
    }

}
