/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*


Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).


For example:
    Given binary tree [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    return its zigzag level order traversal as:
    [
      [3],
      [20,9],
      [15,7]
    ]

 */

public class _0103_Binary_Tree_Zigzag_Level_Order_Traversal {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> zigzagLevelOrder_BFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> Q = new ArrayDeque<TreeNode>();
        boolean isLeft = true;
        if (root != null) {
            Q.addFirst(root);
        }
        while (!Q.isEmpty()) {
            int size = Q.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = isLeft ? Q.removeFirst() : Q.removeLast();
                level.add(node.val);
                if (isLeft) {
                    if (node.left != null) {
                        Q.add(node.left);
                    }
                    if (node.right != null) {
                        Q.add(node.right);
                    }
                } else {
                    if (node.right != null) {
                        Q.addFirst(node.right);
                    }
                    if (node.left != null) {
                        Q.addFirst(node.left);
                    }
                }
            }
            isLeft = !isLeft;
            res.add(level);
        }
        return res;
    }

}
