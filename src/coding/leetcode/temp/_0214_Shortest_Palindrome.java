/**
 *  @author Yunxiang He
 *  @date 02/16/2019
 */

package coding.leetcode.temp;

/*

Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.


Example 1:
    Input: "aacecaaa"
    Output: "aaacecaaa"

Example 2:
    Input: "abcd"
    Output: "dcbabcd"

*/

public class _0214_Shortest_Palindrome {

    //    public static void main(String[] args) {
    //        System.out.println(new _0214_Shortest_Palindrome().shortestPalindrome("aacecaaa"));
    //    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    public String shortestPalindrome(String s) {
    //        int mid = s.length() / 2;
    //        int c1 = mid;
    //        int c2 = mid;
    //        while (c1 > 0 && s.charAt(c1 - 1) == s.charAt(mid)) {
    //            c1--;
    //        }
    //        while (c2 <s.length() && s.charAt(c2 + 1) == s.charAt(mid)) {
    //            c2++;
    //        }
    //    }
    //
    //    private boolean isPartiallyPalindrome(int c1, int c2, String s) {
    //        while (c1 >= 0 && c2 < s.length() && s.charAt(c1) == s.charAt(c2)) {
    //            c1--;
    //            c2++;
    //        }
    //        if (c1 == -1) {
    //            return true;
    //        } else {
    //            return false;
    //        }
    //    }
}
