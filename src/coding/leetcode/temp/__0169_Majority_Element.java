/**
 *  @author Yunxiang He
 *  @date Dec 16, 2017 4:07:40 AM
 */

package coding.leetcode.temp;

import java.util.Arrays;
import java.util.HashMap;

/*

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

*/

public class __0169_Majority_Element {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // One majority for one no-majority, the rest will be the majority
    public int majorityElement(int[] nums) {
        int n = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                n = nums[i];
                count++;
            } else if (n != nums[i]) {
                count--;
            } else {
                count++;
            }
        }
        return n;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int majorityElement_DC(int[] nums) {
        return major(nums, 0, nums.length - 1);
    }

    private int major(int[] nums, int left, int right) {
        // Base case
        if (left == right) {
            return nums[left];
        }
        // Divide
        int mid = left + (right - left) / 2;
        int lm = major(nums, left, mid);
        int rm = major(nums, mid + 1, right);
        // Conquer
        if (lm == rm) {
            return rm;
        }
        return getCount(nums, left, mid, lm) > getCount(nums, mid + 1, right, rm) ? lm : rm;
    }

    private int getCount(int[] nums, int left, int right, int val) {
        int cnt = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == val) {
                cnt++;
            }
        }
        return cnt;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int majorityElement_Sort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int majorityElement3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) > nums.length / 2) {
                res = x;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        __0169_Majority_Element test = new __0169_Majority_Element();
        test.majorityElement(new int[] { 3, 2, 3 });
    }
}
