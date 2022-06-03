package questions._04_linkedList;

import common.ListNode;

/*

Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.


Example 1:
    Input:
        head = [1,2,3,4,5],
        k = 2
    Output:
        [2,1,4,3,5]

Example 2:
    Input:
        head = [1,2,3,4,5],
        k = 3
    Output:
        [3,2,1,4,5]

Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 5000
    0 <= Node.val <= 1000


Follow-up:
    Can you solve the problem in O(1) extra memory space?

*/

public class _0025_Reverse_Nodes_in_k_Group {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Recursion {
        public ListNode reverseKGroup(ListNode head, int k) {
            int _k = k;
            ListNode temp = head;
            while (temp != null && _k > 0) {
                temp = temp.next;
                --_k;
            }
            if (_k == 0) {
                ListNode pre = reverseKGroup(temp, k);
                // Reverse
                while (k-- > 0) {
                    ListNode next = head.next;
                    head.next = pre;
                    pre = head;
                    head = next;
                }
                return pre;
            } else {
                return head;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode res = new ListNode();
            res.next = head;

            boolean flag = true;
            ListNode pre = res;
            while (flag) {
                ListNode temp = pre;
                ListNode _pre = pre.next;
                int _k = k;
                while (temp.next != null && _k > 0) {
                    temp = temp.next;
                    --_k;
                }
                flag = _k == 0;
                if (flag) {
                    _k = k;
                    ListNode curr = pre.next;
                    pre.next = temp.next;
                    while (_k-- > 0) {
                        ListNode next = curr.next;
                        curr.next = pre.next;
                        pre.next = curr;
                        curr = next;
                    }
                    pre = _pre;
                }
            }
            return res.next;
        }

    }
}
