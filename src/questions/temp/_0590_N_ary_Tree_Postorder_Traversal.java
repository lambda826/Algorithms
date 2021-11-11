/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Given an n-ary tree, return the postorder traversal of its nodes' values.

 
Note:
    Recursive solution is trivial, could you do it iteratively?

*/

public class _0590_N_ary_Tree_Postorder_Traversal {

    private List<Integer> list = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> postorder_Recursive(TreeNode root) {
        if (root != null) {
            for (TreeNode node : root.children) {
                postorder_Recursive(node);
            }
            list.add(root.val);
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> postorder_Iterative(TreeNode root) {
        if (root != null) {
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                TreeNode node = deque.remove();
                list.add(0, node.val);
                for (TreeNode n : node.children) {
                    deque.addFirst(n);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        _0590_N_ary_Tree_Postorder_Traversal test = new _0590_N_ary_Tree_Postorder_Traversal();
        TreeNode node1 = new TreeNode(1, new ArrayList<>());
        TreeNode node2 = new TreeNode(2, new ArrayList<>());
        TreeNode node3 = new TreeNode(3, new ArrayList<>());
        TreeNode node4 = new TreeNode(4, new ArrayList<>());
        TreeNode node5 = new TreeNode(5, new ArrayList<>());
        TreeNode node6 = new TreeNode(6, new ArrayList<>());
        node1.children.add(node3);
        node1.children.add(node2);
        node1.children.add(node4);
        node3.children.add(node5);
        node3.children.add(node6);
        test.postorder_Iterative(node1);

    }
}
