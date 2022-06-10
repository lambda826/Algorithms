package questions._04_linkedList;

import common.Node;

/*

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
    struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.


Example 1:
    Input:
        root = [1,2,3,4,5,6,7]
    Output:
        [1,#,2,3,#,4,5,6,7,#]
    Explanation:
        Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
        The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

Example 2:
    Input:
        root = []
    Output:
        []

*/
public class _0116_Populating_Next_Right_Pointers_in_Each_Node {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public Node connect(Node root) {
            Node curr = root;
            while (curr != null && curr.left != null) {
                Node next = curr.left;
                while (curr != null && curr.next != null) {
                    curr.left.next = curr.right;
                    curr.right.next = curr.next.left;
                    curr = curr.next;
                }
                curr.left.next = curr.right;
                curr = next;
            }
            return root;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Recursion {
        public Node connect(Node root) {
            Node temp = root;
            if (root != null && root.left != null) {
                Node next;
                do {
                    next = root.left;
                    while (root != null) {
                        if (root.left != null) {
                            root.left.next = root.right;
                        }
                        if (root.next != null) {
                            root.right.next = root.next.left;
                        }
                        root = root.next;
                    }
                } while (root != null);
                if (next != null) {
                    connect(next);
                }
            }
            return temp;
        }
    }

}
