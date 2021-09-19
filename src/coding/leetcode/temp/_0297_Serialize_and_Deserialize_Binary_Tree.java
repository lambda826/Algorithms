package coding.leetcode.temp;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification:
    The input/output format is the same as how LeetCode serializes a binary tree.
    You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.


Example 1:
    Input:
        root = [1,2,3,null,null,4,5]
    Output:
        [1,2,3,null,null,4,5]

Example 2:
    Input:
        root = []
    Output:
        []

Example 3:
    Input:
        root = [1]
    Output:
        [1]

Example 4:
    Input:
        root = [1,2]
    Output:
        [1,2]


Constraints:
    The number of nodes in the tree is in the range [0, 10000].
    -1000 <= Node.val <= 1000

*/

public class _0297_Serialize_and_Deserialize_Binary_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PreOrder
    private class Solution_DFS {

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
            String[] dataArray = data.split(",");
            return preOrderDeserialize(dataArray, new int[] { 0 });
        }

        private TreeNode preOrderDeserialize(String[] data, int[] index) {
            if (data[index[0]].equals("#")) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(data[index[0]]));
                ++index[0];
                node.left = preOrderDeserialize(data, index);
                ++index[0];
                node.right = preOrderDeserialize(data, index);
                return node;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS Level traversal
    private class Solution_BFS {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if (root != null) {
                Queue<TreeNode> queue = new ArrayDeque<>();
                queue.offer(root);
                sb.append(root.val);
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    while (size-- > 0) {
                        TreeNode node = queue.poll();
                        enqueue(queue, sb, node.left);
                        enqueue(queue, sb, node.right);
                    }
                }
            }
            return sb.toString();
        }

        private void enqueue(Queue<TreeNode> queue, StringBuilder sb, TreeNode node) {
            if (node == null) {
                sb.append(",").append("#");
            } else {
                sb.append(",").append(node.val);
                queue.offer(node);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() > 0) {
                String[] str = data.split(",");
                int i = 0;
                TreeNode root = new TreeNode(Integer.parseInt(str[0]));
                Queue<TreeNode> queue = new ArrayDeque<>();
                queue.offer(root);
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    while (size-- > 0) {
                        TreeNode node = queue.poll();
                        if (!str[++i].equals("#")) {
                            node.left = new TreeNode(Integer.parseInt(str[i]));
                            queue.offer(node.left);
                        }
                        if (!str[++i].equals("#")) {
                            node.right = new TreeNode(Integer.parseInt(str[i]));
                            queue.offer(node.right);
                        }
                    }
                }
                return root;
            }
            return null;
        }
    }

}