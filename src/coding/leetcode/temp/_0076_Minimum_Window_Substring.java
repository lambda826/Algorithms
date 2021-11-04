/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package coding.leetcode.temp;

/*



 */

import java.util.HashMap;
import java.util.Map;

public class _0076_Minimum_Window_Substring {

    class Solution {
        public String minWindow(String s, String t) {
            int start = 0, matched = 0;
            String res = "";
            Map<Character, Integer> map = new HashMap<>(); // char, freq

            for (char c : t.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            for (int right = 0; right < s.length(); right++) {
                char ch = s.charAt(right);
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch) - 1);
                    if (map.get(ch) == 0) {
                        matched++;
                    }
                }

                while (matched == map.size()) {
                    if (res.length() == 0) {
                        res = s.substring(start, right + 1);
                    }
                    if (res.length() > (right - start + 1)) {
                        res = s.substring(start, right + 1);
                    }
                    char left = s.charAt(start);
                    if (map.containsKey(left)) {
                        if (map.get(left) == 0) {
                            matched--;
                        }
                        map.put(left, map.get(left) + 1);
                    }
                    start++;
                }
            }
            return res;
        }
    }
}
