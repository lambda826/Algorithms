/**
 *  @author Yunxiang He
 *  @date 03/17/2019
 */

package questions.temp;

import common.ListNode;

/*

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. 
If pos is -1, then there is no cycle in the linked list.


Example 1:
    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
    Input: head = [1,2], pos = 0
    Output: tail connects to node index 0
    Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
    Input: head = [1], pos = -1
    Output: no cycle
    Explanation: There is no cycle in the linked list.


Note: 
    Do not modify the linked list.


Follow up:
    Can you solve it without using extra space?

*/

public class _0142_Linked_List_Cycle_II {

    public static void main(String[] args) {
        _0142_Linked_List_Cycle_II test = new _0142_Linked_List_Cycle_II();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        l2.next = l1;
        test.detectCycle(l1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCircle = false;
        while (fast != null && fast.next != null && !hasCircle) {
            if ((slow = slow.next) == (fast = fast.next.next)) {
                hasCircle = true;
            }
        }
        if (hasCircle) {
            ListNode slow2 = head;
            while ((slow) != slow2) {
                slow = slow.next;
                slow2 = slow2.next;
            }
            return slow;
        } else {
            return null;
        }
    }
}
