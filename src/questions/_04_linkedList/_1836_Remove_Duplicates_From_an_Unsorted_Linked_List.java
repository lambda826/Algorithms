package questions._04_linkedList;

import common.ListNode;

import java.util.HashMap;
import java.util.Map;

/*

Given the head of a linked list, find all the values that appear more than once in the list and delete the nodes that have any of those values.
Return the linked list after the deletions.


Example 1:
    Input:
        head = [1,2,3,2]
    Output:
        [1,3]
    Explanation:
        2 appears twice in the linked list, so all 2's should be deleted. After deleting all 2's, we are left with [1,3].

Example 2:
    Input:
        head = [2,1,1,2]
    Output:
        []
    Explanation:
        2 and 1 both appear twice. All the elements should be deleted.

Example 3:
    Input:
        head = [3,2,2,1,3,2,4]
    Output:
        [1,4]
    Explanation:
        3 appears twice and 2 appears three times. After deleting all 3's and 2's, we are left with [1,4].


Constraints:
    The number of nodes in the list is in the range [1, 10^5]
    1 <= Node.val <= 10^5

*/
public class _1836_Remove_Duplicates_From_an_Unsorted_Linked_List {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public ListNode deleteDuplicatesUnsorted(ListNode head) {
            Map<Integer, Integer> freq = new HashMap<>();
            ListNode temp = new ListNode();
            ListNode res = temp;
            temp.next = head;
            while (head != null) {
                freq.put(head.val, freq.getOrDefault(head.val, 0) + 1);
                head = head.next;
            }
            while (temp.next != null) {
                if (freq.get(temp.next.val) > 1) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }
            return res.next;
        }
    }
}