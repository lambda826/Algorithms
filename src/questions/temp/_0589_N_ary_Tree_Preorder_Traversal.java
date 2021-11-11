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

Given an n-ary tree, return the preorder traversal of its nodes' values.


Note:
    Recursive solution is trivial, could you do it iteratively?

*/

public class _0589_N_ary_Tree_Preorder_Traversal {

    List<Integer> list = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> preorder_Recursion(TreeNode root) {
        if (root != null) {
            list.add(root.val);
            for (TreeNode nodeNary : root.children) {
                preorder_Recursion(nodeNary);
            }
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> preorder_Iterative(TreeNode root) {
        if (root != null) {
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);
            while (deque.peek() != null) {
                TreeNode node = deque.removeFirst();
                list.add(node.val);
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    deque.addFirst(node.children.get(i));
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        new _0589_N_ary_Tree_Preorder_Traversal().preorder_Iterative(null);
    }

}
