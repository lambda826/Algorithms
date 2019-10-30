/**
 *  @author Yunxiang He
 *  @date 08/02/2018
 */

package coding.temp;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.


Note:
    The depth of the tree is at most 1000.
    The total number of nodes is at most 5000.

*/

public class _0559_Maximum_Depth_of_N_ary_Tree {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxDepth_BFS(TreeNode root) {
        int depth = 0;
        if (root != null) {
            Queue<TreeNode> que = new LinkedList<>();
            que.add(root);
            TreeNode node;
            while (!que.isEmpty()) {
                int size = que.size();
                depth++;
                for (int i = 0; i < size; i++) {
                    node = que.remove();
                    for (TreeNode child : node.children) {
                        if (child != null) {
                            que.add(child);
                        }
                    }
                }
            }
        }
        return depth;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (TreeNode child : root.children) {
            depth = Math.max(depth, maxDepth(child));
        }
        return depth + 1;
    }

}