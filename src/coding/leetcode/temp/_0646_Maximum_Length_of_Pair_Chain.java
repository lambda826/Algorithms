/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.leetcode.temp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*

You are given n pairs of numbers. 
In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. 
Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. 
You needn't use up all the given pairs. 
You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]

Note:
The number of given pairs will be in the range [1, 1000].

 */

public class _0646_Maximum_Length_of_Pair_Chain {

    private Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[1] < o2[1]) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Interval Scheduling (Greedy)
    // Smallest finish time
    // Use PriorityQueue
    public int findLongestChain_Greedy(int[][] pairs) {
        //        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        for (int[] i : pairs) {
            pq.add(i);
        }
        int count = 1;
        int[] i = pq.poll();
        int[] j = null;
        while (pq.size() > 0) {
            if ((j = pq.poll())[0] > i[1]) {
                count++;
                i = j;
            }
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sort the array
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, comparator);
        int count = 1;
        int end = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > end) {
                count++;
                end = pairs[i][1];
            }
        }
        return count;
    }
}
