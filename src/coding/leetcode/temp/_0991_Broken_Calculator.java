/**
 *  @author Yunxiang He
 *  @date 02/09/2019
 */

package coding.leetcode.temp;

/*

On a broken calculator that has a number showing on its display, we can perform two operations:

Double: Multiply the number on the display by 2, or;
Decrement: Subtract 1 from the number on the display.
Initially, the calculator is displaying the number X.

Return the minimum number of operations needed to display the number Y.

 

Example 1:

Input: X = 2, Y = 3
Output: 2
Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
Example 2:

Input: X = 5, Y = 8
Output: 2
Explanation: Use decrement and then double {5 -> 4 -> 8}.
Example 3:

Input: X = 3, Y = 10
Output: 3
Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
Example 4:

Input: X = 1024, Y = 1
Output: 1023
Explanation: Use decrement operations 1023 times.
 

Note:

1 <= X <= 10^9
1 <= Y <= 10^9

*/

public class _0991_Broken_Calculator {

    public static void main(String[] args) {
        System.out.println(new _0991_Broken_Calculator().brokenCalc(1024, 10));
    }

    int min = Integer.MAX_VALUE;

    public int brokenCalc(int X, int Y) {
        if (Y == X) {
            return 0;
        } else if (Y < X) {
            return X - Y;
        } else {
            DFS(0, X, Y, 0);
            return min;
        }
    }

    private int DFS(int pre, int curr, int target, int step) {
        if (curr == target) {
            return step;
        } else if (curr > target) {
            return step + curr - target;
        } else {
            int n1 = DFS(curr, curr * 2, target, step + 1);
            min = Math.min(min, n1);
            for (int i = curr - 1, j = 1; i > pre; --i, j++) {
                int n2 = DFS(curr, curr - j, target, step + j);
                min = Math.min(min, n2);
            }
            return min;
        }
    }
}
