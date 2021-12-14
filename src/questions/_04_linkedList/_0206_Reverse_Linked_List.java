package questions._04_linkedList;

import common.ListNode;

/*

Given the head of a singly linked list, reverse the list, and return the reversed list.


Example 1:
    Input:
        head = [1,2,3,4,5]
    Output:
        [5,4,3,2,1]
Example 2:
    Input:
        head = [1,2]
    Output:
        [2,1]
Example 3:
    Input:
        head = []
    Output:
        []


Constraints:
    The number of nodes in the list is the range [0, 5000].
    -5000 <= Node.val <= 5000


Follow up:
    A linked list can be reversed either iteratively or recursively. Could you implement both?

*/
public class _0206_Reverse_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Iterative1 {
        public ListNode reverseList(ListNode curr) {
            ListNode temp = new ListNode();
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = temp.next;
                temp.next = curr;
                curr = next;
            }
            return temp.next;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Iterative2 {
        public ListNode reverseList(ListNode curr) {
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Recursive {
        public ListNode reverseList(ListNode head) {
            return reverseList(null, head);
        }

        private ListNode reverseList(ListNode pre, ListNode curr) {
            if (curr != null) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = reverseList(curr, next);
            }
            return pre;
        }
    }

}