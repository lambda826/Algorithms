package questions.temp;

/*

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.


Example 1:
    Input: "Let's take LeetCode contest"
    Output: "s'teL ekat edoCteeL tsetnoc"


Note: 
    In the string, each word is separated by single space and there will not be any extra space in the string.

*/

public class _0557_Reverse_Words_in_a_String_III {

    /**
     * 1. Add a whitespace to the original string in order to iterate
     * 2. Convert the string into char array to do the reversion on the char array
     * 3. Revert the segment of the char array whenever encounter a whitespace
     * 4. Convert the char array back into string
     */
    public String reverseWords(String s) {
        s += " ";
        char[] chs = s.toCharArray();
        for (int pre = 0, curr = 0; curr < chs.length; ++curr) {
            if (Character.isWhitespace(chs[curr])) {
                reverse(chs, pre, curr - 1);
                pre = curr + 1;
            }
        }
        // new String(char[] value, int offset, int count)
        return new String(chs, 0, chs.length - 1);
    }

    private void reverse(char[] c, int i, int j) {
        char temp;
        while (i < j) {
            temp = c[i];
            c[i] = c[j];
            c[j] = temp;
            ++i;
            --j;
        }
    }

}
