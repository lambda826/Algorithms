/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-08
 */

package coding.lintcode;

/*

The count-and-say sequence is the sequence of integers beginning as follows:
    1, 11, 21, 1211, 111221, ...
    1 is read off as "one 1" or 11.
    11 is read off as "two 1s" or 21.
    21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence.


Example
    Given n = 5, return "111221".


Notice
    The sequence of integers will be represented as a string.
    
*/

public class __0420_Count_and_Say {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String countAndSay(int n) {
        int count;
        StringBuilder say = new StringBuilder("1");
        StringBuilder temp = new StringBuilder();
        for (int i = 1; i < n; i++) {
            count = 1;
            for (int j = 0; j < say.length() - 1; j++) {
                if (say.charAt(j) == say.charAt(j + 1)) {
                    count++;
                } else {
                    temp.append(count).append(say.charAt(j));
                    count = 1;
                }
            }
            temp.append(count).append(say.charAt(say.length() - 1));
            say = temp;
            temp = new StringBuilder();
        }
        return say.toString();
    }
}
