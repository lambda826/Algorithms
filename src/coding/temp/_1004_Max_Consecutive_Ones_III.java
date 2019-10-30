/**
 *  @author Yunxiang He
 *  @date 03/12/2019
 */

package coding.temp;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 
 

Example 1:
    Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
    Output: 6
    Explanation: 
    [1,1,1,0,0,1,1,1,1,1,1]
    Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Example 2:
    Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
    Output: 10
    Explanation: 
    [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
    Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 

Note:
    1 <= A.length <= 20000
    0 <= K <= A.length
    A[i] is 0 or 1 

*/

public class _1004_Max_Consecutive_Ones_III {

    public static void main(String[] args) {
        _1004_Max_Consecutive_Ones_III test = new _1004_Max_Consecutive_Ones_III();
        test.longestOnes(new int[] { 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 }, 2);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestOnes(int[] A, int K) {
        int l = 0;
        int r = 0;
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestOnes2(int[] A, int K) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(-1);
        int max = 0;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == 0) {
                deque.offerLast(i);
                if (deque.size() == K + 2) {
                    max = Math.max(max, deque.peekLast() - deque.pollFirst() - 1);
                }
            }
        }
        return Math.max(max, A.length - deque.pollFirst() - 1);
    }
}
