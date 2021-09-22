/**
 *  @author Yunxiang He
 *  @date 05/22/2018
 */

package coding.leetcode.temp;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].


Examples:
    s = "3[a]2[bc]", return "aaabcbc".
    s = "3[a2[c]]", return "accaccacc".
    s = "2[abc]3[cd]ef", return "abcabccdcdcdef".


*/

public class _0394_Decode_String {

    public static void main(String[] args) {
        _0394_Decode_String test = new _0394_Decode_String();
        System.out.println(test.decodeString("3[a]2[b4[F]c]"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String decodeString(String s) {
        return DFS(s.toCharArray(), new int[] { 0 });
    }

    private String DFS(char[] ch, int[] i) {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        while (i[0] < ch.length) {
            if (Character.isLetter(ch[i[0]])) {
                sb.append(ch[i[0]++]);
            } else if (Character.isDigit(ch[i[0]])) {
                k = 10 * k + (ch[i[0]++] - '0');
            } else if (ch[i[0]] == '[') {
                ++i[0];
                String res = DFS(ch, i);
                for (int t = 0; t < k; ++t) {
                    sb.append(res);
                }
                k = 0;
            } else {
                ++i[0];
                break;
            }
        }
        return sb.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String decodeString2(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        String copy;
        int k = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ']') {
                stack.offerLast(s.charAt(i));
            } else {
                char ch;
                while ((ch = stack.pollLast()) != '[') {
                    sb.insert(0, ch);
                }
                int r = 1;
                while (!stack.isEmpty() && stack.peekLast() >= '0' && stack.peekLast() <= '9') {
                    k += (r * Character.getNumericValue(stack.pollLast()));
                    r *= 10;
                }
                copy = sb.toString();
                while (--k > 0) {
                    sb.append(copy);
                }
                for (int j = 0; j < sb.length(); ++j) {
                    stack.offerLast(sb.charAt(j));
                }
                sb.setLength(0);
            }
        }
        while (!stack.isEmpty()) {
            res.insert(0, stack.pollLast());
        }
        return res.toString();
    }
}
