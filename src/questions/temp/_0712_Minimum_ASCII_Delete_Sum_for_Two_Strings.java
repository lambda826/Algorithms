/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

/*

Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.


Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.

Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  
Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.


Note:
0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].

*/

public class _0712_Minimum_ASCII_Delete_Sum_for_Two_Strings {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minimumDeleteSum_DP(String s1, String s2) {
        int[] dp = new int[s1.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            dp[i + 1] = dp[i] + s1.charAt(i);
        }
        int pre = 0;
        int curr = 0;
        for (int i = 0; i < s2.length(); i++) {
            pre = dp[0];
            dp[0] += s2.charAt(i);
            for (int j = 0; j < s1.length(); j++) {
                curr = dp[j + 1];
                dp[j + 1] = s2.charAt(i) == s1.charAt(j) ? pre : Math.min(s1.charAt(j) + dp[j], s2.charAt(i) + dp[j + 1]);
                pre = curr;
            }
        }
        return dp[s1.length()];
    }

    public static void main(String[] args) {
        System.out.println(new _0712_Minimum_ASCII_Delete_Sum_for_Two_Strings().minimumDeleteSum_DP("a", "at"));
    }
}
