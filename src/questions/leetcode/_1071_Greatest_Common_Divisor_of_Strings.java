package questions.leetcode;

/*

For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).

Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.


Example 1:
    Input: str1 = "ABCABC", str2 = "ABC"
    Output: "ABC"

Example 2:
    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"

Example 3:
    Input: str1 = "LEET", str2 = "CODE"
    Output: ""


Constraints:
    1 <= str1.length, str2.length <= 1000
    str1 and str2 consist of English uppercase letters.

*/
public class _1071_Greatest_Common_Divisor_of_Strings {

    /**
     * 1.Check if the prefix is divisible for both string, starting from the longest candidate;
     * 2.To validate if divisible, simply concatenate the string.
     */
    class Solution {

        public String gcdOfStrings(String str1, String str2) {
            for (int len = Math.min(str1.length(), str2.length()); len > 0; --len) {
                String prefix = str1.substring(0, len);
                if (isDivisible(prefix, str1) && isDivisible(prefix, str2)) {
                    return prefix;
                }
            }
            return "";
        }

        private boolean isDivisible(String prefix, String str) {
            String res = "";
            for (int i = 0; i < str.length() / prefix.length(); ++i) {
                res += prefix;
            }
            return res.equals(str);
        }
    }

    /**
     * 1.First check if str1 and str2 have GCD, by checking if (str1 + str2) == (str2 + str1).
     * This is because str1 and str2 are composed of same segment base, their concatenation must be consistent, regardless of the order (str1 + str2 = str2 + str1).
     *
     * 2.The method of successive division, also known as the Euclidean algorithm, is used to calculate the GCD (Greatest Common Divisor):
     * Given number a1 and b1, assume a1 = x1 * b1 + y1. If y1 == 0, then gcd(a1, b1) == b1;
     * Otherwise, assume b1 = x2 * b2 + y2. If y2 == 0, then gcd(b1, b2) == b2; which also implies gcd(a1, b1, b2) == b2.
     * This means we just need to recursively do the mod operation till the reminder == 0;
     */
    class Solution2 {

        public String gcdOfStrings(String str1, String str2) {
            if (!(str1 + str2).equals(str2 + str1)) {
                return "";
            }
            return str1.substring(0, gcd(str1.length(), str2.length()));
        }

        private int gcd(int i, int j) {
            if ((i % j) == 0) {
                return j;
            } else {
                return gcd(j, i % j);
            }
        }
    }
}
