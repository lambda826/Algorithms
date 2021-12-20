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
            ListNode temp = head;
            int len = 0;
            while (temp != null) {
                temp = temp.next;
                ++len;
            }
            if (len == 0 || (k %= len) == 0) {
                return head;
            } else {
                ListNode res = new ListNode(0);
                ListNode temp2 = res;
                res.next = head;
                k = len - k;
                while (k-- > 0) {
                    temp2 = temp2.next;
                }
                res.next = temp2.next;
                temp2.next = null;
                temp2 = res;
                while (temp2.next != null) {
                    temp2 = temp2.next;
                }
                temp2.next = head;
                return res.next;
            }
        }
    }
}