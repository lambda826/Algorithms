/**
 *  @author Yunxiang He
 *  @date 02/16/2019
 */

package coding.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Give two Lists, give a maximum value, find a bunch in each of the two lists, and find all the pairs that are closest to the maximum but not larger than the maximum.


Example
    Give a=[2,3,4,5,6], b=[4,5,7], x=8', return [[3,5],[4,4]].
    Explanation:
    the sum of [3,5] or [4,4] is 8, which is no larger than 8. 

    Give a=[2,3,4,5,6], b=[4,5,7], x=10', return [[3,7],[5,5],[6,4]].
    Explanation:
    the sum of [3,7],[5,5],[6,4] is 10, which is no larger than 10. 


Notice
    The length of the two lists do not exceed 100000100000.
    Each element do not exceed 10000000001000000000.

*/

public class __1635_Max_Pair {

    public static void main(String[] args) {
        int[] a = { 2, 2 };
        int[] b = { 2 };
        int x = 4;
        new __1635_Max_Pair().getAns(a, b, x);
        Arrays.asList(1, 2);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sort the array a and b
    // Iterate two pointers from the head of a and tail of b
    // Record the sum of the two pointer
    // Record the max sum that is no greater than target
    // Iterate again to get the records from the two arrays who sum up to target
    public int[][] getAns(int[] a, int[] b, int x) {
        Arrays.parallelSort(a);
        Arrays.parallelSort(b);
        int i = 0;
        int j = b.length - 1;
        int max = Integer.MIN_VALUE;
        int sum;
        while (i < a.length && j >= 0) {
            sum = a[i] + b[j];
            if (sum <= x) {
                max = Math.max(max, sum);
                i++;
            } else {
                j--;
            }
        }
        i = 0;
        j = b.length - 1;
        List<int[]> list = new ArrayList<>();
        while (i < a.length && j >= 0) {
            sum = a[i] + b[j];
            if (sum == max) {
                list.add(new int[] { a[i], b[j] });
                int _i = i;
                int _j = j;
                while (++_i < a.length && a[i] == a[_i]) {
                    list.add(new int[] { a[_i], b[j] });
                }
                while (--_j >= 0 && b[j] == b[_j]) {
                    list.add(new int[] { a[i], b[_j] });
                }
                i = _i;
                j = _j;
            } else if (sum < max) {
                i++;
            } else {
                j--;
            }
        }
        int[][] res = new int[list.size()][2];
        for (int n = 0; n < list.size(); ++n) {
            res[n] = list.get(n);
        }
        return res;
    }
}
