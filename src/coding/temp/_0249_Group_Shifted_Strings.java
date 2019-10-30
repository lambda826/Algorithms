/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:
    Input: 
        ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
    Output: 
        [
          ["abc","bcd","xyz"],
          ["az","ba"],
          ["acef"],
          ["a","z"]
        ]

*/

public class _0249_Group_Shifted_Strings {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> list = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String root = getRoot(str);
            map.putIfAbsent(root, new ArrayList<>());
            map.get(root).add(str);
        }
        for (List<String> l : map.values()) {
            list.add(l);
        }
        return list;
    }

    private String getRoot(String str) {
        char first = str.charAt(0);
        int dis = 'z' - first;
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch + dis <= 'z') {
                sb.append((char) (ch + dis));
            } else {
                sb.append((char) (ch + dis - 26));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        _0249_Group_Shifted_Strings test = new _0249_Group_Shifted_Strings();

        System.out.println('z' - 'a');
        System.out.println(test.getRoot("az"));
        System.out.println(test.getRoot("ba"));
    }
}
