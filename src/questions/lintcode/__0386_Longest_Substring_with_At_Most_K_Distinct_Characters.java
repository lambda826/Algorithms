/**
 * @author Yunxiang He
 * @date 02/21/2019
 */

package questions.lintcode;

/*



 */

public class __0386_Longest_Substring_with_At_Most_K_Distinct_Characters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() == 0 || k == 0) {
            return 0;
        }

        int left = 0, right = 0;
        int count = 0;
        int[] map = new int[256];
        int res = 0;

        while (right < s.length()) {
            char r = s.charAt(right);
            if (map[r] == 0) {
                count++;
            }
            map[r] += 1;

            while (count > k) {
                char l = s.charAt(left);
                map[l] -= 1;
                if (map[l] == 0) {
                    count--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
