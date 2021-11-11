/**
 *  @author Yunxiang He
 *  @date 03/27/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given a string, sort it in decreasing order based on the frequency of characters.


Example 1:
    Input:
        "tree"
    Output:
        "eert"
    Explanation:
        'e' appears twice while 'r' and 't' both appear once.
        So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
    Input:
        "cccaaa"
    Output:
        "cccaaa"
    Explanation:
        Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
        Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:
    Input:
        "Aabb"
    Output:
        "bbAa"
    Explanation:
        "bbaA" is also a valid answer, but "Aabb" is incorrect.
        Note that 'A' and 'a' are treated as two different characters.

*/

public class _0451_Sort_Characters_By_Frequency {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // sort
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (c1, c2) -> map.get(c2) - map.get(c1));
        StringBuilder sb = new StringBuilder();
        for (char ch : list) {
            for (int i = 0; i < map.get(ch); ++i) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // bucket
    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        List<Character>[] list = new List[s.length() + 1];
        for (char ch : map.keySet()) {
            if (list[map.get(ch)] == null) {
                list[map.get(ch)] = new ArrayList<>();
            }
            list[map.get(ch)].add(ch);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = list.length - 1; i >= 0; --i) {
            if (list[i] != null) {
                for (char ch : list[i]) {
                    for (int n = 0; n < i; ++n) {
                        sb.append(ch);
                    }
                }
            }
        }
        return sb.toString();
    }

}
