/**
 *  @author Yunxiang He
 *  @date 02/14/2019
 */

package coding.leetcode.temp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*

Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. 
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. 
If it doesn't exist, output -1 for this number.


Example 1:
    Input: [1,2,1]
    Output: [2,-1,2]
    Explanation: The first 1's next greater number is 2; 
    The number 2 can't find next greater number; 
    The second 1's next greater number needs to search circularly, which is also 2.


Note: 
    The length of given array won't exceed 10000.

*/

public class _0503_Next_Greater_Element_II {

    public static void main(String[] args) {
        new _0503_Next_Greater_Element_II().nextGreaterElements(new int[] { 1, 2, 1 });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> deque = new ArrayDeque<>(); // store the index
        int[] res = new int[nums.length];
        if (nums.length == 0) {
            return res;
        }
        Arrays.fill(res, -1);
        for (int i = 0; i < nums.length * 2; ++i) { // go through next element
            int ii = i % nums.length;
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[ii]) {
                res[deque.pollLast()] = nums[ii];
            }
            deque.offerLast(ii);
        }
        return res;
    }
}
