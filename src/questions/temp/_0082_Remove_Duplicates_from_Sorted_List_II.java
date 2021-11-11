/**
 *  @author Yunxiang He
 *  @date 03/17/2019
 */

package questions.temp;

import common.ListNode;

/*

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.


Example 1:
    Input: 1->2->3->3->4->4->5
    Output: 1->2->5

Example 2:
    Input: 1->1->1->2->3
    Output: 2->3

*/

public class _0082_Remove_Duplicates_from_Sorted_List_II {

    public static void main(String[] args) {
        _0082_Remove_Duplicates_from_Sorted_List_II test = new _0082_Remove_Duplicates_from_Sorted_List_II();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l33 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l44 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l33;
        l33.next = l4;
        l4.next = l44;
        l44.next = l5;
        test.deleteDuplicates(l1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // check the next nodes of the current node
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        dummy.next = head;
        while (curr.next != null && curr.next.next != null) {
            ListNode n1 = curr.next;
            ListNode n2 = curr.next.next;
            if (n1.val != n2.val) {
                curr = curr.next;
            } else {
                while (n2 != null && n1.val == n2.val) {
                    n2 = n2.next;
                }
                curr.next = n2;
            }
        }
        return dummy.next;
    }
}
