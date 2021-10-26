/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-30 03:13
 */

package coding.leetcode.temp;

import java.util.Arrays;
import java.util.Random;

/*

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:
1 <= w.length <= 10^4
1 <= w[i] <= 10^5
pickIndex will be called at most 10^4 times.

Example 1:
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]

Example 2:
Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]

Explanation of Input Syntax:
The input is two lists: the subroutines called and their arguments. 
Solution's constructor has one argument, the array w. pickIndex has no arguments. 
Arguments are always wrapped with a list, even if there aren't any.

*/

public class _0528_Random_Pick_with_Weight {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] arr;

    public _0528_Random_Pick_with_Weight(int[] w) {
        arr = new int[w.length];
        arr[0] = w[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int target = new Random().nextInt(arr[arr.length - 1]) + 1;
        int res = Arrays.binarySearch(arr, target);
        return res >= 0 ? res : -res - 1;
    }


}
