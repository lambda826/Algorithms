package questions._08_bfs_DFS;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.


Example 1:
    Input:
        root = [1,2,3,null,5,null,4]
    Output:
        [1,3,4]

Example 2:
    Input:
        root = [1,null,3]
    Output:
        [1,3]

Example 3:
    Input:
        root = []
    Output:
        []


Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

*/

public class _0199_Binary_Tree_Right_Side_View {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS
    public List<Integer> rightSideView_BFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                list.add(queue.getLast().val);
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode curr = queue.poll();
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                }
            }
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
    // First add right subtree and then left subtree
    // The first node to add for each level is the rightmost node
    // When the list.size() is less than height, it indicates the node is the rightmost one
    public List<Integer> rightSideView_DFS(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        DFS(root, list, 1);
        return list;
    }

    private void DFS(TreeNode node, List<Integer> list, int height) {
        if (node != null) {
            if (list.size() < height) {
                list.add(node.val);
            }
            DFS(node.right, list, height + 1);
            DFS(node.left, list, height + 1);
        }
    }

}