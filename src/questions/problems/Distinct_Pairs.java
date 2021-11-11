/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-12
 */

package questions.problems;

import java.util.Arrays;

public class Distinct_Pairs {

    public static int countTwoSum(int[] arry, int target) {
        Arrays.sort(arry);
        int left = 0;
        int right = arry.length - 1;
        int count = 0;
        while (left < right) {
            while (arry[left] == arry[left + 1]) {
                left++;
            }
            while (arry[right] == arry[right - 1]) {
                right--;
            }
            if (left < right && arry[left] + arry[right] == target) {
                count++;
                left++;
                right--;
            } else if (arry[left] + arry[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        if ((target & 1) == 0) {
            int half = target >> 1;
            int c = 0;
            for (int i : arry) {
                if (i == half) {
                    c++;
                    if (c == 2) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countTwoSum(new int[] { 1, 2, 3, 6, 7, 8, 9, 1 }, 10));
        System.out.println(countTwoSum(new int[] { 1, 3, 46, 1, 3, 9 }, 47));
        System.out.println(countTwoSum(new int[] { 6, 6, 3, 9, 3, 5, 1 }, 12));
        System.out.println(countTwoSum(new int[] { 1, 2, 3, 4, 5, 6, 5, 5, 4, 7, 8, 9, 6, 2, 1, 3 }, 10));
    }
}