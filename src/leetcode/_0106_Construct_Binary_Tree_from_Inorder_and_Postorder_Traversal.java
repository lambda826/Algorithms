package leetcode;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and
return the binary tree.


Example 1:
    Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
    Output: [3,9,20,null,null,15,7]

Example 2:
    Input: inorder = [-1], postorder = [-1]
    Output: [-1]


Constraints:
    1 <= inorder.length <= 3000
    postorder.length == inorder.length
    -3000 <= inorder[i], postorder[i] <= 3000
    inorder and postorder consist of unique values.
    Each value of postorder also appears in inorder.
    inorder is guaranteed to be the inorder traversal of the tree.
    postorder is guaranteed to be the postorder traversal of the tree.

*/
public class _0106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length; ++i) {
                map.put(inorder[i], i);
            }
            return buildTree(postorder.length - 1, 0, inorder.length - 1, postorder, map);
        }

        private TreeNode buildTree(int root, int from, int to, int[] postorder, Map<Integer, Integer> map) {
            if (from > to) {
                return null;
            }
            TreeNode t = new TreeNode(postorder[root]);
            int idx = map.get(postorder[root]);
            t.left = buildTree(root - (to - idx) - 1, from, idx - 1, postorder, map);
            t.right = buildTree(root - 1, idx + 1, to, postorder, map);
            return t;
        }
    }
}
