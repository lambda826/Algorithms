/**
 *  @author Yunxiang He
 *  @date 03/04/2019
 */

package questions.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*

Given a binary tree, return the postorder traversal of its nodes' values.


Example:
    Input: [1,null,2,3]
       1
        \
         2
        /
       3
    Output: [3,2,1]


Follow up: 
    Recursive solution is trivial, could you do it iteratively?

*/

public class _0145_Binary_Tree_Postorder_Traversal {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    private void postOrder(TreeNode node, List<Integer> list) {
        if (node != null) {
            postOrder(node.left, list);
            postOrder(node.right, list);
            list.add(node.val);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                res.addFirst(curr.val);
                stack.offerLast(curr);
                curr = curr.right;
            } else {
                curr = stack.pollLast().left;
            }
        }
        return res;
    }
}
