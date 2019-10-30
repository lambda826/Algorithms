/**
 *  @author Yunxiang He
 *  @date 03/30/2019
 */

package coding.temp;

import java.util.HashMap;
import java.util.Map;

/*

You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. 
Return whether you can make such a split.


Example 1:
    Input: [1,2,3,3,4,5]
    Output: True
    Explanation:
    You can split them into two consecutive subsequences : 
    1, 2, 3
    3, 4, 5

Example 2:
    Input: [1,2,3,3,4,4,5,5]
    Output: True
    Explanation:
    You can split them into two consecutive subsequences : 
    1, 2, 3, 4, 5
    3, 4, 5

Example 3:
    Input: [1,2,3,4,4,5]
    Output: False

Note:
    The length of the input is in range of [1, 10000]

*/

public class _0659_Split_Array_into_Consecutive_Subsequences {

    public static void main(String[] args) {
        _0659_Split_Array_into_Consecutive_Subsequences test = new _0659_Split_Array_into_Consecutive_Subsequences();
        test.isPossible(new int[] { 1, 2, 3, 3, 4, 5 });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> appendFreq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        for (int n : nums) {
            if (freq.get(n) != 0) { // n is eat up
                if (appendFreq.getOrDefault(n, 0) > 0) {
                    // append to an exsiting consecutive sequence
                    appendFreq.put(n, appendFreq.get(n) - 1);
                    appendFreq.put(n + 1, appendFreq.getOrDefault(n + 1, 0) + 1);
                } else if (freq.getOrDefault(n + 1, 0) > 0 && freq.getOrDefault(n + 2, 0) > 0) {
                    // create a new consecutive sequence of 3 elements
                    freq.put(n + 1, freq.get(n + 1) - 1);
                    freq.put(n + 2, freq.get(n + 2) - 1);
                    appendFreq.put(n + 3, appendFreq.getOrDefault(n + 3, 0) + 1);
                } else {
                    return false;
                }
                freq.put(n, freq.get(n) - 1);
            }
        }
        return true;
    }
}
