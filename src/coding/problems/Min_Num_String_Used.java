/**
 *  @author Yunxiang He
 *  @date 2018-11-15 16:21
 */

package coding.problems;

/*

题目是给定两个string，source 和 target，用source的多个copy拼接成target，但是拼接的过程中可以删掉source的一些字符再拼接（也就是不连续的子串）。
    1. 什么情况下肯定可以拼出target？
    2. 最少用多少个source的copy。


Example：
    source = 'QWRTYE'
    target = 'QTRYEQW' = 'Q##T##' + '##R#YE' + 'QW####'
    所以就是用到3个source的copy。

*/

public class Min_Num_String_Used {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int minNum_Greedy(String source, String target) {
        int num = 0;
        int i = 0;
        while (i < target.length()) {
            for (int j = 0; j < source.length() && i < target.length(); j++) {
                if (source.charAt(j) == target.charAt(i)) {
                    System.out.print(target.charAt(i));
                    i++;
                }
            }
            System.out.println();
            num++;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(minNum_Greedy("QWRTYE", "QTRYEQW"));
    }

}
