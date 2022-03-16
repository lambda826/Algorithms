package questions._04_linkedList;

import common.ListNode;

/*

Given the head of a linked list, rotate the list to the right by k places.


Example 1:
    Input:
        head = [1,2,3,4,5],
        k = 2
    Output:
        [4,5,1,2,3]
Example 2:
    Input:
        head = [0,1,2],
        k = 4
    Output:
        [2,0,1]


Constraints:
    The number of nodes in the list is in the range [0, 500].
    -100 <= Node.val <= 100
    0 <= k <= 2 * 10^9

*/
public class _0061_Rotate_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k == 0) {
                return head;
            }
            // 1. Compute the length of the linked list.
            ListNode oldTail = head;
            int len = 1;
            while (oldTail.next != null) {
                oldTail = oldTail.next;
                ++len;
            }
            // 2. Attach tail to head.
            oldTail.next = head;
            // 3. Find newTail: (len - k - 1)th node
            k = len - k % len - 1;
            ListNode newTail = head;
            while (k-- > 0) {
                newTail = newTail.next;
            }
            // 4. Find newHead: (len - k)th node.
            ListNode newHead = newTail.next;
            // 5. Detach
            newTail.next = null;
            return newHead;

        }
    }
}