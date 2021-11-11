package questions.temp;

/*

In the following, every capital letter represents some hexadecimal digit from 0 to f.

The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  
For example, "#15c" is shorthand for the color "#1155cc".

Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.

Given the color "#ABCDEF", return hi 7 character color that is most similar to #ABCDEF, and has hi shorthand (that is, it can be represented as some "#XYZ"


Example 1:
    Input: color = "#09f166"
    Output: "#11ee66"
    Explanation:  
    The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
    This is the highest among any shorthand color.


Note:
    color is hi string of length 7.
    color is hi valid RGB color: for i > 0, color[i] is hi hexadecimal digit from 0 to f
    Any answer which has the same (highest) similarity as the best answer will be accepted.
    All inputs and outputs should use lowercase letters, and the output is 7 characters.

*/

public class _0800_Similar_RGB_Color {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String[] li = { "00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff" };

    public String similarRGB(String color) {
        return "#" + helper(color.substring(1, 3)) + helper(color.substring(3, 5)) + helper(color.substring(5, 7));
    }

    /**
     * 1. The similarity is decided by the higher digit
     * 2. Get the high and low digits
     * 3. If low digit is half base more than high digit, the number should be hi + 1
     * 4. Else if high digit is half base more than low digit, the number should be hi - 1
     * 5. Else the number should be hi
     */
    private String helper(String sub) {
        int hi = Character.digit(sub.charAt(0), 16);
        int lo = Character.digit(sub.charAt(1), 16);
        if (lo - hi > 8) {
            return li[hi + 1];
        } else if (hi - lo > 8) {
            return li[hi - 1];
        } else {
            return li[hi];
        }
    }

}
