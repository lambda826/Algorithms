/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 07:17
 */

package coding.temp;

/*

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
Input: ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:
All given inputs are in lowercase letters a-z.

*/

public class _0014_Longest_Common_Prefix {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = strs[0];
        int index = 1;
        while (index < strs.length) {
            while (strs[index].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length() - 1);
            }
            index++;
        }
        return pre;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        outter:
        while (true) {
            for (int j = 0; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    break outter;
                }
            }
            res.append(strs[0].charAt(i));
            i++;
        }
        return res.toString();
    }
}
