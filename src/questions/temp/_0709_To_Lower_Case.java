package questions.temp;

/*

Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.


Example 1:
    Input: "Hello"
    Output: "hello"

Example 2:
    Input: "here"
    Output: "here"

Example 3:
    Input: "LOVELY"
    Output: "lovely"

*/

public class _0709_To_Lower_Case {

    /**
     * 1. The difference between uppercase and lowercase is 32
     * 2. For each uppercase character, add 32, convert to char and append to the result string
     */
    public String toLowerCase_Stream(String str) {
        StringBuilder sb = new StringBuilder();
        str.chars().forEach((ch) -> sb.append(lowerCase(ch)));
        return sb.toString();
    }

    private char lowerCase(int ch) {
        if (ch >= 'A' && ch <= 'Z') {
            ch += 32;
        }
        return (char) ch;
    }

}
