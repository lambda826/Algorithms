/**
 *  @author Yunxiang He
 *  @date Dec 21, 2017 9:55:59 PM
 */

package questions.temp;

/*

Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

*/

public class _0345_Reverse_Vowels_of_a_String {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Search from the head and tail
    // When find a vowel from both sides, swap them
    // The vowels could be lowercase or uppercase
    public String reverseVowels(String s) {
        char[] c = s.toCharArray();
        int l = 0;
        int r = c.length - 1;
        while (l < r) {
            while (l < r && !(c[l] == 'a' || c[l] == 'e' || c[l] == 'i' || c[l] == 'o' || c[l] == 'u' || c[l] == 'A' || c[l] == 'E' || c[l] == 'I' || c[l] == 'O' || c[l] == 'U')) {
                l++;
            }
            while (l < r && !(c[r] == 'a' || c[r] == 'e' || c[r] == 'i' || c[r] == 'o' || c[r] == 'u' || c[r] == 'A' || c[r] == 'E' || c[r] == 'I' || c[r] == 'O' || c[r] == 'U')) {
                r--;
            }
            char temp = c[l];
            c[l] = c[r];
            c[r] = temp;
            l++;
            r--;
        }
        return new String(c);
    }

    public static void main(String[] args) {
        System.out.println(new _0345_Reverse_Vowels_of_a_String().reverseVowels("hello"));
    }
}
