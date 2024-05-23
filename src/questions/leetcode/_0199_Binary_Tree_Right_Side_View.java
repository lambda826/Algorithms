package questions.leetcode;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.


Example 1:
    Input: root = [1,2,3,null,5,null,4]
    Output: [1,3,4]
Example 2:
    Input: root = [1,null,3]
    Output: [1,3]
Example 3:
    Input: root = []
    Output: []


Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

*/
public class _0199_Binary_Tree_Right_Side_View {

    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(root, 0, res);
            return res;
        }

        private void dfs(TreeNode node, int depth, List<Integer> list) {
            if (node != null) {
                if (list.size() == depth) {
                    list.add(node.val);
                }
                dfs(node.right, depth + 1, list);
                dfs(node.left, depth + 1, list);
            }
        }
    }

    class Solution2 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root != null) {
                Deque<TreeNode> queue = new ArrayDeque<>();
                queue.offerLast(root);
                while (!queue.isEmpty()) {
                    int levelSize = queue.size();
                    res.add(queue.getFirst().val);
                    while (levelSize-- > 0) {
                        TreeNode node = queue.pollFirst();
                        if (node.right != null) {
                            queue.offerLast(node.right);
                        }
                        if (node.left != null) {
                            queue.offerLast(node.left);
                        }
                    }
                }
            }
            return res;
        }
    }
}