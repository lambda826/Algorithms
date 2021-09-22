/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.leetcode.temp;

import common.ListNode;

/*

Given a singly linked list, determine if it is a palindrome.


Example 1:
    Input: 1->2
    Output: false

Example 2:
    Input: 1->2->2->1
    Output: true


Follow up:
    Could you do it in O(n) time and O(1) space?

*/

public class _0234_Palindrome_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // find mid
    // reverse the 2nd half
    // compare
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // find the mid (find the beginning of the 2nd half)
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next;

        // reverse the 2nd half
        ListNode pre = null;
        ListNode next = null;
        while (slow != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        // compare
        while (pre != null && head != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        return true;
    }
}
