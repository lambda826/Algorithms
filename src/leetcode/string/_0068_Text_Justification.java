package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/*

Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:
    A word is defined as a character sequence consisting of non-space characters only.
    Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    The input array words contains at least one word.


Example 1:
    Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
    Output:
    [
       "This    is    an",
       "example  of text",
       "justification.  "
    ]

Example 2:
    Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
    Output:
    [
      "What   must   be",
      "acknowledgment  ",
      "shall be        "
    ]
    Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
    Note that the second line is also left-justified because it contains only one word.

Example 3:
    Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
    Output:
    [
      "Science  is  what we",
      "understand      well",
      "enough to explain to",
      "a  computer.  Art is",
      "everything  else  we",
      "do                  "
    ]


Constraints:
    1 <= words.length <= 300
    1 <= words[i].length <= 20
    words[i] consists of only English letters and symbols.
    1 <= maxWidth <= 100
    words[i].length <= maxWidth

*/
public class _0068_Text_Justification {

    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> result = new ArrayList<>();
            int i = 0;
            do {
                int j = i;
                int len = words[j].length();
                while (j + 1 < words.length && len + 1 + words[j + 1].length() <= maxWidth) {
                    j++;
                    len += 1 + words[j].length();
                }
                String s;
                if (j == words.length - 1) {
                    s = buildLast(words, i, maxWidth);
                } else {
                    s = build(words, i, j, len, maxWidth);
                }
                result.add(s);
                i = j + 1;
            } while (i < words.length);
            return result;
        }

        private String build(String[] words, int l, int r, int len, int maxWidth) {
            StringBuilder s = new StringBuilder();
            int totalNumOfPad = maxWidth - (len - (r - l));
            while (l <= r) {
                s.append(words[l]);
                int numOfPad = r == l ? totalNumOfPad : (int) Math.ceil((double) totalNumOfPad / (r - l));
                while (numOfPad > 0) {
                    s.append(" ");
                    numOfPad--;
                    totalNumOfPad--;
                }
                l++;
            }
            return s.toString();
        }

        private String buildLast(String[] words, int l, int maxWidth) {
            StringBuilder s = new StringBuilder();
            while (l < words.length) {
                s.append(words[l]);
                if (s.length() < maxWidth) {
                    s.append(" ");
                }
                l++;
            }
            while (s.length() < maxWidth) {
                s.append(" ");
            }
            return s.toString();
        }
    }
}
