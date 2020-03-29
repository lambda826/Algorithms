package coding.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/*

Given a n-ary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).


Example 1:
    Input: root = [1,null,3,2,4,null,5,6]
    Output: 3

Example 2:
    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output: 5


Note:
    The depth of the tree is at most 1000.
    The total number of nodes is at most 5000.


History:
    3/28/2020

*/

public class _0559_Maximum_Depth_of_N_ary_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxDepth_BFS(TreeNode root) {
        int depth = 0;
        if (root != null) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                ++depth;
                int size = queue.size();
                while (size-- > 0) {
                    for (TreeNode n : queue.poll().children) {
                        queue.offer(n);
                    }
                }
            }
        }
        return depth;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxDepth_DFS(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        } else {
            for (TreeNode child : root.children) {
                depth = Math.max(depth, maxDepth_DFS(child));
            }
        }
        return depth + 1;
    }

}