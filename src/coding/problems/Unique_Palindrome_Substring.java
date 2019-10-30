/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-30
 */

package coding.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Unique_Palindrome_Substring {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> countSubstrings(String s) {
        Set<String> set = new HashSet<>();
        for (int center = 0; center < s.length(); center++) {
            countFromCenter(s, center, center, set);
            countFromCenter(s, center, center + 1, set);
        }
        return new ArrayList<>(set);
    }

    private void countFromCenter(String s, int left, int right, Set<String> set) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            set.add(s.substring(left, right + 1));
            left--;
            right++;
        }
    }
}
