/**
 *  @author Yunxiang He
 *  @date 08/05/2018
 */

package coding.leetcode.temp;

/*

N couples sit in 2N seats arranged in a row and want to hold hands. 
We want to know the minimum number of swaps so that every couple is sitting side by side. 
A swap consists of choosing any two people, then they stand up and switch seats.

The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, 
the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).

The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.


Example 1:
    Input: row = [0, 2, 1, 3]
    Output: 1
    Explanation: We only need to swap the second (row[1]) and third (row[2]) person.

Example 2:
    Input: row = [3, 2, 0, 1]
    Output: 0
    Explanation: All couples are already seated side by side.


Note:
    len(row) is even and in the range of [4, 60].
    row is guaranteed to be a permutation of 0...len(row)-1.

*/

public class _0765_Couples_Holding_Hands {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minSwapsCouples(int[] row) {
        int count = 0;
        int[] couples = new int[row.length];
        for (int i = 0; i < row.length; i += 2) {
            couples[row[i]] = row[i + 1];
            couples[row[i + 1]] = row[i];
        }
        for (int i = 0; i < row.length; i += 2) {
            if (couples[i] != i + 1) {
                couples[couples[i]] = couples[i + 1];
                couples[couples[i + 1]] = couples[i];
                count++;
            }
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minSwapsCouples_Greedy(int[] row) {
        int count = 0;
        int[] seats = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            seats[row[i]] = i;
        }
        for (int one = 0; one < row.length; one = one + 2) {
            int other;
            if ((row[one] & 1) == 1) {
                other = row[one] - 1;
            } else {
                other = row[one] + 1;
            }
            if (row[one + 1] != other) {
                swap(row, seats, one + 1, other);
                count++;
            }
        }
        return count;
    }

    private void swap(int[] couple, int[] seats, int one, int other) {
        int coup = seats[other];
        int temp = couple[one];
        couple[one] = couple[coup];
        couple[coup] = temp;
        int _temp = seats[temp];
        seats[temp] = seats[other];
        seats[other] = _temp;
    }

    public static void main(String[] args) {
        System.out.println(new _0765_Couples_Holding_Hands().minSwapsCouples(new int[] { 0, 2, 1, 3, }));
    }
}
