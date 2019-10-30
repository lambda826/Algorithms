/**
 * @author Yunxiang He
 * @date 10/09/2017
 */

package coding.temp;

import common.ListNode;

/*

Sort a linked list in O(n log n) time using constant space complexity.


Example 1:
    Input: 4->2->1->3
    Output: 1->2->3->4

Example 2:
    Input: -1->5->3->4->0
    Output: -1->0->3->4->5

*/

public class _0148_Sort_List {

    public static void main(String[] args) {
        _0148_Sort_List test = new _0148_Sort_List();
        ListNode n1 = new ListNode(11);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(14);
        ListNode n4 = new ListNode(13);
        ListNode n5 = new ListNode(12);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        ListNode res = test.sortList(n1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = divide(head);
        ListNode head2 = mid.next;
        // cut the list off
        mid.next = null;
        return merge(sortList(head), sortList(head2));
    }

    private ListNode divide(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

}
