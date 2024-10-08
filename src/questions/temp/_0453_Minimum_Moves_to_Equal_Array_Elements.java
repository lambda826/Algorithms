/**
 * @author: Yunxiang He
 * @date : 2018-07-16 10:40
 */

package questions.temp;

/*

Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.


Example:
    Input:
        [1,2,3]
    Output:
        3
    Explanation:
        Only three moves are needed (remember each move increments two elements):
        [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

*/

public class _0453_Minimum_Moves_to_Equal_Array_Elements {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minMoves(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            sum += nums[i];
        }
        return sum - min * nums.length;
    }
}
