package questions._10_tree.serialization;

/*

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree.
An N-ary tree is a rooted tree in which each node has no more than N children.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree as [1 [3[5 6] 2 4]].
    Note that this is just an example, you do not necessarily need to follow this format.
    Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.
For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.


Example 1:
    Input:
        root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output:
        [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]

Example 2:
    Input:
        root = [1,null,3,2,4,null,5,6]
    Output:
        [1,null,3,2,4,null,5,6]

Example 3:
    Input:
        root = []
    Output:
        []


Constraints:
    The number of nodes in the tree is in the range [0, 10^4].
    0 <= Node.val <= 10^4
    The height of the n-ary tree is less than or equal to 1000
    Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.

*/

import common.Node;

import java.util.ArrayList;

public class _0428_Serialize_and_Deserialize_N_ary_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // record number of Children
    // pre-order, return current node
    class Solution_RecordNumberOfChildren {

        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if (root != null) {
                StringBuilder sb = new StringBuilder();
                preOrderSerialize(root, sb);
                return sb.toString();
            }
            return null;
        }

        private void preOrderSerialize(Node currentNode, StringBuilder sb) {
            sb.append(currentNode.val).append(","); // first visit current node
            if (currentNode.children == null) {
                sb.append(0).append(","); // append # of children as 0 if there is no children
            } else {
                sb.append(currentNode.children.size()).append(","); // append # of children
                for (Node child : currentNode.children) {
                    preOrderSerialize(child, sb);
                }
            }
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            return preOrderDeserialize(data.split(","), new int[] { 0 });
        }

        private Node preOrderDeserialize(String[] data, int[] index) {
            Node node = new Node(Integer.parseInt(data[index[0]]));
            ++index[0];
            node.children = new ArrayList<>();
            int numOfChildren = Integer.parseInt(data[index[0]]);
            for (int i = 0; i < numOfChildren; ++i) {
                ++index[0];
                node.children.add(preOrderDeserialize(data, index));
            }
            return node;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Embrace children nodes in []
    class Solution_DFS {

        // Encodes a tree to a single string.
        public String serialize(Node root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        private void serialize(Node node, StringBuilder sb) {
            if (node != null) {
                sb.append(node.val);
                sb.append("[");
                if (node.children != null) {
                    for (Node child : node.children) {
                        serialize(child, sb);
                    }
                }
                sb.append("]");
            }
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }
            return deserialize(data, 0, data.length());
        }

        private Node deserialize(String data, int left, int right) {
            int start = left;
            // Find value
            while (Character.isDigit(data.charAt(start))) {
                ++start;
            }
            // Create new node
            Node node = new Node(Integer.parseInt(data.substring(left, start)));
            node.children = new ArrayList<>();
            // Check whether the node has children
            if (++start < --right) {
                // Create child nodes
                int count = 0;
                int index = start;
                while (index < right) {
                    if (data.charAt(index) == '[') {
                        ++index;
                        ++count;
                    } else if (data.charAt(index) == ']') {
                        ++index;
                        --count;
                    } else {
                        // if the current char is digit
                        ++index;
                        continue;
                    }

                    // when found a closure, do a deeper recursion
                    if (count == 0) {
                        if (start < index) {
                            node.children.add(deserialize(data, start, index));
                            start = index;
                        }
                    }
                }
            }
            return node;
        }
    }

}