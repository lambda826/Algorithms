/**
 *  @author Yunxiang He
 *  @date Jan 3, 2018 3:00:18 PM
 */

package coding.temp;

/*

Nearly every one have used the Multiplication Table. 
But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1   2   3
2   4   6
3   6   9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).

Example 2:
Input: m = 2, n = 3, k = 6
Output: 
Explanation: 
The Multiplication Table:
1   2   3
2   4   6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).

Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]

 */

public class _0668_Kth_Smallest_Number_in_Multiplication_Table {
    /**
     * Solution:
     * Binary Seach
     * --------------------------------------------------------------
     * Tips:
     * There are many kinds of counting approaches
     * Here only two are listed
     * --------------------------------------------------------------
     *  Time complexity : O(Max(m, n) * log(hi - lo))
     * Space complexity : O(1)
     */
    public int findKthNumber(int m, int n, int k) {
        int lo = 1;
        int hi = m * n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = count2(mid, m, n);
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    //    private int count1(int mid, int m, int n) {
    //        int count = 0;
    //        for (int i = 1; i <= m; i++) {
    //            count += Math.min(n, mid / i);
    //        }
    //        return count;
    //    }

    private int count2(int mid, int m, int n) {
        int count = 0;
        int i = 1;
        int j = m;
        while (i <= n && j >= 1) {
            if (i * j <= mid) {
                count += j;
                i++;
            } else {
                j--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        _0668_Kth_Smallest_Number_in_Multiplication_Table test = new _0668_Kth_Smallest_Number_in_Multiplication_Table();
        int m = 10;
        int n = 5;
        int k = 14;
        System.out.println(test.findKthNumber(m, n, k));
    }
}
