/**
 *  @author: Yunxiang He
 *  @date  : 2018-08-02 03:03
 */

package questions.temp;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]


*/

public class _0515_Find_Largest_Value_in_Each_Tree_Row {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> largestValues_BFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> que = new LinkedList<>();
            que.add(root);
            while (!que.isEmpty()) {
                int size = que.size();
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode node = que.remove();
                    max = Math.max(max, node.val);
                    if (node.left != null) {
                        que.add(node.left);
                    }
                    if (node.right != null) {
                        que.add(node.right);
                    }
                }
                list.add(max);
            }
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> largestValues_DFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            DFS(root, list, 0);
        }
        return list;
    }

    private void DFS(TreeNode node, List<Integer> list, int depth) {
        if (node != null) {
            if (depth == list.size()) {
                list.add(node.val);
            }
            list.set(depth, Math.max(list.get(depth), node.val));
            DFS(node.left, list, depth + 1);
            DFS(node.right, list, depth + 1);
        }
    }
}
