package questions._04_linkedList;

import common.ListNode;

/*

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle.
Note that pos is not passed as a parameter.

Do not modify the linked list.


Example 1:
    Input:
        head = [3,2,0,-4],
        pos = 1
    Output:
        tail connects to node index 1
    Explanation:
        There is a cycle in the linked list, where tail connects to the second node.

Example 2:
    Input:
        head = [1,2],
        pos = 0
    Output:
        tail connects to node index 0
    Explanation:
        There is a cycle in the linked list, where tail connects to the first node.

Example 3:
    Input:
        head = [1],
        pos = -1
    Output:
        no cycle
    Explanation:
        There is no cycle in the linked list.


Constraints:
    The number of the nodes in the list is in the range [0, 10^4].
    -10^5 <= Node.val <= 105
    pos is -1 or a valid index in the linked-list.


Follow up:
    Can you solve it using O(1) (i.e. constant) memory?

*/

public class _0142_Linked_List_Cycle_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Assume:
    //      X: from beginning to cycle entry
    //      Y: from cycle entry to meet point
    //      Z: from meet point to cycle entry
    // We have:
    //      slow: (X + Y) * 2 = fast: X + Y + Z + Y
    //      Then: 2X = X + Z => X = Z
    //      Which means the distance from beginning to cycle entry equals the distance from meet point to cycle entry
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    ListNode temp = head;
                    while (temp != slow) {
                        temp = temp.next;
                        slow = slow.next;
                    }
                    return temp;
                }
            }
            return null;
        }
    }
}