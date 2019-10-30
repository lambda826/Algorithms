/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package coding.lintcode;

import common.RandomListNode;

/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Challenge
Could you solve it with O(1) space?

*/

public class __0105_Copy_List_with_Random_Pointer {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        RandomListNode p = head;
        while (p != null) {
            RandomListNode copied = new RandomListNode(p.label);
            copied.next = p.next;
            p.next = copied;
            p = p.next.next;
        }
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        RandomListNode dummy = new RandomListNode(0);
        dummy.next = head;
        p = dummy;
        while (p != null && p.next != null) {
            RandomListNode original = p.next;
            p.next = original.next;
            original.next = original.next.next;
            p = p.next;
        }
        return dummy.next;
    }
}
