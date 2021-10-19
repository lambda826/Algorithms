/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-30
 */

package coding.problems;

public class Find_the_Substring {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int find(String s, String x) {
        int res = 0;
        if (s == null || s.length() == 0 || x == null || x.length() == 0) {
            return 0;
        }
        for (int index = 0; index < s.length() - x.length(); index++) {
            for (int i = 0; i < x.length(); i++) {
                if (x.charAt(i) != '*' && x.charAt(i) != s.charAt(index + i)) {
                    break;
                }
                res = index;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(find("xabcdey", "ab*de"));
    }
}
