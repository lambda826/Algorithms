/**
 *  @author Yunxiang He
 *  @date Jul 20, 2018 3:31:43 PM
 */

package coding.temp;

/*

Given two strings s and t , write a function to determine if t is an anagram of s.


Example 1:
    Input: s = "anagram", t = "nagaram"
    Output: true

Example 2:
    Input: s = "rat", t = "car"
    Output: false


Note:
    You may assume the string contains only lowercase alphabets.


Follow up:
    What if the inputs contain unicode characters? 
    How would you adapt your solution to such case?

*/

public class _0242_Valid_Anagram {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isAnagram(String s, String t) {
        int[] letters = new int[26];
        for (char ch : s.toCharArray()) {
            letters[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            letters[ch - 'a']--;

        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
