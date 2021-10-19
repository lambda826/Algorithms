/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-08
 */

package coding.lintcode;

/*

Given a positive integer, return its corresponding column __00000_title as appear in an Excel sheet.


Example
    1 -> A
    2 -> B
    3 -> C
     ...
    26 -> Z
    27 -> AA
    28 -> AB 

*/

public class __1350_Excel_Sheet_Column_Title {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            sb.insert(0, ((char) (n % 26 + 'A')));
            n /= 26;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new __1350_Excel_Sheet_Column_Title().convertToTitle(27));
    }
}
