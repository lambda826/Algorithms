/**
 *  @author Yunxiang He
 *  @date 03/17/2019
 */

package coding.leetcode.temp;

import common.ListNode;

/*

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
You may not modify the values in the list's nodes, only nodes itself may be changed.


Example 1:
    Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:
    Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

*/

public class _0143_Reorder_List {

    public static void main(String[] args) {
        _0143_Reorder_List test = new _0143_Reorder_List();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        test.reorderList(l1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // reverse the second half
    // append zigzag
    public void reorderList(ListNode head) {
        if (head != null && head.next != null) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode head2 = slow.next;
            slow.next = null;
            ListNode head1 = head;
            head2 = reverse(null, head2);
            while (head1 != null && head2 != null) {
                ListNode next1 = head1.next;
                ListNode next2 = head2.next;
                head1.next = head2;
                head2.next = next1;
                head1 = next1;
                head2 = next2;
            }
        }
    }

    private ListNode reverse(ListNode pre, ListNode curr) {
        if (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            return reverse(curr, next);
        }
        return pre;
    }
}
