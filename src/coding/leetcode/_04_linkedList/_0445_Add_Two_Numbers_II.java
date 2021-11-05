package coding.leetcode._04_linkedList;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

You are given two non-empty linked lists representing two non-negative integers.
The most significant digit comes first and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.


Example 1:
    Input:
        l1 = [7,2,4,3],
        l2 = [5,6,4]
    Output:
        [7,8,0,7]

Example 2:
    Input:
        l1 = [2,4,3],
        l2 = [5,6,4]
    Output:
        [8,0,7]

Example 3:
    Input:
        l1 = [0],
        l2 = [0]
    Output:
        [0]


Constraints:
    The number of nodes in each linked list is in the range [1, 100].
    0 <= Node.val <= 9
    It is guaranteed that the list represents a number that does not have leading zeros.

*/

public class _0445_Add_Two_Numbers_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. reverse two linkedlist
    // 2. add
    // 3. reverse the result list
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ll1 = reverse(null, l1);
        ListNode ll2 = reverse(null, l2);
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (ll1 != null || ll2 != null || carry != 0) {
            if (ll1 != null) {
                carry += ll1.val;
                ll1 = ll1.next;
            }
            if (ll2 != null) {
                carry += ll2.val;
                ll2 = ll2.next;
            }
            ListNode node = new ListNode(carry % 10);
            carry /= 10;
            curr.next = node;
            curr = curr.next;
        }
        return reverse(null, dummy.next);
    }

    private ListNode reverse(ListNode pre, ListNode curr) {
        if (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            return reverse(curr, next);
        }
        return pre;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // stack
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.addFirst(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.addFirst(l2.val);
            l2 = l2.next;
        }
        ListNode curr = null;
        ListNode next = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            if (!stack1.isEmpty()) {
                carry += stack1.removeFirst();
            }
            if (!stack2.isEmpty()) {
                carry += stack2.removeFirst();
            }
            curr = new ListNode(carry % 10);
            carry /= 10;
            curr.next = next;
            next = curr;
        }
        return curr;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. padding 0 to the front of the shorter linkedlist
    // 2. recursively add the two lists
    class Solution {

        private int carry = 0;

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int count1 = 0;
            int count2 = 0;
            ListNode ll1 = l1;
            ListNode ll2 = l2;
            while (ll1 != null) {
                ll1 = ll1.next;
                ++count1;
            }
            while (ll2 != null) {
                ll2 = ll2.next;
                ++count2;
            }
            if (count1 > count2) {
                l2 = padding(l2, count1 - count2);
            } else {
                l1 = padding(l1, count2 - count1);
            }
            ListNode res = add(l1, l2);
            if (carry != 0) {
                ListNode listNode = new ListNode(1);
                listNode.next = res;
                res = listNode;
            }
            return res;
        }

        private ListNode add(ListNode l1, ListNode l2) {
            ListNode node = new ListNode();
            if (l1.next != null) {
                node.next = add(l1.next, l2.next);
            }
            carry += l1.val + l2.val;
            node.val = carry % 10;
            carry /= 10;
            return node;
        }

        private ListNode padding(ListNode l, int count) {
            ListNode head = new ListNode(0);
            head.next = l;
            while (count-- > 0) {
                ListNode node = new ListNode(0);
                node.next = head.next;
                head.next = node;
            }
            return head.next;
        }
    }
}