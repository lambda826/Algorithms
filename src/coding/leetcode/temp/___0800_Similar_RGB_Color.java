/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-02 20:32
 */

package coding.leetcode.temp;

/*

In the following, every capital letter represents some hexadecimal digit from 0 to f.

The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  
For example, "#15c" is shorthand for the color "#1155cc".

Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.

Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can be represented as some "#XYZ"

Example 1:
Input: color = "#09f166"
Output: "#11ee66"
Explanation:  
The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
This is the highest among any shorthand color.

Note:
color is a string of length 7.
color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
Any answer which has the same (highest) similarity as the best answer will be accepted.
All inputs and outputs should use lowercase letters, and the output is 7 characters.

*/

public class ___0800_Similar_RGB_Color {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String similarRGB(String color) {
        String[] li = { "00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff" };
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 1; i < color.length(); i = i + 2) {
            int i1 = Character.digit(color.charAt(i), 16);
            int i2 = i1 * 16 + Character.digit(color.charAt(i + 1), 16);
            int num = Math.abs(Integer.parseInt(li[i1], 16) - i2);
            int min = num;
            String rgb = li[i1];
            if (i1 != 0) {
                num = Math.abs(i2 - Integer.parseInt(li[i1 - 1], 16));
                if (min > num) {
                    min = num;
                    rgb = li[i1 - 1];
                }
            }
            if (i1 != 15) {
                num = Math.abs(Integer.parseInt(li[i1 + 1], 16) - i2);
                if (min > num) {
                    min = num;
                    rgb = li[i1 + 1];
                }
            }
            sb.append(rgb);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ___0800_Similar_RGB_Color().similarRGB("#09f166"));
        System.out.println(0xaa - 0xa0);
        System.out.println(0x99 - 0xa0);
    }
}
