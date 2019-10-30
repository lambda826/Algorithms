/**
 *  @author Yunxiang He
 *  @date 06/27/2018
 */

package coding.temp;

import common.ListNode;

import java.util.PriorityQueue;

/*

Merge k sorted linked lists and return it as one sorted list. 
Analyze and describe its complexity.

Example:
    Input:
        [
          1->4->5,
          1->3->4,
          2->6
        ]
    Output: 
        1->1->2->3->4->4->5->6

*/

public class _0023_Merge_k_Sorted_Lists {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // merge sort
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeSort(lists, 0, lists.length - 1);
    }

    private ListNode mergeSort(ListNode[] lists, int from, int to) {
        if (from < to) {
            return merge(mergeSort(lists, from, (from + to) / 2), mergeSort(lists, (from + to) / 2 + 1, to));
        } else {
            return lists[from];
        }
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (ListNode l : lists) {
            if (l != null) {
                minHeap.offer(l);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode l = minHeap.poll();
            temp.next = l;
            temp = temp.next;
            if (l.next != null) {
                minHeap.offer(l.next);
            }
        }
        return head.next;
    }

}
