package questions._04_linkedList;

import common.ListNode;

/*

You are given the head of a singly linked-list. The list can be represented as:

    L0 → L1 → … → Ln - 1 → Ln
    Reorder the list to be on the following form:

    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    You may not modify the values in the list's nodes. Only nodes themselves may be changed.


Example 1:
    Input:
        head = [1,2,3,4]
    Output:
        [1,4,2,3]
Example 2:
    Input:
        head = [1,2,3,4,5]
    Output:
        [1,5,2,4,3]


Constraints:
    The number of nodes in the list is in the range [1, 5 * 10^4].
    1 <= Node.val <= 1000

*/

public class _0143_Reorder_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        ListNode temp;
        boolean flag = false;

        public void reorderList(ListNode head) {
            temp = head;
            helper(null, head);
        }

        private void helper(ListNode pre, ListNode curr) {
            if (curr != null) {
                helper(curr, curr.next);
                flag = flag || temp == curr || temp.next == curr;
                if (!flag) {
                    pre.next = null;
                    insert(curr);
                }
            }
        }

        private void insert(ListNode node) {
            node.next = temp.next;
            temp.next = node;
            temp = temp.next.next;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Reverse 2nd half
    // 2. Append zigzag
    class Solution_Iterative {

        public void reorderList(ListNode head) {
            if (head != null && head.next != null) {
                ListNode slow = head;
                ListNode fast = head;
                // Find mid
                while (fast.next != null && fast.next.next != null) {
                    slow = slow.next;
                    fast = fast.next.next;
                }
                ListNode head2 = slow.next;
                slow.next = null;
                ListNode head1 = head;
                // Reverse 2nd half
                head2 = reverse(null, head2);
                // Link
                while (head1 != null && head2 != null) {
                    ListNode next1 = head1.next;
                    ListNode next2 = head2.next;
                    head1.next = head2;
                    head2.next = next1;
                    head1 = next1;
                    head2 = next2;
                }
            }
        }

        private ListNode reverse(ListNode pre, ListNode curr) {
            if (curr != null) {
                ListNode next = curr.next;
                curr.next = pre;
                return reverse(curr, next);
            }
            return pre;
        }
    }
}
