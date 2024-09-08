/**
 * @author Yunxiang He
 * @date 12/30/2017
 */

package questions.temp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
Define a pair (u,v) which consists of one element from the first array and one element from the second array.
Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.


Example 1:
    Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
    Return: [1,2],[1,4],[1,6]
    The first 3 pairs are returned from the sequence:
    [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Example 2:
    Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
    Return: [1,1],[1,1]
    The first 2 pairs are returned from the sequence:
    [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

Example 3:
    Given nums1 = [1,2], nums2 = [3],  k = 3 
    Return: [1,3],[2,3]
    All possible pairs are returned from the sequence:
    [1,3],[2,3]

*/

public class _0373_Find_K_Pairs_with_Smallest_Sums {

    public static void main(String[] args) {
        new _0373_Find_K_Pairs_with_Smallest_Sums().kSmallestPairs(new int[] { 1, 1, 2, 4, 6 }, new int[] { 1, 2, 3, 4, 5 }, 10);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> (nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]));
        minHeap.offer(new int[] { 0, 0 });
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] arr = minHeap.poll();
            res.add(new int[] { nums1[arr[0]], nums2[arr[1]] });
            if (arr[0] < nums1.length - 1 && arr[1] == 0) { // index from nums1
                minHeap.offer(new int[] { arr[0] + 1, arr[1] });
            }
            if (arr[1] < nums2.length - 1) { // index from nums2
                minHeap.offer(new int[] { arr[0], arr[1] + 1 });
            }
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new LinkedList<>();
        final int N1 = nums1.length;
        final int N2 = nums2.length;
        if (N1 == 0 || N2 == 0) {
            return res;
        }
        int[] dp = new int[N1];
        int start = 0;
        for (int i = 0; i < k && start < N1; i++) {
            int curt = start;
            int min = nums1[start] + nums2[dp[start]];
            for (int j = start + 1; j < N1; j++) {
                if (dp[j] == dp[j - 1] && dp[j] == 0) {
                    break;
                }
                if (nums1[j] + nums2[dp[j]] < min) {
                    min = nums1[j] + nums2[dp[j]];
                    curt = j;
                }
            }
            res.add(new int[] { nums1[curt], nums2[dp[curt]] });
            if (++dp[curt] == N2) {
                start++;
            }
        }
        return res;
    }

}
