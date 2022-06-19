package questions._06_sorting.mergeSort;

import common.ListNode;

/*

Given the head of a linked list, return the list after sorting it in ascending order.


Example 1:
    Input:
        head = [4,2,1,3]
    Output:
        [1,2,3,4]

Example 2:
    Input:
        head = [-1,5,3,4,0]
    Output:
        [-1,0,3,4,5]

Example 3:
    Input:
        head = []
    Output:
        []


Constraints:
    The number of nodes in the list is in the range [0, 5 * 10^4].
    -10^5 <= Node.val <= 10^5


Follow up:
    Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

*/

public class _0148_Sort_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            } else {
                // Find mid to partition
                ListNode slow = head;
                ListNode fast = head;
                while (fast.next != null && fast.next.next != null) {
                    slow = slow.next;
                    fast = fast.next.next;
                }
                ListNode next = slow.next;
                // Cut
                slow.next = null;
                return merge(sortList(head), sortList(next));
            }
        }

        private ListNode merge(ListNode node1, ListNode node2) {
            if (node1 == null) {
                return node2;
            } else if (node2 == null) {
                return node1;
            } else if (node1.val < node2.val) {
                node1.next = merge(node1.next, node2);
                return node1;
            } else {
                node2.next = merge(node1, node2.next);
                return node2;
            }
        }
    }

}
