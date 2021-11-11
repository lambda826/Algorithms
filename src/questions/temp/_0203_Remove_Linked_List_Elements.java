/**
 *  @author Yunxiang He
 *  @date 12/19/2017
 */

package questions.temp;

import common.ListNode;

/*

Remove all elements from a linked list of integers that have value val.


Example
    Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
    Return: 1 --> 2 --> 3 --> 4 --> 5

*/

public class _0203_Remove_Linked_List_Elements {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode removeElements_Iterative(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        } else if (head.val != val) {
            head.next = removeElements2(head.next, val);
            return head;
        } else {
            return removeElements2(head.next, val);
        }
    }
}
