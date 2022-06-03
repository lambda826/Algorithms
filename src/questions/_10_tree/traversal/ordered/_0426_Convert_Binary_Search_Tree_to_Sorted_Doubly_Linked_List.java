package questions._10_tree.traversal.ordered;

import common.Node;

/*

Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place.
After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor.
You should return the pointer to the smallest element of the linked list.


Example 1:
    Input:
        root = [4,2,5,1,3]
    Output:
        [1,2,3,4,5]
    Explanation:
        The figure below shows the transformed BST.
        The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example 2:
    Input:
        root = [2,1,3]
    Output:
        [1,2,3]


Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -1000 <= Node.val <= 1000
    All the values of the tree are unique.

*/
public class _0426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // inorder
    class Solution {

        private Node pre;
        private Node head;

        public Node treeToDoublyList(Node root) {
            if (root != null) {
                inorder(root);
                head.left = pre;
                pre.right = head;
            }
            return head;
        }

        private void inorder(Node node) {
            if (node != null) {
                treeToDoublyList(node.left);
                if (head == null) {
                    head = node;
                }
                if (pre != null) {
                    pre.right = node;
                    node.left = pre;
                }
                pre = node;
                treeToDoublyList(node.right);
            }
        }
    }

}
