/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-11
 */

package coding.problems;

public class Merge_Strings {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String mergeStrings(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        } else if (b == null || b.length() == 0) {
            return a;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < a.length() || j < b.length(); i++, j++) {
            if (i < a.length()) {
                sb.append(a.charAt(i));
            }
            if (j < b.length()) {
                sb.append(b.charAt(j));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String a = "abc";
        String b = "stuvwx";
        System.out.println(mergeStrings(a, b));

        a = "abcdef";
        b = "";
        System.out.println(mergeStrings(a, b));

        a = "abc";
        b = "def";
        System.out.println(mergeStrings(a, b));

        a = "ab";
        b = "zsd";
        System.out.println(mergeStrings(a, b));
    }

}
