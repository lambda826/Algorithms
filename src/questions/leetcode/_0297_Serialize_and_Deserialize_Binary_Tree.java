package questions.leetcode;

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
    The number of nodes in the tree is in the range [0, 10^4].
    -1000 <= Node.val <= 1000

*/
public class _0297_Serialize_and_Deserialize_Binary_Tree {


    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        private void serializeHelper(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append("#").append(",");
            } else {
                sb.append(node.val).append(",");
                serializeHelper(node.left, sb);
                serializeHelper(node.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return deserializeHelper(data.split(","), new int[] { 0 });
        }

        private TreeNode deserializeHelper(String[] data, int[] index) {
            if (data[index[0]].equals("#")) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(data[index[0]]));
                ++index[0];
                node.left = deserializeHelper(data, index);
                ++index[0];
                node.right = deserializeHelper(data, index);
                return node;
            }
        }
    }

    public class Codec2 {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        private void serializeHelper(TreeNode node, StringBuilder sb) {
            sb.append("(");
            if (node != null) {
                sb.append(node.val + 1000);
                serializeHelper(node.left, sb);
                serializeHelper(node.right, sb);
            }
            sb.append(")");
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return deserializeHelper(data, new int[] { 0 });
        }

        private TreeNode deserializeHelper(String data, int[] idx) {
            TreeNode node = null;
            if (idx[0] != data.length()) {
                int val = 0;
                if (data.charAt(idx[0]) == '(') {
                    while (Character.isDigit(data.charAt(++idx[0]))) {
                        val = 10 * val + (data.charAt(idx[0]) - '0');
                    }
                    if (data.charAt(idx[0]) == '(') {
                        node = new TreeNode(val - 1000);
                        node.left = deserializeHelper(data, idx);
                        node.right = deserializeHelper(data, idx);
                    }
                }
                if (data.charAt(idx[0]) == ')') {
                    ++idx[0];
                }
            }
            return node;
        }
    }

    public class Codec3 {
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