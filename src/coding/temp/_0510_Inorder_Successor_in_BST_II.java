/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package coding.temp;

import algorithms.tree.BST;
import common.TreeNode;

/*

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
The successor of a node p is the node with the smallest key greater than p.val.
You will have direct access to the node but not to the root of the tree. 
Each node will have a reference to its parent node.


Note:
    If the given node has no in-order successor in the tree, return null.
    It's guaranteed that the values of the tree are unique.
    Remember that we are using the Node type instead of TreeNode type so their string representation are different.

*/

public class _0510_Inorder_Successor_in_BST_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode inorderSuccessor(TreeNode node) {
        return BST.successor(node);
    }
}
