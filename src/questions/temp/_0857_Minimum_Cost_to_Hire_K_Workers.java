/**
 *  @author Yunxiang He
 *  @date 03/28/2019
 */

package questions.temp;

import java.util.Arrays;
import java.util.PriorityQueue;

/*

There are N workers.  
The i-th worker has a quality[i] and a minimum wage expectation wage[i].
Now we want to hire exactly K workers to form a paid group.  
When hiring a group of K workers, we must pay them according to the following rules:
    Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
    Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.


Example 1:
    Input: quality = [10,20,5], wage = [70,50,30], K = 2
    Output: 105.00000
    Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.

Example 2:
    Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
    Output: 30.66667
    Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 


Note:
    1 <= K <= N <= 10^4, where N = quality.length = wage.length
    1 <= quality[i] <= 10^4
    1 <= wage[i] <= 10^4
    Answers within 10^-5 of the correct answer will be considered correct.

*/

public class _0857_Minimum_Cost_to_Hire_K_Workers {

    public static void main(String[] args) {
        int[] quality = { 3, 1, 10, 10, 1 };
        int[] wage = { 4, 8, 2, 2, 7 };
        int K = 3;
        _0857_Minimum_Cost_to_Hire_K_Workers test = new _0857_Minimum_Cost_to_Hire_K_Workers();
        test.mincostToHireWorkers(quality, wage, K);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 工资必须要与工作能力成正比，也就是说，每个工人的工资是一个常数p与其工作能力的乘积；
    // 那么为了花最少的钱，我们一定会选一个最小的常数p，这个p其实就是每个工人的单位工作能力的价格；
    // 因为不能低于最低工资标准，所以我们一定要选择全部K个工人当中的最大的p，才能使得工资不低于所有人各自的最低工资标准；
    // 在选定某一个p之后，我们选出来单位工作能力价格小于或者等于p的所有工人，在这些工人当中选出工作能力最低的K个工人，花费的价格就是就是在当前p的条件下最低的费用，我们遍历所有的p之后，就能得到最低花费。
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        double[][] workers = new double[n][2]; // (ratio, quality)
        for (int i = 0; i < n; ++i) {
            workers[i] = new double[] { (double) wage[i] / quality[i], (double) quality[i] };
        }
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0])); // ratio ASC
        PriorityQueue<Double> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b, a)); // quality DESC
        double total = Double.MAX_VALUE;
        double totalQuality = 0;
        for (double[] worker : workers) {
            totalQuality += worker[1];
            maxHeap.offer(worker[1]);
            if (maxHeap.size() > K) {
                totalQuality -= maxHeap.poll();
            }
            if (maxHeap.size() == K) {
                total = Math.min(total, totalQuality * worker[0]);
            }
        }
        return total;
    }
}
