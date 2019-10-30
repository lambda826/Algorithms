/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-10
 */

package coding.problems;

public class String_Y_N {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxConsecutiveY(String[] arrs) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < arrs.length; i++) {
            if (arrs[i].indexOf("N") == -1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new String_Y_N().maxConsecutiveY(new String[] { "YYYYY", "YYYYY", "YYNYY", "YYYYY" }));
        System.out.println(new String_Y_N().maxConsecutiveY(new String[] { "YNYYYYY", "YYYYYYN", "YYYYNNN", }));
    }
}
