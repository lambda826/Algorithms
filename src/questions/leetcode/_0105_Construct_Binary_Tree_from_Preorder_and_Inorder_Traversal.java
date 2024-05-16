package questions.leetcode;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and
return the binary tree.


Example 1:
    Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    Output: [3,9,20,null,null,15,7]
Example 2:
    Input: preorder = [-1], inorder = [-1]
    Output: [-1]


Constraints:
    1 <= preorder.length <= 3000
    inorder.length == preorder.length
    -3000 <= preorder[i], inorder[i] <= 3000
    preorder and inorder consist of unique values.
    Each value of inorder also appears in preorder.
    preorder is guaranteed to be the preorder traversal of the tree.
    inorder is guaranteed to be the inorder traversal of the tree.

*/
public class _0105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length; ++i) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, map, 0, 0, inorder.length - 1);
        }

        private TreeNode buildTree(int[] preorder, Map<Integer, Integer> map, int root, int from, int to) {
            if (from > to) {
                return null;
            }
            TreeNode t = new TreeNode(preorder[root]);
            int idx = map.get(t.val);
            t.left = buildTree(preorder, map, root + 1, from, idx - 1);
            t.right = buildTree(preorder, map, root + (idx - from + 1), idx + 1, to);
            return t;
        }
    }
}
