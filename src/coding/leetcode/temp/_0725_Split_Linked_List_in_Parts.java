/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.leetcode.temp;

import common.ListNode;

/*

Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. 
This may lead to some parts being null.
The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.
Return a List of ListNode's representing the linked list parts that are formed.


Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]

Example 1:
	Input: 
		root = [1, 2, 3], k = 5
	Output: 
		[[1],[2],[3],[],[]]
	Explanation:
		The input and each element of the output are ListNodes, not arrays.
		For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
		The first element output[0] has output[0].val = 1, output[0].next = null.
		The last element output[4] is null, but it's string representation as a ListNode is [].

Example 2:
	Input: 
		root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
	Output: 
		[[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
	Explanation:
		The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.

Note:
	The length of root will be in the range [0, 1000].
	Each value of a node in the input will be an integer in the range [0, 999].
	k will be an integer in the range [1, 50].

*/

public class _0725_Split_Linked_List_in_Parts {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        new _0725_Split_Linked_List_in_Parts().splitListToParts(l1, 3);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        ListNode temp = root;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        temp = root;
        double i;
        int j = 0;
        ListNode end = null;
        while (len != 0) {
            i = Math.ceil((double) len / k--);
            len -= i;
            res[j++] = temp;
            while (i-- != 0) {
                end = temp;
                temp = temp.next;
            }
            end.next = null;
        }
        return res;
    }
}
