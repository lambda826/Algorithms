package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given two binary trees original and cloned and given a reference to a node target in the original tree.
The cloned tree is a copy of the original tree.
Return a reference to the same node in the cloned tree.
Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.


Example 1:
    Input:
        tree = [7,4,3,null,null,6,19], target = 3
    Output:
        3
    Explanation:
        In all examples the original and cloned trees are shown. The target node is a green node from the original tree.
        The answer is the yellow node from the cloned tree.
Example 2:
    Input:
        tree = [7], target =  7
    Output:
        7
Example 3:
    Input:
        tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
    Output:
        4


Constraints:
    The number of nodes in the tree is in the range [1, 104].
    The values of the nodes of the tree are unique.
    target node is a node from the original tree and is not null.

 */
public class _1379_Find_a_Corresponding_Node_of_a_Binary_Tree_in_a_Clone_of_That_Tree {

    class Solution_DFS {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null) {
                return null;
            } else if (original == target) {
                return cloned;
            } else {
                TreeNode t = getTargetCopy(original.left, cloned.left, target);
                if (t != null) {
                    return t;
                } else {
                    return getTargetCopy(original.right, cloned.right, target);
                }
            }
        }
    }

    class Solution_BFS {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            Queue<TreeNode> q1 = new ArrayDeque<>();
            Queue<TreeNode> q2 = new ArrayDeque<>();
            q1.offer(original);
            q2.offer(cloned);
            while (!q1.isEmpty()) {
                TreeNode t1 = q1.poll();
                TreeNode t2 = q2.poll();
                if (t1 == target) {
                    return t2;
                } else {
                    enque(q1, t1.left);
                    enque(q1, t1.right);
                    enque(q2, t2.left);
                    enque(q2, t2.right);
                }
            }
            return null;
        }

        private void enque(Queue<TreeNode> que, TreeNode t) {
            if (t != null) {
                que.offer(t);
            }
        }
    }
}
