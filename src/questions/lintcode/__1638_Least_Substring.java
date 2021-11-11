/**
 *  @author Yunxiang He
 *  @date 02/16/2019
 */

package questions.lintcode;

/*

Given a string containing n lowercase letters, the string needs to be divided into several continuous substrings, 
the letter in the substring should be same, and the number of letters in the substring does not exceed k, and output the minimal substring number meeting the requirement.


Example
Example1
    Input: s = "aabbbc", k = 2
    Output: 4
    Explanation:
    we can get "aa", "bb", "b", "c" four substring.

Example2
    input: s = "aabbbc", k = 3
    Output: 3
    we can get "aa", "bbb", "c" three substring.

Notice
    n <= 1e5

*/

public class __1638_Least_Substring {

    public static void main(String[] args) {
        System.out.println(new __1638_Least_Substring().getAns("bbbaccacaba", 2));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getAns(String s, int k) {
        int count = 0;
        char pre = ' ';
        int n = 1;
        for (char ch : s.toCharArray()) {
            if (ch == pre && n < k) {
                n++;
            } else {
                count++;
                n = 1;
            }
            pre = ch;
        }
        return count;
    }
}
