/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package coding.company.amazon.debug;

import java.util.Arrays;

public class Array {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArray(new int[] { 1, 4, 3, 2, 5 })));
    }

    public static int[] sortArray(int arr[]) {
        int len = arr.length;
        int small, pos, i, j, temp;
        for (i = 0; i <= len - 1; i++) {
            for (j = i; j < len; j++) {
                temp = 0;
                if (arr[i] < arr[j]) { ////////////////////////////
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
