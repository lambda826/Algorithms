/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package questions.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*

Give a string s witch represents the content of the novel, 
and then give a list indicating that the words do not participate in statistics, 
find the most frequent word(return the one with the smallest lexicographic order if there are more than one word)


Example
Input: s = "Jimmy has an apple, it is on the table, he like it"
excludeWords = ["a","an","the"]
Output:"it"

*/

public class __1484_The_Most_Frequent_word {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String frequentWord(String s, Set<String> excludewords) {
        String[] tokens = s.toLowerCase().split("\\W+");
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (String token : tokens) {
            if (!excludewords.contains(token)) {
                map.put(token, map.getOrDefault(token, 0) + 1);
                max = Math.max(max, map.get(token));
            }
        }
        List<String> resList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                resList.add(entry.getKey());
            }
        }
        Collections.sort(resList);
        return resList.get(0);

        //        String res = "";
        //        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        //            if (entry.getValue() == max) {
        //                if ("".equals(res)) {
        //                    res = entry.getKey(); 
        //                } else {
        //                    res = res.compareTo(entry.getKey()) < 0 ? res : entry.getKey();
        //                }
        //            }
        //        }
        //        return res;

    }
}
