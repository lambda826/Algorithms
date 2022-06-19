package questions._04_linkedList;

import common.ListNode;
import common.TreeNode;

/*

Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


Example 1:
    Input:
        head = [-10,-3,0,5,9]
    Output:
        [0,-3,9,-10,null,5]
    Explanation:
        One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.

Example 2:
    Input:
        head = []
    Output:
        []

*/
public class _0109_Convert_Sorted_List_to_Binary_Search_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            if (head == null) {
                return null;
            }
            return sortedListToBST(head, null);
        }

        private TreeNode sortedListToBST(ListNode head, ListNode tail) {
            if (head == tail || head == null) {
                return null;
            } else {
                ListNode slow = head;
                ListNode fast = head;
                while (fast != tail && fast.next != tail) {
                    slow = slow.next;
                    fast = fast.next.next;
                }
                TreeNode root = new TreeNode(slow.val);
                root.left = sortedListToBST(head, slow);
                root.right = sortedListToBST(slow.next, tail);
                return root;
            }
        }
    }
}
