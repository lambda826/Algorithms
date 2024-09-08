/**
 * @author: Yunxiang He
 * @date : 2018-10-29
 */

package questions.problems;

import java.util.PriorityQueue;

public class Merge_Score {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 每次merge最小的两个球
    public static int mergerScore(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }

        while (pq.size() > 1) {
            pq.add(pq.remove() + pq.remove());
        }
        return pq.remove();
    }

    public static void main(String[] args) {
        System.out.println(mergerScore(new int[] { 4, 3, 2, 5, 1 }));
    }
}
