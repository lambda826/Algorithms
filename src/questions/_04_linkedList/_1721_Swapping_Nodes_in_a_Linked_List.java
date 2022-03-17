package questions._04_linkedList;

import common.ListNode;

/*

You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).


Example 1:
    Input:
        head = [1,2,3,4,5],
        k = 2
    Output:
        [1,4,3,2,5]

Example 2:
    Input:
        head = [7,9,6,6,7,8,3,0,9,5],
        k = 5
    Output:
        [7,9,6,6,8,7,3,0,9,5]


Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 10^5
    0 <= Node.val <= 100

 */
public class _1721_Swapping_Nodes_in_a_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public ListNode swapNodes(ListNode head, int k) {
            ListNode temp = new ListNode(0, head);
            ListNode fast = temp;
            ListNode slow = temp;
            while (--k > 0) {
                fast = fast.next;
            }
            ListNode node = fast;
            fast = fast.next;
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            // Swap
            ListNode target1 = node.next;
            ListNode target2 = slow.next;
            if (target1 != target2) {
                if (target1.next == target2) {
                    node.next = target2;
                    target1.next = target2.next;
                    target2.next = target1;
                } else if (target2.next == target1) {
                    slow.next = target1;
                    target2.next = target1.next;
                    target1.next = target2;
                } else {
                    ListNode next1 = target1.next;
                    ListNode next2 = target2.next;
                    node.next = target2;
                    slow.next = target1;
                    target1.next = next2;
                    target2.next = next1;
                }
            }
            return temp.next;
        }
    }
}
