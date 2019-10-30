/**
 *  @author Yunxiang He
 *  @date 03/17/2019
 */

package coding.temp;

import common.ListNode;

/*

Given a linked list, rotate the list to the right by k places, where k is non-negative.


Example 1:
    Input: 1->2->3->4->5->NULL, k = 2
    Output: 4->5->1->2->3->NULL
    Explanation:
    rotate 1 steps to the right: 5->1->2->3->4->NULL
    rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:
    Input: 0->1->2->NULL, k = 4
    Output: 2->0->1->NULL
    Explanation:
    rotate 1 steps to the right: 2->0->1->NULL
    rotate 2 steps to the right: 1->2->0->NULL
    rotate 3 steps to the right: 0->1->2->NULL
    rotate 4 steps to the right: 2->0->1->NULL

*/

public class _0061_Rotate_List {

    public static void main(String[] args) {
        _0061_Rotate_List test = new _0061_Rotate_List();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        test.rotateRight(l1, 2);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // rotate to right k ==> rotate to left len - k
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            ++len;
        }
        k %= len;
        if (k == 0) {
            return head;
        } else {
            int n = len - k;
            temp = head;
            while (--n > 0) {
                temp = temp.next;
            }
            ListNode _temp = temp.next;
            ListNode _head = _temp;
            temp.next = null;
            while (_temp.next != null) {
                _temp = _temp.next;
            }
            _temp.next = head;
            return _head;
        }
    }
}
