package questions.leetcode;

/*

Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.


Example 1:
    Input: s = "hello"
    Output: "holle"

Example 2:
    Input: s = "leetcode"
    Output: "leotcede"


Constraints:
    1 <= s.length <= 3 * 105
    s consist of printable ASCII characters.

*/

import java.util.Set;

public class _0345_Reverse_Vowels_of_a_String {

    class Solution {
        public String reverseVowels(String s) {
            char[] chs = s.toCharArray();
            int left = 0;
            int right = chs.length - 1;
            Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
            while (left < right) {
                while (left < right && !vowels.contains(chs[left])) {
                    left++;
                }
                while (left < right && !vowels.contains(chs[right])) {
                    right--;
                }
                swap(chs, left, right);
                left++;
                right--;
            }
            return new String(chs);
        }

        private void swap(char[] chs, int i, int j) {
            char temp = chs[i];
            chs[i] = chs[j];
            chs[j] = temp;
        }
    }

}
