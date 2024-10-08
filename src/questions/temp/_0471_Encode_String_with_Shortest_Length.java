/**
 * @author Yunxiang He
 * @date 02/21/2019
 */

package questions.temp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*

Given a non-empty string, encode the string such that its encoded length is the shortest.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.


Example 1:
    Input: "aaa"
    Output: "aaa"
    Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.

Example 2:
    Input: "aaaaa"
    Output: "5[a]"
    Explanation: "5[a]" is shorter than "aaaaa" by 1 character.

Example 3:
    Input: "aaaaaaaaaa"
    Output: "10[a]"
    Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".

Example 4:
    Input: "aabcaabcd"
    Output: "2[aabc]d"
    Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
 

Example 5:
    Input: "abbbabbbcabbbabbbc"
    Output: "2[2[abbb]c]"
    Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".


Note:
    k will be a positive integer and encoded string will not be empty or have extra space.
    You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
    If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
 
*/

public class _0471_Encode_String_with_Shortest_Length {

    public static void main(String[] args) {
        _0471_Encode_String_with_Shortest_Length test = new _0471_Encode_String_with_Shortest_Length();
        test.encode2("abbbabbbcabbbabbbc");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];
        String[][] bases = new String[n][n];
        int[][] counts = new int[n][n];
        for (int len = 0; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = s.substring(i, j + 1);
                bases[i][j] = s.substring(i, j + 1);
                counts[i][j] = 1;
                boolean foundBase = false;
                for (int k = i; k < j; k++) {
                    if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                    if (!foundBase && bases[i][k].equals(bases[k + 1][j])) {
                        foundBase = true;
                        counts[i][j] = counts[i][k] + counts[k + 1][j];
                        bases[i][j] = bases[i][k];
                    }
                }
                if (foundBase) {
                    String temp = counts[i][j] + "[" + bases[i][j] + "]";
                    if (temp.length() < dp[i][j].length()) {
                        dp[i][j] = temp;
                    }
                } else {
                    bases[i][j] = dp[i][j];
                }
            }
        }
        return dp[0][n - 1];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String encode2(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for (int len = 0; len < s.length(); len++) {
            for (int i = 0; i < s.length() - len; i++) {
                int j = i + len;
                String substr = s.substring(i, j + 1);
                dp[i][j] = substr;
                if (j - i > 3) {
                    // Loop for trying all results that we get after dividing the strings into 2 and combine the results of 2 substrings
                    for (int k = i; k < j; k++) {
                        if ((dp[i][k] + dp[k + 1][j]).length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k + 1][j];
                        }
                    }
                    // Loop for checking if string can itself found some pattern in it which could be repeated.
                    for (int k = 0; k < substr.length(); k++) {
                        String repeatStr = substr.substring(0, k + 1);
                        if (repeatStr != null && substr.length() % repeatStr.length() == 0 && substr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = substr.length() / repeatStr.length() + "[" + dp[i][i + k] + "]";
                            if (ss.length() < dp[i][j].length()) {
                                dp[i][j] = ss;
                            }
                        }
                    }
                }
            }
            System.out.println(Arrays.deepToString(dp));
        }
        return dp[0][s.length() - 1];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Map<String, String> map = new HashMap<String, String>();

    public String encode3(String s) {
        if (s == null || s.length() == 0) {
            return "";
        } else if (s.length() <= 4) {
            return s;
        } else if (map.containsKey(s)) {
            return map.get(s);
        }
        String ret = s;
        for (int k = s.length() / 2; k < s.length(); k++) {
            String pattern = s.substring(k);
            int times = countRepeat(s, pattern);
            if (times * pattern.length() != s.length()) {
                continue;
            }
            String candidate = times + "[" + encode(pattern) + "]";
            if (candidate.length() < ret.length()) {
                ret = candidate;
            }
        }
        for (int i = 1; i < s.length(); i++) {
            String left = encode(s.substring(0, i));
            String right = encode(s.substring(i));
            String candidate = left + right;
            if (candidate.length() < ret.length()) {
                ret = candidate;
            }
        }
        map.put(s, ret);
        return ret;
    }

    private int countRepeat(String s, String pattern) {
        int times = 0;
        while (s.length() >= pattern.length()) {
            String sub = s.substring(s.length() - pattern.length());
            if (sub.equals(pattern)) {
                times++;
                s = s.substring(0, s.length() - pattern.length());
            } else {
                return times;
            }
        }
        return times;
    }

}
