package coding.leetcode.temp;

import java.util.Arrays;

/*

You have an array of logs.
Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.
Then, either:
    Each word after the identifier will consist only of lowercase letters, or;
    Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  
It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  
The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  
The digit-logs should be put in their original order.

Return the final order of the logs.


Example 1:
    Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
    Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]


Note:
    0 <= logs.length <= 100
    3 <= logs[i].length <= 100
    logs[i] is guaranteed to have an identifier, and a word after the identifier.

 */

public class _0937_Reorder_Log_Files {

    /**
     * 1. Write a comparator to sort the array
     * 2. If the first string is a digit-log
     *     2.1. If the second string is a digit-log, keep the original order, return 0
     *     2.2. If the second string is a letter-log, the letter-log should be in front, return 1
     * 3. If the first string is a letter-log
     *     3.1 If the second string is a digit-log, keep the original order, return -1
     *     3.2 If the second string is also a letter-log, compare the strings, if there is a tie, compare the identifier
     */
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            int index1 = a.indexOf(" ");
            int index2 = b.indexOf(" ");
            if (Character.isDigit(a.charAt(index1 + 1))) {
                if (Character.isDigit(b.charAt(index2 + 1))) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (Character.isDigit(b.charAt(index2 + 1))) {
                return -1;
            } else {
                String suffix1 = a.substring(index1 + 1);
                String suffix2 = b.substring(index2 + 1);
                int cmp = suffix1.compareTo(suffix2);
                if (cmp == 0) {
                    return a.substring(0, index1).compareTo(b.substring(0, index2));
                } else {
                    return cmp;
                }
            }
        });
        return logs;
    }
}
