/**
 *  @author Yunxiang He
 *  @date 12/23/2017
 */

package questions.temp;

import common.ListNode;

/*

Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
You may assume the integer do not contain any leading zero, except the number 0 itself.
The digits are stored such that the most significant digit is at the head of the list.


Example :
    Input: [1,2,3]
    Output: [1,2,4]

*/

public class _0369_Plus_One_Linked_List {


    public static void main(String[] args) {
        _0369_Plus_One_Linked_List test = new _0369_Plus_One_Linked_List();
        test.plusOne(new ListNode(9));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode plusOne(ListNode head) {
        ListNode l = new ListNode(1);
        l.next = head;
        return (helper(head) == 0) ? head : l;
    }

    public int helper(ListNode node) {
        if (node.next == null) {
            node.val += 1;
        } else {
            node.val += helper(node.next);
        }
        int val = node.val;
        node.val %= 10;
        return val / 10;
    }
}
