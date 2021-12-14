package questions._04_linkedList;

import common.ListNode;

/*

Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
Return the linked list sorted as well.


Example 1:
    Input:
        head = [1,2,3,3,4,4,5]
    Output:
        [1,2,5]

Example 2:
    Input:
        head = [1,1,1,2,3]
    Output:
        [2,3]


Constraints:
    The number of nodes in the list is in the range [0, 300].
    -100 <= Node.val <= 100
    The list is guaranteed to be sorted in ascending order.

*/
public class _0082_Remove_Duplicates_from_Sorted_List_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Check if there is a duplicate nodes.
    //      If yes, store this value into the temporal duplication variable (dup).
    //      Remove the next node if it has the same value as duplication variable.
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode tempHead = new ListNode();
            tempHead.next = head;
            ListNode curr = tempHead;
            int dup = Integer.MAX_VALUE;
            while (curr.next != null) {
                if (curr.next.next != null && curr.next.val == curr.next.next.val) {
                    dup = curr.next.val;
                }
                if (curr.next.val == dup) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
            return tempHead.next;
        }
    }
}