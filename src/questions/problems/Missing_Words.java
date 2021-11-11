/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-25
 */

package questions.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Missing_Words {

    public String[] missWords(String s, String t) {
        List<String> list = new ArrayList<>();
        if (s == null) {
            return null;
        } else if (t == null) {
            return s.split(" ");
        }

        String[] _s = s.split(" ");
        String[] _t = t.split(" ");
        for (int i = 0, j = 0; i < _s.length; i++) {
            if (j < _t.length && _s[i].equals(_t[j])) {
                j++;
            } else {
                list.add(_s[i]);
            }
        }

        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Missing_Words().missWords("I am using HackerRank to improve programming", "am HackerRank to improve")));
    }
}
