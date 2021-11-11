/**
 *  @author Yunxiang He
 *  @date 03/17/2019
 */

package questions.temp;

import common.ListNode;

/*

Given a ListNode from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. 
The given ListNode can be a reference to any single ListNode in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. 
After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given ListNode is null), you should create a new single cyclic list and return the reference to that single ListNode. 
Otherwise, you should return the original given ListNode.

*/

public class _0708_Insert_into_a_Cyclic_Sorted_List {

    public static void main(String[] args) {
        _0708_Insert_into_a_Cyclic_Sorted_List test = new _0708_Insert_into_a_Cyclic_Sorted_List();
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l1;
        ListNode head = l1;
        test.insert(head, 2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. walk a circle first to check whether every node has the same value
    // 2. check whether the value can be inserted after the tail
    // 3. walk from the head, find the right place to insert
    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            return new ListNode(insertVal, null);
        }
        ListNode dummy = head;
        ListNode tail = head;
        // 1
        do {
            tail = tail.next;
        } while (tail != head && tail.val <= tail.next.val);
        // 2
        if (tail.val == tail.next.val || (insertVal >= tail.val || insertVal <= tail.next.val)) {
            insert(tail, new ListNode(insertVal, null));
        } else {
            // 3
            head = tail.next;
            while (true) {
                if (insertVal >= head.val && insertVal <= head.next.val) {
                    insert(head, new ListNode(insertVal, null));
                    break;
                }
                head = head.next;
            }
        }
        return dummy;
    }

    private void insert(ListNode curr, ListNode insert) {
        insert.next = curr.next;
        curr.next = insert;
    }
}
