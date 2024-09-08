/**
 * @author Yunxiang He
 * @date Jan 12, 2018 9:52:39 PM
 */

package questions.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/*

Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.

Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]

Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.

*/

public class _0500_Keyboard_Row {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('q', 1);
        map.put('w', 1);
        map.put('e', 1);
        map.put('r', 1);
        map.put('t', 1);
        map.put('y', 1);
        map.put('u', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('p', 1);
        map.put('a', 2);
        map.put('s', 2);
        map.put('d', 2);
        map.put('f', 2);
        map.put('g', 2);
        map.put('h', 2);
        map.put('j', 2);
        map.put('k', 2);
        map.put('l', 2);
        map.put('z', 3);
        map.put('x', 3);
        map.put('c', 3);
        map.put('v', 3);
        map.put('b', 3);
        map.put('n', 3);
        map.put('m', 3);
        outter:
        for (String word : words) {
            int row = map.get(Character.toLowerCase(word.charAt(0)));
            for (char c : word.toCharArray()) {
                if (row != map.get(Character.toLowerCase(c))) {
                    continue outter;
                }
            }
            list.add(word);
        }
        return list.toArray(new String[] {});
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String[] solution2(String[] words) {
        List<String> resList = new ArrayList<>();
        List<Character> l1 = Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p');
        List<Character> l2 = Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l');
        List<Character> l3 = Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm');
        outter:
        for (String str : words) {
            boolean b1 = true;
            boolean b2 = true;
            boolean b3 = true;
            for (char c : str.toLowerCase().toCharArray()) {
                if (b1 && l1.contains(c)) {
                    b2 = false;
                    b3 = false;
                } else if (b2 && l2.contains(c)) {
                    b1 = false;
                    b3 = false;
                } else if (b3 && l3.contains(c)) {
                    b1 = false;
                    b2 = false;
                } else {
                    continue outter;
                }
            }
            resList.add(str);
        }
        return resList.toArray(new String[resList.size()]);
    }

    public String[] solution3(String[] words) {
        return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
    }

    public static void main(String[] args) {
        _0500_Keyboard_Row test = new _0500_Keyboard_Row();
        test.findWords(new String[] { "Hello", "Alaska", "Dad", "Peace" });
    }
}
