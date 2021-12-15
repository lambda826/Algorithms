package questions._04_linkedList;

import common.ListNode;

/*

Given the head of a singly linked list, return true if it is a palindrome.


Example 1:
    Input:
        head = [1,2,2,1]
    Output:
        true
Example 2:
    Input:
        head = [1,2]
    Output:
        false


Constraints:
    The number of nodes in the list is in the range [1, 10^5].
    0 <= Node.val <= 9


Follow up:
    Could you do it in O(n) time and O(1) space?

*/
public class _0234_Palindrome_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Utilize fast and slow pointers to find mid;
    // 2. Reverse the 2nd half;
    // 3. Compare head and mid;
    // 4. Restore 2nd half.
    class Solution {
        public boolean isPalindrome(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            slow.next = reverse(slow.next);
            ListNode mid = slow.next;
            boolean isPalindrome = true;
            while (mid != null) {
                if (mid.val != head.val) {
                    isPalindrome = false;
                    break;
                }
                mid = mid.next;
                head = head.next;
            }
            slow.next = reverse(slow.next);
            return isPalindrome;
        }


        private ListNode reverse(ListNode curr) {
            ListNode temp = null;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = temp;
                temp = curr;
                curr = next;
            }
            return temp;
        }
    }
}