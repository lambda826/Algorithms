/**
 *  @author Yunxiang He
 *  @date 12/24/2017
 */

package coding.leetcode.temp;

/*

Convert a non-negative integer to its english words representation. 
Given input is guaranteed to be less than 2^31 - 1.


Example 1:
    Input: 123
    Output: "One Hundred Twenty Three"

Example 2:
    Input: 12345
    Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:
    Input: 1234567
    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:
    Input: 1234567891
    Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

*/

public class _0273_Integer_to_English_Words {

    public static void main(String[] args) {
        System.out.println(new _0273_Integer_to_English_Words().numberToWords(23250868));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Corner cases : 0, 20, 100, 1000
    // Should take care of whitespace " "
    private String[] tens = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private String[] hundreds = new String[] { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        if (num >= 1000000000) {
            sb.append(tens[num / 1000000000]).append(" ").append("Billion ");
            num %= 1000000000;
        }
        if (num >= 1000000) {
            hundred(sb, num / 1000000).append("Million ");
            num %= 1000000;
        }
        if (num >= 1000) {
            hundred(sb, num / 1000).append("Thousand ");
            num %= 1000;
        }
        hundred(sb, num);
        return sb.toString().trim();
    }

    private StringBuilder hundred(StringBuilder sb, int num) {
        if (num >= 100) {
            sb.append(tens[num / 100]).append(" Hundred ");
            num %= 100;
        }
        if (num >= 20) {
            sb.append(hundreds[num / 10]).append(" ");
            num %= 10;
        }
        if (num > 0) {
            sb.append(tens[num % 20]).append(" ");
        }
        return sb;
    }

}
