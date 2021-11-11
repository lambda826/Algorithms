package questions.temp;

/*

Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
You can use each character in text at most once.
Return the maximum number of instances that can be formed.


Example 1:
    Input: text = "nlaebolko"
    Output: 1

Example 2:
    Input: text = "loonbalxballpoon"
    Output: 2

Example 3:
    Input: text = "leetcode"
    Output: 0


Constraints:
    1 <= text.length <= 10^4
    text consists of lower case English letters only.

*/

public class _1189_Maximum_Number_of_Balloons {

    /**
     * 1. Count every character
     * 2. For 'b' and 'l', the number should divide 2
     * 3. Record the min
     */
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for (char c : text.toCharArray()) {
            count[c - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        res = Math.min(res, count['b' - 'a']);
        res = Math.min(res, count['a' - 'a']);
        res = Math.min(res, count['l' - 'a'] / 2);
        res = Math.min(res, count['o' - 'a'] / 2);
        res = Math.min(res, count['n' - 'a']);
        return res;
    }
}
