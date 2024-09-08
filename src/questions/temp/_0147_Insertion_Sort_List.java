/**
 * @author Yunxiang He
 * @date 03/16/2019
 */

package questions.temp;

import common.ListNode;

/*

Sort a linked list using insertion sort.
 

Algorithm of Insertion Sort:
    Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
    At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
    It repeats until no input elements remain.


Example 1:
    Input: 4->2->1->3
    Output: 1->2->3->4

Example 2:
    Input: -1->5->3->4->0
    Output: -1->0->3->4->5

*/

public class _0147_Insertion_Sort_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // record the node next of the next iteration
    // cut off the next of the curr node
    // insert the curr node int the sorted listnode from the head dummy
    //   if the curr node is greater or equal than the tail of the sorted node, append it to the tail.next, update the tail to be curr
    public ListNode insertionSortList(ListNode curr) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = null;
            if (tail.val <= curr.val) {
                tail.next = curr;
                tail = curr;
            } else {
                ListNode head = dummy;
                while (head.next != null && head.next.val < curr.val) {
                    head = head.next;
                }
                curr.next = head.next;
                head.next = curr;
            }
            curr = next;
        }
        return dummy.next;
    }
}
