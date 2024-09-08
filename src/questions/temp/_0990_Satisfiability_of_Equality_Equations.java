/**
 * @author Yunxiang He
 * @date 02/09/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

 

Example 1:

Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
Example 2:

Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
Example 3:

Input: ["a==b","b==c","a==c"]
Output: true
Example 4:

Input: ["a==b","b!=c","c==a"]
Output: false
Example 5:

Input: ["c==c","b==d","x!=z"]
Output: true
 

Note:

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] and equations[i][3] are lowercase letters
equations[i][1] is either '=' or '!'
equations[i][2] is '='

*/

public class _0990_Satisfiability_of_Equality_Equations {
    public boolean equationsPossible(String[] equations) {
        Map<Character, Character> equal = new HashMap<>();
        List<String> notEqual = new ArrayList<>();

        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                notEqual.add(equation);
            } else {
                union(equal, equation.charAt(0), equation.charAt(3));
            }
        }

        for (String equation : notEqual) {
            if (find(equal, equation.charAt(0)) == find(equal, equation.charAt(3))) {
                return false;
            }
        }
        return true;
    }

    private char find(Map<Character, Character> equal, char index) {
        if (equal.get(index) == null) {
            equal.put(index, index);
            return index;
        }
        if (equal.get(index) == index) {
            return index;
        }
        char temp = find(equal, equal.get(index));
        equal.put(index, temp);
        return temp;
    }

    private void union(Map<Character, Character> equal, char index1, char index2) {
        char r1 = find(equal, index1);
        char r2 = find(equal, index2);
        if (r1 != r2) {
            equal.put(r1, r2);
        }
    }
}
