/**
 * @author: Yunxiang He
 * @date : 2018-10-04
 */

package questions.lintcode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/*

Given a string s, find all the unique substring with the length of k and sort the results in lexicographic order.


Example
Input: s = "caaab"
k = 2
Output:["aa","ab","ca"]

*/

public class __1481_Unique_Substring {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> uniqueSubstring(String s, int k) {
        SortedSet<String> set = new TreeSet<>();
        for (int i = 0, j = i + k; j <= s.length(); i++, j++) {
            set.add(s.substring(i, j));
        }
        return new ArrayList<>(set);
    }
}
