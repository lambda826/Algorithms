/**
 *  @author Yunxiang He
 *  @date 05/22/2018
 */

package questions._10_tree.bst;

import common.TreeNode;

/*

Convert a BST to a sorted circular doubly-linked list in-place. 
Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

 
We want to transform this BST into a circular doubly linked list. 
Each TreeNode in a doubly linked list has a predecessor and successor. 
For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. 
The "head" symbol means the TreeNode it points to is the smallest element of the linked list.

 
Specifically, we want to do the transformation in place. 
After the transformation, the left pointer of the tree TreeNode should point to its predecessor, and the right pointer should point to its successor. 
We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. 
The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

*/

public class _0426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(8);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);
        TreeNode t6 = new TreeNode(7);
        TreeNode t8 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t5.left = t7;
        t5.right = t8;
        TreeNode res = new _0426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List().treeToDoublyList(t1);
        TreeNode temp = res;
        do {
            System.out.println(temp.val);
            temp = temp.right;
        } while (temp != res);
        do {
            System.out.println(temp.val);
            temp = temp.left;
        } while (temp != res);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // inorder
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode dummy = new TreeNode(0);
        pre = dummy;
        inorder(root);
        pre.right = dummy.right;
        dummy.right.left = pre;
        return dummy.right;
    }

    private TreeNode pre;

    private void inorder(TreeNode node) {
        if (node != null) {
            // left
            inorder(node.left);
            // visit
            node.left = pre;
            pre.right = node;
            pre = node;
            // right
            inorder(node.right);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode treeToDoublyList2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode[] left = new TreeNode[] { root, root };
        TreeNode[] right = new TreeNode[] { root, root };
        if (root.left != null) {
            left = traverse(root.left, true);
            root.left = left[1];
            left[1].right = root;
        }
        if (root.right != null) {
            right = traverse(root.right, false);
            root.right = right[0];
            right[0].left = root;
        }
        left[0].left = right[1];
        right[1].right = left[0];
        return left[0];
    }

    private TreeNode[] traverse(TreeNode node, boolean isLeft) {
        TreeNode[] _left;
        TreeNode[] _right;
        if (node.left != null) {
            _left = traverse(node.left, true);
            node.left = _left[1];
            _left[1].right = node;
        } else {
            _left = new TreeNode[] { node, node };
        }
        if (node.right != null) {
            _right = traverse(node.right, false);
            node.right = _right[0];
            _right[0].left = node;
        } else {
            _right = new TreeNode[] { node, node };
        }
        return new TreeNode[] { _left[0], _right[1] };
    }

}
