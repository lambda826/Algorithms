/**
 * @author Yunxiang He
 * @date Jan 8, 2018 2:28:25 PM
 */

package questions.temp;

/*

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:
Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. 
Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

 */

public class __0258_Add_Digits {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 能被 4 整除的数，个位和十位所组成的两位数能被 4 整除
    // 能被 7 整除的数，若一个整数的个位数字截去，再从余下的数中，减去个位数的 2 倍，如果差是 7 的倍数，则原数能被 7 整除；如果差太大或心算不易看出是否7的倍数，就需要继续上述「截尾、倍大、相减、验差」的过程
    // 能被 8 整除的数，百位、十位和个位所组成的三位数能被 8 整除，那么这个数能被 8 整除
    // 能被 9 整除的数， 各个数位上的数字和能被 9 整除，那么这个数能被 9 整除
    public int addDigits(int num) {
        return num == 0 ? 0 : (num % 9 == 0 ? 9 : num % 9);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int addDigits2(int num) {
        while (num > 9) {
            int temp = 0;
            while (num > 0) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }
        return num;
    }
}
