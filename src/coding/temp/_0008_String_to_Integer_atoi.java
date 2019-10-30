/**
 *  @author Yunxiang He
 *  @date Jan 10, 2018 12:07:16 AM
 */

package coding.temp;

/*

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

 */

public class _0008_String_to_Integer_atoi {

    /**
     * Solution:
     *
     * --------------------------------------------------------------
     * Tips:
     * Corner case:
     * ""
     * +-1
     * + 1
     * 001
     * 001a4
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public int myAtoi(String str) {
        if (str.length() == 0 || str == "" || str == null) {
            return 0;
        }
        str = str.trim();
        int i = 0;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            i = 1;
        }
        int digit = 0;
        if (i >= str.length()) {
            return 0;
        }
        while (i < str.length() && digit < 12) {
            if (!isNum(str.charAt(i))) {
                if (digit == 0) {
                    return 0;
                } else {
                    break;
                }
            }
            i++;
            digit++;
        }
        str = str.substring(0, i);
        Long res = Long.valueOf(str);
        if (res >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return Integer.valueOf(str);
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        _0008_String_to_Integer_atoi test = new _0008_String_to_Integer_atoi();
        System.out.println(test.myAtoi("1"));
    }
}
