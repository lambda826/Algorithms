/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

/*



 */

public class _0395_Longest_Substring_with_At_Least_K_Repeating_Characters {

    public int longestSubstring(String s, int k) {
        return helper(s.toCharArray(), 0, s.length(), k);
    }

    private int helper(char[] str, int left, int right, int k) {
        if (right - left < k) {
            return 0;
        }
        int[] freq = new int[26];
        for (int i = left; i < right; i++) {
            freq[str[i] - 'a']++;
        }
        for (int i = left; i < right; i++) {
            if (freq[str[i] - 'a'] < k) {
                int j = i + 1;
                while (j < right && freq[str[j] - 'a'] < k) {
                    j++;
                }
                return Math.max(helper(str, left, i, k), helper(str, j, right, k));
            }
        }
        return right - left;
    }
}
