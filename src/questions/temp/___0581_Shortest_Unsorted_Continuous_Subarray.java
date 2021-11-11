/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27 03:25
 */

package questions.temp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

*/

public class ___0581_Shortest_Unsorted_Continuous_Subarray {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 一次遍历，左、右同时进行；
    // 左边前进记录当前经过元素的最大值，若按照升序规则，则当前遍历元素即为当前最大值；如果二者不相等，则用j记录当前前进的索引；
    // 右边后退记录当前经过元素的最小值，按照升序规则，则当前遍历元素即为当前最小值；如果二者不相等，则用i记录当前后退的索引。
    // 当一次遍历完成，前进的索引记录了不符合升序规则的最大索引，后退的索引记录了不符合规则的最小索引。
    // 注意在给i和j赋初值的时候要考虑数组元素全部按升序排序的情况，返回为0。所以，赋值i和j为不大于0且相差1，如：i = 0, j = -1，或i = -1, j = -2
    public int findUnsortedSubarray_WithoutExtraSpace(int[] nums) {
        int L = 0;
        int R = -1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int l = 0, r = nums.length - 1; r >= 0; l++, r--) {
            max = Math.max(max, nums[l]);
            if (nums[l] != max) {
                R = l;
            }
            min = Math.min(min, nums[r]);
            if (nums[r] != min) {
                L = r;
            }
        }
        return (R - L + 1);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findUnsortedSubarray_Stack(int[] nums) {
        Deque<Integer> Q = new ArrayDeque<>();
        int L = nums.length - 1;
        int R = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            Q.add(i);
            if (nums[Q.getLast()] > nums[i + 1]) {
                while (Q.size() > 0 && nums[Q.getLast()] > nums[i + 1]) {
                    L = Math.min(L, Q.removeLast());
                }
            }
        }
        Q.clear();
        for (int i = nums.length - 1; i > 0; i--) {
            Q.add(i);
            if (nums[Q.getLast()] < nums[i - 1]) {
                while (Q.size() > 0 && nums[Q.getLast()] < nums[i - 1]) {
                    R = Math.max(R, Q.removeLast());
                }
            }
        }
        return R - L > 0 ? R - L + 1 : 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findUnsortedSubarray_Sort(int[] nums) {
        int[] _nums = Arrays.copyOf(nums, nums.length);
        //        int[] _nums = nums.clone();
        Arrays.sort(nums);
        int L = 0;
        int R = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != _nums[i]) {
                L = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= L; i--) {
            if (nums[i] != _nums[i]) {
                R = i;
                break;
            }
        }
        return R - L + 1;
    }

    public static void main(String[] args) {
        ___0581_Shortest_Unsorted_Continuous_Subarray test = new ___0581_Shortest_Unsorted_Continuous_Subarray();
        //        int[] nums = new int[] { 2, 6, 4, 8, 10, 9, 15 };
        //        int[] nums2 = new int[] { 4, 2, 2, 3, 2, 2, };
        int[] nums3 = new int[] { 1, 3, 5, 4, 2 };
        int res = test.findUnsortedSubarray_WithoutExtraSpace(nums3);
        System.out.println(res);
    }
}
