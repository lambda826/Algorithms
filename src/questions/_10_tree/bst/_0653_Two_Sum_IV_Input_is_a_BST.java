/**
 *  @author Yunxiang He
 *  @date Dec 11, 2017 4:26:59 PM
 */

package questions._10_tree.bst;

import common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False


*/

public class _0653_Two_Sum_IV_Input_is_a_BST {
    public boolean findTarget(TreeNode root, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (q.size() > 0) {
            TreeNode t = q.remove();
            if (map.containsKey(t.val)) {
                return true;
            } else {
                map.put(k - t.val, 0);
            }
            if (t.left != null) {
                q.add(t.left);
            }
            if (t.right != null) {
                q.add(t.right);
            }
        }
        return false;
    }
}
