/**
 *  @author Yunxiang He
 *  @date 08/02/2018
 */

package questions.temp;

import common.ListNode;

/*

Given a sorted linked list, delete all duplicates such that each element appear only once.


Example 1:
    Input: 1->1->2
    Output: 1->2

Example 2:
    Input: 1->1->2->3->3
    Output: 1->2->3

*/

public class _0083_Remove_Duplicates_from_Sorted_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = head;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) { // if head.val == head.next.val, then jump head.next
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy;
    }
}
