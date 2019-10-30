/**
 *  @author Yunxiang He
 *  @date 12/18/2018
 */

package coding.temp;

import common.ListNode;

/*

Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.


Example:
    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4

*/

public class _0021_Merge_Two_Sorted_Lists {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(3);
        ListNode l13 = new ListNode(4);
        l11.next = l12;
        l12.next = l13;
        ListNode l21 = new ListNode(2);
        ListNode l22 = new ListNode(5);
        ListNode l23 = new ListNode(6);
        l21.next = l22;
        l22.next = l23;
        new _0021_Merge_Two_Sorted_Lists().mergeTwoLists(l11, l21);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                temp.next = l2;
                break;
            } else if (l2 == null) {
                temp.next = l1;
                break;
            } else if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        return dummy.next;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

}
