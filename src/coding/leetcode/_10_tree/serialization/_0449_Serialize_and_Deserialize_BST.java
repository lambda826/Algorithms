package coding.leetcode._10_tree.serialization;

import common.TreeNode;

/*

Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree.
There is no restriction on how your serialization/deserialization algorithm should work.
You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.


Example 1:
    Input: root = [2,1,3]
    Output: [2,1,3]

Example 2:
    Input: root = []
    Output: []


Constraints:
    The number of nodes in the tree is in the range [0, 10^4].
    0 <= Node.val <= 10^4
    The input tree is guaranteed to be a binary search tree.

*/

public class _0449_Serialize_and_Deserialize_BST {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // preOrder
    // Return current node
    class Solution_DFS {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preOrderSerialize(root, sb);
            return sb.toString();
        }

        private void preOrderSerialize(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("#").append(",");
            } else {
                sb.append(node.val).append(",");
                preOrderSerialize(node.left, sb);
                preOrderSerialize(node.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return preOrderDeserialize(data.split(","), new int[] { 0 });
        }

        private TreeNode preOrderDeserialize(String[] dd, int[] index) {
            if (dd[index[0]].equals("#")) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(dd[index[0]]));
                ++index[0];
                node.left = preOrderDeserialize(dd, index);
                ++index[0];
                node.right = preOrderDeserialize(dd, index);
                return node;
            }
        }
    }

}