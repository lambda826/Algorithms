package leetcode.string.anagram;

/*

Given two strings s1 and s2, return true if s2 contains a
of s1, or false otherwise.
In other words, return true if one of s1's permutations is the substring of s2.


Example 1:
    Input: s1 = "ab", s2 = "eidbaooo"
    Output: true
    Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
    Input: s1 = "ab", s2 = "eidboaoo"
    Output: false


Constraints:
    1 <= s1.length, s2.length <= 10^4
    s1 and s2 consist of lowercase English letters.

*/
public class _0567_Permutation_in_String {

    static class Solution_Optimal {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length()) {
                return false;
            }
            int[] arr = new int[26];
            for (int i = 0; i < s1.length(); ++i) {
                int k = s1.charAt(i) - 'a';
                arr[k]++;
            }
            int left = 0;
            int right = 0;
            int count = s1.length();
            while (right < s1.length()) {
                int k = s2.charAt(right) - 'a';
                // found an unmatched character
                if (arr[k] <= 0) {
                    count++;
                } else {
                    // found a matched character
                    count--;
                }
                // When the character is countered into the array, we do subtraction (-)
                arr[k]--;
                right++;
            }
            if (count == 0) {
                return true;
            }
            while (right < s2.length()) {
                int l = s2.charAt(left) - 'a';
                int r = s2.charAt(right) - 'a';
                if (l != r) {
                    // found an unmatched character
                    if (arr[l] >= 0) {
                        count++;
                    } else {
                        // found a matched character
                        count--;
                    }
                    // When the character is leaving from the array, we do addition (+)
                    arr[l]++;
                    // found an unmatched character
                    if (arr[r] <= 0) {
                        count++;
                    } else {
                        // found a matched character
                        count--;
                    }
                    // When the character is countered into the array, we do addition (+)
                    arr[r]--;
                    if (count == 0) {
                        return true;
                    }
                }
                left++;
                right++;
            }
            return false;
        }
    }
}
