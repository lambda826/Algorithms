package coding.leetcode.temp;

/*

For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.


Example 1:
    Input: str1 = "ABCABC", str2 = "ABC"
    Output: "ABC"
Example 2:
    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"
Example 3:
    Input: str1 = "LEET", str2 = "CODE"
    Output: ""


Note:
    1 <= str1.length <= 1000
    1 <= str2.length <= 1000
    str1[i] and str2[i] are English uppercase letters.

*/

import static algorithms.math.GCD.gcd;

public class _1071_Greatest_Common_Divisor_of_Strings {

    /**
     * Reduce to a math problem:
     * 1. Find the gcd of the lengths of the two strings
     * 2. Approve if this gcd substring can divide the two strings, then this gcd substring is the desired result
     * 3. Otherwise return ""
     *
     * Approve:
     *      If the gcd length substring cannot divide the strings, then no other smaller divisor substring can divide the input strings
     *      Because: 1) The smaller divisor substring must can divide the gcd length substring
     *               2) If smaller divisor substring can divide the input strings, then the gcd length substring must can divide the input strings
     *               3) Swap the conclusion and condition, the statement keeps valid
     */
    public static String gcdOfStrings(String str1, String str2) {
        String gcd = str1.substring(0, gcd(Math.max(str1.length(), str2.length()), Math.min(str1.length(), str2.length())));
        if (isDivisible(str1, gcd) && isDivisible(str2, gcd)) {
            return gcd;
        }
        return "";
    }

    private static boolean isDivisible(String str, String gcd) {
        int len = gcd.length();
        for (int i = 0, j = len; j <= str.length(); i += len, j += len) {
            if (!gcd.equals(str.substring(i, j))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABABAB", "ABAB"));
    }
}
