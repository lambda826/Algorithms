/**
 *  @author Yunxiang He
 *  @date 04/12/2019
 */

package coding.temp;

import common.TreeNode;

/*

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary search tree. 
    There is no restriction on how your serialization/deserialization algorithm should work. 
    You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.


Note: 
    Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

*/

public class _0449_Serialize_and_Deserialize_BST {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        sb.append(",");
        if (node == null) {
            sb.append("#");
        } else {
            sb.append(node.val);
            serialize(node.left, sb);
            serialize(node.right, sb);
        }
    }

    public TreeNode deserialize(String data) {
        String[] dd = data.split(",");
        int[] i = { 1 };
        return deserialize(dd, i);
    }

    private TreeNode deserialize(String[] dd, int[] i) {
        if (dd[i[0]].equals("#")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(dd[i[0]]));
            i[0]++;
            node.left = deserialize(dd, i);
            i[0]++;
            node.right = deserialize(dd, i);
            return node;
        }
    }
}
