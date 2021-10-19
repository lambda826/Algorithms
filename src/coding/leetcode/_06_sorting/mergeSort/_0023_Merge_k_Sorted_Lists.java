package coding.leetcode._06_sorting.mergeSort;

import common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.


Example 1:
    Input:
        lists = [[1,4,5],[1,3,4],[2,6]]
    Output:
        [1,1,2,3,4,4,5,6]
    Explanation:
        The linked-lists are:
            [
              1->4->5,
              1->3->4,
              2->6
            ]
            merging them into one sorted list:
            1->1->2->3->4->4->5->6

Example 2:
    Input:
        lists = []
    Output:
        []

Example 3:
    Input:
        lists = [[]]
    Output:
        []


Constraints:
    k == lists.length
    0 <= k <= 10000
    0 <= lists[i].length <= 500
    -10000 <= lists[i][j] <= 10000
    lists[i] is sorted in ascending order.
    The sum of lists[i].length won't exceed 10000.

*/

public class _0023_Merge_k_Sorted_Lists {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Merge Sort:
    // 1. Partition: until it cannot be further partition (single list)
    // 2. Merge: merge two sorted list
    class Solution_MergeSort {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return partition(lists, 0, lists.length - 1);
        }

        private ListNode partition(ListNode[] lists, int lo, int hi) {
            if (lo == hi) {
                return lists[lo];
            } else {
                int mid = lo + (hi - lo) / 2;
                ListNode left = partition(lists, lo, mid);
                ListNode right = partition(lists, mid + 1, hi);
                return merge(left, right);
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
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Heap (Priority Queue)
    // 1. Enqueue every head of all the linked list into the min heap;
    // 2. Poll the peek (min node) and append to current pointer;
    // 2.1. Enqueue the next node of this node.
    //
    // Lesson learned:
    //      Comparator.comparingInt(ToIntFunction<? super T> keyExtractor)
    class Solution_Heap {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }

            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
            for (ListNode listNode : lists) {
                if (listNode != null) {
                    pq.offer(listNode);
                }
            }

            ListNode head = new ListNode(0);
            ListNode temp = head;
            while (!pq.isEmpty()) {
                temp.next = pq.poll();
                temp = temp.next;
                if (temp.next != null) {
                    pq.offer(temp.next);
                }
            }
            return head.next;
        }
    }

}