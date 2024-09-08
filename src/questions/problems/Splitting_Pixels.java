/**
 * @author Yunxiang He
 * @date 2018-11-10 13:04
 */

package questions.problems;

public class Splitting_Pixels {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String closest(String str) {
        int[] black = { 0, 0, 0 };
        int[] white = { 255, 255, 255 };
        int[] red = { 255, 0, 0 };
        int[] green = { 0, 255, 0 };
        int[] blue = { 0, 0, 255 };
        int one = Integer.parseInt(str.substring(0, 8), 2);
        int two = Integer.parseInt(str.substring(8, 16), 2);
        int three = Integer.parseInt(str.substring(16, 24), 2);
        double _black = Math.pow(one - black[0], 2) + Math.pow(two - black[1], 2) + Math.pow(three - black[2], 2);
        double _white = Math.pow(one - white[0], 2) + Math.pow(two - white[1], 2) + Math.pow(three - white[2], 2);
        double _red = Math.pow(one - red[0], 2) + Math.pow(two - red[1], 2) + Math.pow(three - red[2], 2);
        double _green = Math.pow(one - green[0], 2) + Math.pow(two - green[1], 2) + Math.pow(three - green[2], 2);
        double _blue = Math.pow(one - blue[0], 2) + Math.pow(two - blue[1], 2) + Math.pow(three - blue[2], 2);

        double max = _black;
        String res = "Black";
        if (_black == max) {
            res = "Ambiguous";
        } else if (_black > max) {
            max = _white;
            res = "White";
        }

        if (_red == max) {
            res = "Ambiguous";
        } else if (_red > max) {
            max = _red;
            res = "Red";
        }

        if (_green == max) {
            res = "Ambiguous";
        } else if (_green > max) {
            max = _green;
            res = "Green";
        }

        if (_blue == max) {
            res = "Ambiguous";
        } else if (_blue > max) {
            max = _blue;
            res = "Blue";
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("11", 2));
    }
}
