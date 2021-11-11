/**
 *  @author Yunxiang He
 *  @date 03/12/2019
 */

package questions.temp;

/*

Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:
    Input: [1,0,1,1,0]
    Output: 4
    Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
        After flipping, the maximum number of consecutive 1s is 4.


Note:
    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000


Follow up:
    What if the input numbers come in one by one as an infinite stream? 
    In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. 
    Could you solve it efficiently?

*/

public class _0487_Max_Consecutive_Ones_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findMaxConsecutiveOnes(int[] A) {
        int l = 0;
        int r = 0;
        int K = 1;
        while (r < A.length) {
            if (A[r++] == 0) {
                --K;
            }
            if (K < 0 && A[l++] == 0) {
                ++K;
            }
        }
        return r - l;
    }

}
