/**
 *  @author Yunxiang He
 *  @date 10/11/2017
 */

package questions.temp;

import common.ListNode;

/*

Reverse a singly linked list.


Example:
    Input: 1->2->3->4->5->NULL
    Output: 5->4->3->2->1->NULL


Follow up:
    A linked list can be reversed either iteratively or recursively. Could you implement both?

*/

public class _0206_Reverse_Linked_List {

    public static void main(String[] args) {
        _0206_Reverse_Linked_List test = new _0206_Reverse_Linked_List();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(22);
        ListNode l3 = new ListNode(333);
        l1.next = l2;
        l2.next = l3;
        System.out.println(test.reverseList2(l1).val);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode reverseList2(ListNode head) {
        return reverseList2(null, head);
    }

    private ListNode reverseList2(ListNode pre, ListNode curr) {
        if (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            return reverseList2(curr, next);
        }
        return pre;
    }

}
