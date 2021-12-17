package questions._23_bitManipulation;


import common.ListNode;

/*
Given head which is a reference node to a singly-linked list.
The value of each node in the linked list is either 0 or 1.
The linked list holds the binary representation of a number.
Return the decimal value of the number in the linked list.


Example 1:
    Input:
        head = [1,0,1]
    Output:
        5
    Explanation:
        (101) in base 2 = (5) in base 10
Example 2:
    Input:
        head = [0]
    Output:
        0


Constraints:
    The Linked List is not empty.
    Number of nodes will not exceed 30.
    Each node's value is either 0 or 1.

*/
public class _1290_Convert_Binary_Number_in_a_Linked_List_to_Integer {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public int getDecimalValue(ListNode head) {
            int num = 0;
            while (head != null) {
                num = (num << 1) | head.val;
                head = head.next;
            }
            return num;
        }
    }
}