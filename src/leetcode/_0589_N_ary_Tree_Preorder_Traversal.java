package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*

Given the root of an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)


Example 1:
    Input: root = [1,null,3,2,4,null,5,6]
    Output: [1,3,5,6,2,4]

Example 2:
    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]


Constraints:
    The number of nodes in the tree is in the range [0, 10^4].
    0 <= Node.val <= 10^4
    The height of the n-ary tree is less than or equal to 1000.


Follow up:
    Recursive solution is trivial, could you do it iteratively?

*/
public class _0589_N_ary_Tree_Preorder_Traversal {

    class Solution {
        public List<Integer> preorder(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            preorder(root, res);
            return res;
        }

        private void preorder(TreeNode node, List<Integer> res) {
            if (node != null) {
                res.add(node.val);
                for (TreeNode next : node.children) {
                    preorder(next, res);
                }
            }
        }
    }

    class Solution2 {
        public List<Integer> preorder(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root != null) {
                Deque<TreeNode> stack = new LinkedList<>();
                stack.offer(root);
                while (!stack.isEmpty()) {
                    root = stack.pollLast();
                    res.add(root.val);
                    for (int i = root.children.size() - 1; i >= 0; --i) {
                        stack.offerLast(root.children.get(i));
                    }
                }
            }
            return res;
        }
    }

}
