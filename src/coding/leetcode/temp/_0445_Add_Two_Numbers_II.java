/**
 *  @author Yunxiang He
 *  @date 12/23/2017
 */

package coding.leetcode.temp;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*

You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.


Example:
    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 8 -> 0 -> 7


Follow up:
    What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

*/

public class _0445_Add_Two_Numbers_II {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(4);
        l5.next = l6;
        l6.next = l7;

        ListNode res = new _0445_Add_Two_Numbers_II().addTwoNumbers2(l1, l5);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }

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
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        // 1
        ListNode _l1 = l1;
        ListNode _l2 = l2;
        int c1 = 0;
        while (_l1 != null) {
            c1++;
            _l1 = _l1.next;
        }
        int c2 = 0;
        while (_l2 != null) {
            c2++;
            _l2 = _l2.next;
        }
        int d = c1 - c2;
        ListNode temp;
        if (d < 0) {
            while (d != 0) {
                temp = new ListNode(0);
                temp.next = l1;
                l1 = temp;
                d++;
            }
        } else {
            while (d != 0) {
                temp = new ListNode(0);
                temp.next = l2;
                l2 = temp;
                d--;
            }
        }
        // 2
        ListNode l = new ListNode(1);
        ListNode next = add(l1, l2);
        l.next = next;
        return carry == 1 ? l : next;
    }

    private int carry = 0;

    private ListNode add(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        if (l1.next != null) {
            node.next = add(l1.next, l2.next);
        }
        carry += l1.val + l2.val;
        node.val = carry % 10;
        carry /= 10;
        return node;
    }

}
