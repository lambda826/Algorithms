/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-16 11:11
 */

package questions.temp;

import java.util.HashSet;
import java.util.Set;

/*

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: 
Starting with any positive integer, 
replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay), 
or it loops endlessly in a cycle which does not include 1. 
Those numbers for which this process ends in 1 are happy numbers.

Example: 
Input: 19
Output: true
Explanation: 
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

*/

public class __0202_Happy_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isHappy(int n) {
        int happyNum = 0;
        int[] sq = { 0, 1, 4, 9, 16, 25, 36, 49, 64, 81 };
        Set<Integer> set = new HashSet<>();
        while (n != 0) {
            happyNum = 0;
            while (n > 0) {
                happyNum += sq[n % 10];
                n /= 10;
            }
            if (!set.add(happyNum)) {
                return false;
            }
            if (happyNum == 1) {
                return true;
            }
            n = happyNum;
        }
        return false;
    }

    public static void main(String[] args) {
        new __0202_Happy_Number().isHappy(7);
    }
}
