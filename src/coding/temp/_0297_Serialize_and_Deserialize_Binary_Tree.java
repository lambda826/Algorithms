/**
 *  @author Yunxiang He
 *  @date 01/27/2018
 */

package coding.temp;

import common.TreeNode;

/*

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.


For example, you may serialize the following tree
        1
       / \
      2   3
         / \
        4   5
    as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. 
    You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.


Note:
    Do not use class member/global/static variables to store states. 
    Your serialize and deserialize algorithms should be stateless.


*/

public class _0297_Serialize_and_Deserialize_Binary_Tree {

    public static void main(String[] args) {
        Integer[] nums = { 5, 2, 3, null, null, 2, 4, 3, 1 };
        TreeNode root = TreeNode.array2Tree(nums);
        _0297_Serialize_and_Deserialize_Binary_Tree test = new _0297_Serialize_and_Deserialize_Binary_Tree();
        String data = test.serialize(root);
        test.serialize(test.deserialize(data));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
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
