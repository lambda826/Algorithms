/**
 *  @author: Yunxiang He
 *  @date  : 2018-09-29
 */

package questions.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HackLand_Election {

    static String electionWinner(String[] votes) {

        Map<String, Integer> nameMap = new HashMap<>();
        for (String vote : votes) {
            nameMap.put(vote, nameMap.getOrDefault(vote, 0) + 1);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(nameMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o2.getKey().compareTo(o1.getKey());
                } else {
                    return o2.getValue() - o1.getValue();
                }
            }
        });
        return list.get(0).getKey();
    }

    public static void main(String[] args) {
        System.out.println(electionWinner(new String[] { "Al", "Mi", "Ma", "Mi", "Ma" }));
    }
}
