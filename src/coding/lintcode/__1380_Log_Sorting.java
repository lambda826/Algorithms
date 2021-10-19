/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package coding.lintcode;

import java.util.Arrays;
import java.util.Comparator;

/*

Give a log, consisting of List< String >, each element representing one line of logs. Each line of log information is separated by a space. The first is the ID of the log, followed by the log content.
There are two ways to make content:

All consist of letters and spaces.
All consist of numbers and spaces.
Now that the logs are sorted, it is required that component 1 be sorted in order of content lexicography and placed at the top, and component 2 should be placed at the bottom and output in the order of input. (Note that the space also belongs to the content, and when the lexicographic order of
the composition method 1 is equal, sort according to the dictionary order of log ID., and the guarantee ID is not repeated)
Example
Given

[
    "zo4 4 7",
    "a100 Act zoo",
    "a1 9 2 3 1",
    "g9 act car"
]
, return

[
    "a100 Act zoo",
    "g9 act car",
    "zo4 4 7",
    "a1 9 2 3 1"
]
Explanation:
"Act zoo" < "act car", So the output is as above.
Given

[
    "zo4 4 7",
    "a100 Actzoo",
    "a100 Act zoo",
    "Tac Bctzoo",
    "Tab Bctzoo",
    "g9 act car"
]
, return

[
    "a100 Act zoo",
    "a100 Actzoo",
    "Tab Bctzoo",
    "Tac Bctzoo",
    "g9 act car",
    "zo4 4 7"
]
Explanation:
Because "Bctzoo" == "Bctzoo", the comparison "Tab" and "Tac" have "Tab" < Tac ", so" Tab Bctzoo "< Tac Bctzoo".
Because ' '<'z', so "A100 Act zoo" < A100 Actzoo".
Notice
The total number of logs entered was n, and n < = 10000.

The length of each line is mi, and mi < = 100.

*/

public class __1380_Log_Sorting {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] logSort(String[] logs) {
        Arrays.sort(logs, new Comparator<String>() {
            public int compare(String o1, String o2) {
                int i1 = o1.indexOf(" ") + 1;
                int i2 = o2.indexOf(" ") + 1;
                boolean isDig1 = Character.isDigit(o1.charAt(i1));
                boolean isDig2 = Character.isDigit(o2.charAt(i2));
                if (isDig1 && isDig2) {
                    return 0;
                } else if (isDig1) {
                    return 1;
                } else if (isDig2) {
                    return -1;
                }
                int cmp = o1.substring(i1).compareTo(o2.substring(i2));
                if (cmp != 0) {
                    return cmp;
                } else {
                    return o1.substring(0, i1).compareTo(o2.substring(0, i2));
                }
            }
        });
        return logs;
    }
}
