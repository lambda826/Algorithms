package coding.problems;

import java.util.Arrays;

public class Minimun_Unique_Array_Sum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int minUniqueArry(int[] arr) {
        Arrays.sort(arr);
        int pre = arr[0];
        int sum = pre;
        //		int cur = arr[1];
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            if (pre >= cur) {
                cur = pre + 1;
            }
            pre = cur;
            sum += pre;
        }
        return sum;
    }

}
