/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-14
 */

package questions.lintcode;

import java.util.Arrays;

/*

Given a list of salaries, find out a cap to make the adjusted salary totals to target. 
cap is defined as: the current salary is smaller than cap, then cap is used as the new salary, otherwise Keep the original salary

Example
    Give a=[1,2,3,4],target=13,
    return 3.
    Explanation:
    If cap=3, the list will change into [3,3,3,4].
    
    Give a=[1,2,3,4],target=16,
    return 4.
    Explanation:
    If cap=4, the list will change into [4,4,4,4].


Notice
    The length of the list does not exceed 100000100000
    The salaries do not exceed 1000010000

*/

public class __1626_Salary_Adjustment {

    public int getCap(int[] a, int target) {
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 0; i--) {
            int temp = a[i] * (i + 1);
            if (temp == target) {
                return a[i];
            } else if (temp > target) {
                target -= a[i];
            } else {
                i++;
                while (temp != target) {
                    temp += i;
                }
                return temp / i;
            }
        }
        return a[0];
    }

    public static void main(String[] args) {
        System.out.println(new __1626_Salary_Adjustment().getCap(new int[] { 2, 4, 6, 8, 10, 12, 14, 15 }, 72));
    }
}
