/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-09
 */

package coding.lintcode;

import common.ListNode;

/*

Reverse a linked list.


Example
    For linked list 1->2->3, the reversed linked list is 3->2->1


Challenge
    Reverse it in-place and in one-pass

*/

public class __0035_Reverse_Linked_List {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
