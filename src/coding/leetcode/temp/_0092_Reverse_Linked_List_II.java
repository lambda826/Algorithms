/**
 *  @author Yunxiang He
 *  @date 03/17/2019
 */

package coding.leetcode.temp;

import common.ListNode;

/*

Reverse a linked list from position m to n. 
Do it in one-pass.


Example:
    Input: 1->2->3->4->5->NULL, m = 2, n = 4
    Output: 1->4->3->2->5->NULL

Note: 
    1 ≤ m ≤ n ≤ length of list.

*/

public class _0092_Reverse_Linked_List_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find the start point
    // reverse until the end point
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (m > 1) {
            head = head.next;
            --n;
            --m;
        }
        ListNode h = head.next;
        ListNode curr = h;
        ListNode pre = null;
        ListNode next = null;
        // walk n steps, pre is the reversed list, curr is the next node
        while (n-- > 0) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        head.next = pre;
        h.next = curr;
        return dummy.next;
    }
}
