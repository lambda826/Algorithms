/**
 * @author Yunxiang He
 * @date Dec 24, 2017 12:45:23 AM
 */

package questions.temp;

/*

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

*/

public class _0012_Integer_to_Roman {

    // Solution 1
    // Denote every digit by Roman
    public String intToRoman(int num) {
        String[] M = { "", "M", "MM", "MMM" };
        String[] C = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String[] X = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String[] I = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    // Solution 2
    // Only need digits : 1 4 5 9
    public String intToRoman2(int num) {
        int[] nums = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strings = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            while (num >= nums[i]) {
                res.append(strings[i]);
                num -= nums[i];
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        new _0012_Integer_to_Roman().intToRoman2(2324);
    }
}
