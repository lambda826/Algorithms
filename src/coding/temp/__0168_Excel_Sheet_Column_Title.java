/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-18 17:27
 */

package coding.temp;

/*

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
    
Example 1:
Input: 1
Output: "A"

Example 2:
Input: 28
Output: "AB"

Example 3:
Input: 701
Output: "ZY"

*/

public class __0168_Excel_Sheet_Column_Title {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1 -> A
    // 2 -> B
    // 3 -> C
    // ...
    // 26 -> Z
    // 27 -> AA
    // 28 -> AB 
    // 
    // 0 -> A
    // 1 -> B
    // 2 -> C
    // ...
    // 25 -> Z
    // 26 -> AA
    // 27 -> AB 
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.insert(0, ((char) (n % 26 + 65)));
            n /= 26;
        }
        return sb.toString();
    }
}
