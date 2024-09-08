/**
 * @author Yunxiang He
 * @date 02/28/2019
 */

package questions.company.amazon.questions;

import java.util.ArrayList;
import java.util.List;

/*

Michelle has created a word game for her students. 
The word game begins with Michelle writing a string and a number, K, on the board.
The students must find a substring of size K such that there is exactly one character that is repeated one;
in other words, there should be k - 1 distinct characters in the substring.

Write an algorithm to help the students find the correct answer. 
If no such substring can be found, return an empty list;
if multiple such substrings exist, return all them, without repetitions. 
The order in which the substrings are does not matter.

Input:
The input to the function/method consists of two arguments - inputString, representing the string written by the teacher;
num an integer representing the number, K, written by the teacher on the board.

Output:
Return a list of all substrings of inputString with K characters, that have k-1 distinct character i.e.
exactly one character is repeated, or an empty list if no such substring exist in inputString.
The order in which the substrings are returned does not matter.

Constraints:
The input integer can only be greater than or equal to 0 and less than or equal to 26 (0 <= num <= 26)
The input string consists of only lowercase alphabetic characters.


Example
    Input:
    inputString = awaglk
    num = 4
Output:
    [awag]
Explanation:
    The substrings are {awag, wagl, aglk}
    The answer is awag as it has 3 distinct characters in a string of size 4, and only one character is repeated twice.

*/

public class K_Substring_with_K_1_different_characters {

    public static void main(String[] args) {
        K_Substring_with_K_1_different_characters test = new K_Substring_with_K_1_different_characters();
        System.out.println(test.KSubstring("awaglk", 4));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> KSubstring(String stringIn, int k) {
        List<String> list = new ArrayList<>();
        int[] duplicate = new int[256];
        int l = 0;
        int r = 0;
        int numOfDifferentCharacters = 0;
        int index;
        while (r < stringIn.length() && r < k) {
            index = stringIn.charAt(r++);
            duplicate[index]++;
            if (duplicate[index] == 1) {
                numOfDifferentCharacters++;
            }
        }
        while (r < stringIn.length() && r < stringIn.length()) {
            if (numOfDifferentCharacters == k - 1) {
                list.add(stringIn.substring(l, r));
            }
            index = stringIn.charAt(l++);
            duplicate[index]--;
            if (duplicate[index] == 0) {
                numOfDifferentCharacters--;
            }
            index = stringIn.charAt(r++);
            duplicate[index]++;
            if (duplicate[index] == 1) {
                numOfDifferentCharacters++;
            }
        }
        if (numOfDifferentCharacters == k - 1) {
            list.add(stringIn.substring(l, r));
        }
        return list;
    }

}
