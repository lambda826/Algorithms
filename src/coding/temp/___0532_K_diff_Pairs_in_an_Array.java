/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27 00:26
 */

package coding.temp;

import java.util.HashMap;
import java.util.Map;

/*

Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. 
Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

*/

public class ___0532_K_diff_Pairs_in_an_Array {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findPairs(int[] nums, int k) {
        if (nums.length < 2 || k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int n : nums) {
            // c == 0 denotes it's the first time to encounter n;
            int c = map.getOrDefault(n, 0);
            map.put(n, c + 1);
            // if it is the 2nd time to encounter n
            if (k == 0 && c == 1) {
                res++;
            } else if (k != 0 && c == 0) {
                if (map.containsKey(n - k)) {
                    res++;
                }
                if (map.containsKey(n + k)) {
                    res++;
                }
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findPairs2(int[] nums, int k) {
        if (nums.length < 2 || k < 0) {
            return 0;
        }
        Map<Integer, Integer> needed = new HashMap<>();
        int res = 0;
        for (int n : nums) {
            needed.put(n + k, needed.getOrDefault(n + k, 0) + 1);
        }
        for (int n : nums) {
            if (needed.containsKey(n)) {
                if (k == 0) {
                    if (needed.get(n) > 1) {
                        res++;
                        needed.remove(n);
                    }
                } else {
                    res++;
                    needed.remove(n);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ___0532_K_diff_Pairs_in_an_Array().findPairs(new int[] { 3, 1, 4, 1, 5 }, 0));
    }
}
