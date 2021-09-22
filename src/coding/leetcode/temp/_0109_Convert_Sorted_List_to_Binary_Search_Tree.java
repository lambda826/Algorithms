/**
 *  @author Yunxiang He
 *  @date 06/22/2018
 */

package coding.leetcode.temp;

import common.ListNode;
import common.TreeNode;

/*

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


Example:
	Given the sorted linked list: [-10,-3,0,5,9],
	One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
	
	      0
	     / \
	   -3   9
	   /   /
	 -10  5

*/

public class _0109_Convert_Sorted_List_to_Binary_Search_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode fast = head.next; // fast walk one step first
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next.next;
        TreeNode root = new TreeNode(slow.next.val);
        // cut
        slow.next.next = null;
        slow.next = null;
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(head2);
        return root;
    }
}
