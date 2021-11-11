package questions._10_tree.traversal.universal_traversal;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given two binary trees original and cloned and given a reference to a node target in the original tree.
The cloned tree is a copy of the original tree.
Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.


Follow up: Solve the problem if repeated values on the tree are allowed.


Example 1:
    Input: tree = [7,4,3,null,null,6,19], target = 3
    Output: 3
    Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.

Example 2:
    Input: tree = [7], target =  7
    Output: 7

Example 3:
    Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
    Output: 4

Example 4:
    Input: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
    Output: 5

Example 5:
    Input: tree = [1,2,null,3], target = 2
    Output: 2


Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    The values of the nodes of the tree are unique.
    target node is a node from the original tree and is not null.

*/

public class _1379_Find_a_Corresponding_Node_of_a_Binary_Tree_in_a_Clone_of_That_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Traverse both tree at same pace
    public final TreeNode getTargetCopy_BFS(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Queue<TreeNode> queue1 = new ArrayDeque<>();
        Queue<TreeNode> queue2 = new ArrayDeque<>();
        queue1.offer(original);
        queue2.offer(cloned);
        TreeNode result = null;
        while (!queue1.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1 == target) {
                result = node2;
                break;
            }
            if (node1.left != null) {
                queue1.offer(node1.left);
                queue2.offer(node2.left);
            }
            if (node1.right != null) {
                queue1.offer(node1.right);
                queue2.offer(node2.right);
            }
        }
        return result;
    }
}
