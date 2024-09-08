/**
 * @author: Yunxiang He
 * @date : 2018-07-17 01:34
 */

package questions.temp;

/*

The set S originally contains numbers from 1 to n. 
But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. 
Your task is to firstly find the number occurs twice and then find the number that is missing. 
Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]

Note:
The given array size will in the range [2, 10^4].
The given array's numbers won't have any order.

*/

public class __0645_Set_Mismatch {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        int start = 0;
        int sum = 0;
        while (start < nums.length) {
            int temp = start;
            int num = 0;
            while (nums[temp] != 0) {
                sum += nums[temp];
                int next = nums[temp] - 1;
                num = nums[temp];
                nums[temp] = 0;
                temp = next;
            }
            if (res[0] == 0 && start != temp) {
                res[0] = num;
            }
            start++;
        }
        res[1] = res[0] + (1 + nums.length) * nums.length / 2 - sum;
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] findErrorNums2(int[] nums) {
        int[] count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - 1]++;
        }

        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (count[i] == 0) {
                res[1] = i + 1;
            } else if (count[i] == 2) {
                res[0] = i + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        new __0645_Set_Mismatch().findErrorNums(new int[] { 8, 1, 4, 5, 7, 2, 3, 5, 6, 10 });
    }
}
