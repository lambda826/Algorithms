/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package questions.temp;

import common.ListNode;

/*

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.


Example:
    Given this linked list: 1->2->3->4->5
    For k = 2, you should return: 2->1->4->3->5
    For k = 3, you should return: 3->2->1->4->5


Note:
    Only constant extra memory is allowed.
    You may not alter the values in the list's nodes, only nodes itself may be changed.

*/

public class _0025_Reverse_Nodes_in_k_Group {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        new _0025_Reverse_Nodes_in_k_Group().reverseKGroup(l1, 2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // get the head and tail of the segment, revserse the segment, if the tail is null, then don't reverse
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode tail = null;
        pre.next = head;
        while ((tail = getSegmentTai(head, k)) != null) {
            ListNode next = tail.next;
            // reverse[head, tail]
            reverse(head, tail.next);
            pre.next = tail;
            head.next = next;
            pre = head;
            head = next;
        }
        return dummy.next;
    }

    private void reverse(ListNode head, ListNode tail_next) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail_next) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    private ListNode getSegmentTai(ListNode node, int k) {
        // walk (k - 1) steps
        while (--k > 0 && node != null) {
            node = node.next;
        }
        return node;
    }
}
