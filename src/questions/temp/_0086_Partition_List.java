/**
 * @author Yunxiang He
 * @date 03/17/2019
 */

package questions.temp;

import common.ListNode;

/*

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.


Example:
    Input: head = 1->4->3->2->5->2, x = 3
    Output: 1->2->2->4->3->5

*/

public class _0086_Partition_List {

    public static void main(String[] args) {
        _0086_Partition_List test = new _0086_Partition_List();
        ListNode l1 = new ListNode(1);
        ListNode l4 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l22 = new ListNode(2);
        l1.next = l4;
        l4.next = l3;
        l3.next = l2;
        l2.next = l5;
        l5.next = l22;
        test.partition(l1, 3);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode curr1 = dummy1;
        ListNode curr2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                curr1.next = head;
                curr1 = head;
            } else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}
