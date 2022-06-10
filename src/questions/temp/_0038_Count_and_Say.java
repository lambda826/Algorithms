/**
 *  @author Yunxiang He
 *  @date Jan 8, 2018 9:49:03 PM
 */

package questions.temp;

/*

The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:
Input: 1
Output: "1"

Example 2:
Input: 4
Output: "1211"

*/

public class _0038_Count_and_Say {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String countAndSay_Iterative(int n) {
        StringBuilder sb = new StringBuilder("1");
        StringBuilder sb2 = new StringBuilder();
        for (int k = 1; k < n; k++) {
            int count = 1;
            int i = 0;
            while (i < sb.length() - 1) {
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    count++;
                } else {
                    sb2.append(count).append(sb.charAt(i));
                    count = 1;
                }
                i++;
            }
            sb2.append(count).append(sb.charAt(i));
            sb = sb2;
            sb2 = new StringBuilder();
        }
        return sb.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String countAndSay_Recursion(int n) {
        if (n <= 1) {
            return "1";
        }
        String str = countAndSay_Recursion(n - 1);
        StringBuilder say = new StringBuilder();
        int p = 0;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(p)) {
                count++;
            } else {
                say.append(count);
                say.append(str.charAt(p));
                count = 1;
                p = i;
            }
        }
        say.append(count);
        say.append(str.charAt(p));
        return say.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _0038_Count_and_Say().countAndSay_Iterative(5));
    }
}
