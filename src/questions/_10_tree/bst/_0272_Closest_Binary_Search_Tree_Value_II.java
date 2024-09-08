/**
 * @author Yunxiang He
 * @date 03/01/2019
 */

package questions._10_tree.bst;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.


Example:
    Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
        4
       / \
      2   5
     / \
    1   3
    Output: [4,3]


Note:
    Given target value is a floating point.
    You may assume k is always valid, that is: k ≤ total nodes.
    You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
    
*/

public class _0272_Closest_Binary_Search_Tree_Value_II {

    public static void main(String[] args) {
        TreeNode root = TreeNode.array2Tree(new Integer[] { 4, 2, 5, 1, 3 });
        _0272_Closest_Binary_Search_Tree_Value_II test = new _0272_Closest_Binary_Search_Tree_Value_II();
        test.closestKValues(root, 3.714286, 2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        inOrder(root, target, k, deque);
        return new ArrayList<>(deque);
    }

    private void inOrder(TreeNode node, double target, int k, Deque<Integer> deque) {
        if (node != null) {
            inOrder(node.left, target, k, deque);
            if (deque.size() == k) {
                if (Math.abs(node.val - target) < Math.abs(deque.peekFirst() - target)) {
                    deque.pollFirst();
                    deque.offerLast(node.val);
                }
            } else {
                deque.offerLast(node.val);
            }
            inOrder(node.right, target, k, deque);
        }
    }
}
