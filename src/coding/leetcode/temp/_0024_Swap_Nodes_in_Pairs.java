/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.leetcode.temp;

import common.ListNode;

/*

Given a linked list, swap every two adjacent nodes and return its curr.


Example:
    Given 1->2->3->4, you should return the list as 2->1->4->3.


Note:
    Your algorithm should use only constant extra space.
    You may not modify the values in the list's nodes, only nodes itself may be changed.

*/

public class _0024_Swap_Nodes_in_Pairs {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode swapPairs(ListNode curr) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = curr;
        while (curr != null && curr.next != null) {
            ListNode next = curr.next;
            pre.next = next;
            curr.next = curr.next.next;
            next.next = curr;
            pre = curr;
            curr = curr.next;
        }
        return dummy.next;
    }
}
