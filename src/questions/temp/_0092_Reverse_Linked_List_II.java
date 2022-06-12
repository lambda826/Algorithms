package questions.temp;

import common.ListNode;

/*

Given the head of a singly linked list and two integers left and right where left <= right,
reverse the nodes of the list from position left to position right, and return the reversed list.


Example 1:
    Input:
        head = [1,2,3,4,5],
        left = 2,
        right = 4
    Output:
        [1,4,3,2,5]

Example 2:
    Input:
        head = [5],
        left = 1,
        right = 1
    Output: [5]


Constraints:
    The number of nodes in the list is n.
    1 <= n <= 500
    -500 <= Node.val <= 500
    1 <= left <= right <= n

*/
public class _0092_Reverse_Linked_List_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find correct pre and curr position;
    // Insert curr.next into pre.next.
    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (left == right) {
                return head;
            }
            ListNode res = new ListNode();
            res.next = head;
            ListNode pre = res;
            ListNode curr = head;
            while (left > 1 && right > 1) {
                left--;
                right--;
                curr = curr.next;
                pre = pre.next;
            }
            while (right - 1 > 0) {
                right--;
                ListNode next = curr.next;
                curr.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            return res.next;
        }
    }
}
