package questions.leetcode.string;

/*

Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.


Example 1:
    Input: haystack = "sadbutsad", needle = "sad"
    Output: 0
    Explanation: "sad" occurs at index 0 and 6.
    The first occurrence is at index 0, so we return 0.

Example 2:
    Input: haystack = "leetcode", needle = "leeto"
    Output: -1
    Explanation: "leeto" did not occur in "leetcode", so we return -1.


Constraints:
1 <= haystack.length, needle.length <= 10^4
haystack and needle consist of only lowercase English characters.

*/
public class _0028_Find_the_Index_of_the_First_Occurrence_in_a_String {

    class Solution {
        public int strStr(String haystack, String needle) {
            int result = -1;
            for (int i = 0; i < haystack.length(); ++i) {
                boolean found = false;
                int j = 0;
                while (j < needle.length() && i + j < haystack.length()) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        break;
                    }
                    j++;
                }
                if (j == needle.length()) {
                    result = i;
                    break;
                }
            }
            return result;
        }
    }

}
