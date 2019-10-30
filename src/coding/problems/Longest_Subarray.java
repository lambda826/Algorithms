/**
 *  @author: Yunxiang He
 *  @date  : 2018-11-11
 */

package coding.problems;

import java.util.Arrays;
import java.util.List;

public class Longest_Subarray {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int maxLength(List<Integer> a, int k) {
        int sum = 0;
        int maxLength = 0;

        for (int i = 0, j = 0; j < a.size(); j++) {
            sum += a.get(j);
            while (sum > k) {
                sum -= a.get(i);
                i++;
            }
            maxLength = Math.max(maxLength, j - i);
        }

        return maxLength + 1;
    }

    public static void main(String[] args) {
        System.out.println(maxLength(Arrays.asList(new Integer[] { 1, 2, 3 }), 3));
        System.out.println(maxLength(Arrays.asList(new Integer[] { 3, 1, 2, 3 }), 4));
        System.out.println(maxLength(Arrays.asList(new Integer[] { 4, 3, 1, 2, 1 }), 4));
    }
}
