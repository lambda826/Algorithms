/**
 *  @author Yunxiang He
 *  @date Dec 17, 2017 11:43:59 PM
 */

package coding.temp;

import java.util.HashMap;
import java.util.TreeSet;

/*

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.


Example 1:
    Input: nums = [1,2,3,1], k = 3, t = 0
    Output: true

Example 2:
    Input: nums = [1,0,1,1], k = 1, t = 2
    Output: true

Example 3:
    Input: nums = [1,5,9,1,5,9], k = 2, t = 3
    Output: false

*/

public class _0220_Contains_Duplicate_III {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) {
                return true;
            }

            // Find the predecessor of current element
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        boolean f = !(t == 0);
        t = f ? t : 1;
        for (int i = 0; i < nums.length; i++) {
            long n = (((long) nums[i]) + Integer.MAX_VALUE) / t;
            if (map.containsKey(n)) {
                return true;
            }
            if (map.containsKey(n - 1) && f) {
                if (Math.abs(map.get(n - 1) - nums[i]) <= t) {
                    return true;
                }
            }
            if (map.containsKey(n + 1) && f) {
                if (Math.abs(map.get(n + 1) - nums[i]) <= t) {
                    return true;
                }
            }
            if (i >= k) {
                Long key = (((long) nums[i - k]) + Integer.MAX_VALUE) / t;
                map.remove(key);
            }
            map.put(n, nums[i]);
        }
        return false;
    }
}
