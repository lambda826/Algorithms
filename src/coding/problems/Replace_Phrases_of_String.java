/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-18
 */

package coding.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

给定一个句子s, 一个list of string phrases, 要求用phrases里面的phrase替换s里面的单词

事例:
    input:
    s = “the google building balcony has a great view of bay area”
    phrases = ["google_building", "building_balcony", "bay_area", "view_of_bay_area", "great_view"]
    output:
    "the google building_balcony has a great view_of_bay_area"


要求:
    1. 贪心地尽可能使用长的phrase。比"great_view"和"bay_area"比"view_of_bay_area"要短，所以在多种替换都有可能时，优先使用长的phrase，也就是"view_of_bay_area"。
    2. 同样长度的替换发生冲突时没有限制必须使用某一个. "the google_building balcony has a great view_of_bay_area" 也是合法输出

*/

public class Replace_Phrases_of_String {

    public String replacePhrase(String str, String[] phrases) {
        StringBuilder sb = new StringBuilder(str);
        Map<String, String> map = new HashMap<>();
        for (String phrase : phrases) {
            map.put(phrase.replaceAll("_", " "), phrase);
        }

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }

        });

        for (String key : list) {
            int index = sb.indexOf(key);
            if (index >= 0) {
                sb.replace(index, index + key.length(), map.get(key));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Replace_Phrases_of_String().replacePhrase("the google building balcony has a great view of bay area", new String[] { "google_building", "building_balcony", "bay_area", "view_of_bay_area", "great_view" }));
    }

}
