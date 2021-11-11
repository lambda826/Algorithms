package questions._09_dfs_backtracking;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].


Example 1:
    Input:
        s = "3[a]2[bc]"
    Output:
        "aaabcbc"

Example 2:
    Input:
        s = "3[a2[c]]"
    Output:
        "accaccacc"

Example 3:
    Input:
        s = "2[abc]3[cd]ef"
    Output:
        "abcabccdcdcdef"

Example 4:
    Input:
        s = "abc3[cd]xyz"
    Output:
        "abccdcdcdxyz"


Constraints:
    1 <= s.length <= 30
    s consists of lowercase English letters, digits, and square brackets '[]'.
    s is guaranteed to be a valid input.
    All the integers in s are in the range [1, 300].

*/

public class _0394_Decode_String {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS approach:
    //      We need to globally record current index of the string;
    //      A number may follow a string;
    //      '[' always follows a number;
    //      When we encounter a '[', we should go one layer deeper;
    //      When we encounter a ']', we should go back to previous recursion.
    class Solution_DFS {
        public String decodeString(String s) {
            return DFS(s.toCharArray(), new int[] { 0 });
        }

        private String DFS(char[] chs, int[] index) {
            StringBuilder sb = new StringBuilder();
            int times = 0;
            while (index[0] < chs.length) {
                char ch = chs[index[0]];
                if (Character.isDigit(ch)) {
                    times = times * 10 + (ch - '0');
                    ++index[0];
                } else if (Character.isLetter(ch)) {
                    sb.append(ch);
                    ++index[0];
                } else if (ch == '[') {
                    ++index[0];
                    String str = DFS(chs, index);
                    while (times > 0) {
                        --times;
                        sb.append(str);
                    }
                } else { // If the char is ']', we need to break the loop and go back to previous recursion.
                    ++index[0];
                    break;
                }
            }
            return sb.toString();
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Utilize a stack to store the string groups (times, '[', string, ']');
    // 2. Utilize a variable tempTimes to store and calculate the times of the repeated string;
    // 3. Scan the input string:
    //      3.1. If the current char is a letter, push it into stack;
    //      3.2. If the current char is a digit, calculate the tempTimes accordingly;
    //      3.3. If the current char is '[', which always follows the times, we can flush tempTimes into the stack and reset tempTimes to 0;
    //      3.4. If the current char is ']', then we need to compute times * string, and push it back to the stack;
    // 4. At the end, we append all the strings in the stack;
    class Solution_Stack {

        public String decodeString(String s) {
            Deque<String> stack = new ArrayDeque<>(); // 1
            int tempTimes = 0; // 2
            for (int i = 0; i < s.length(); ++i) { // 3
                char ch = s.charAt(i);
                if (Character.isLetter(ch)) { // 3.1.
                    stack.offerLast(String.valueOf(ch));
                } else if (Character.isDigit(ch)) { // 3.2.
                    tempTimes = 10 * tempTimes + (ch - '0');
                } else if (ch == '[') { // 3.3.
                    stack.offerLast(String.valueOf(tempTimes));
                    stack.offerLast(String.valueOf(ch));
                    tempTimes = 0;
                } else if (ch == ']') { // 3.4.
                    StringBuilder sb = new StringBuilder();
                    while (!stack.getLast().equals("[")) {
                        sb.insert(0, stack.pollLast());
                    }
                    stack.pollLast();
                    String temp = sb.toString();
                    String tempRes = "";
                    int kk = Integer.parseInt(stack.pollLast());
                    while (kk-- > 0) {
                        tempRes += temp;
                    }
                    stack.offerLast(tempRes);
                }
            }
            String res = ""; // 4.
            while (!stack.isEmpty()) {
                res += stack.pollFirst();
            }
            return res;
        }
    }

}