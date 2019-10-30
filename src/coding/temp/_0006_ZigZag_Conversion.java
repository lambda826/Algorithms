/**
 * @author Yunxiang He
 * @date Nov 6, 2017 10:29:43 PM
 */

package coding.temp;

/*

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"

 */

public class _0006_ZigZag_Conversion {

    /**
     * Optimal solution:
     * --------------------------------------------------------------
     * Tips:
     * Corner case: ("", 1), ("A", 1), ("AB", 1)
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public String convert(String s, int numRows) {
        if (numRows == 0) {
            return "";
        }
        int step = 2 * numRows - 2;
        if (step == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < numRows; r++) {
            int index = r;
            int index2 = index + 2 * (numRows - r) - 2;
            boolean flag = true;
            if (index2 == numRows - 1 || index2 == step) {
                flag = false;
            }
            while (index < s.length()) {
                sb.append(s.charAt(index));
                index += step;
                if (flag && index2 < s.length()) {
                    sb.append(s.charAt(index2));
                    index2 += step;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        _0006_ZigZag_Conversion test = new _0006_ZigZag_Conversion();
        System.out.println(test.convert("abc", 3));
    }

    public String convert2(String s, int numRows) {
        String res = "";
        char[] _s = s.toCharArray();
        int l = _s.length;
        int margin = numRows * 2 - 2;
        int _margin = margin;

        // row 1
        int x = 0;
        while (x <= l) {
            res += _s[x];
            x += margin;
        }

        // row 2+
        for (int i = 1; i < numRows; i++) {
            int k = i;
            int y = k + _margin;
            while (k <= l) {
                res += _s[k];
                k += margin;
                if (y <= l) {
                    res += _s[y];
                    if (y + margin <= l) {
                        res += _s[y + margin];
                    }
                }
            }
            margin = (numRows - i) * 2 - 2;
        }
        return res;
    }
}
