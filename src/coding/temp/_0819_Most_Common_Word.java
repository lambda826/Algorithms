/**
 *  @author Yunxiang He
 *  @date Jul 10, 2018 2:28:14 PM
 */

package coding.temp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  
It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  
Words in the paragraph are not case sensitive.  
The answer is in lowercase.


Example:
    Input: 
    paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
    banned = ["hit"]
    Output: "ball"
    Explanation: 
    "hit" occurs 3 times, but it is a banned word.
    "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
    Note that words in the paragraph are not case sensitive,
    that punctuation is ignored (even if adjacent to words, such as "ball,"), 
    and that "hit" isn't the answer even though it occurs more because it is banned.


Note:
    1 <= paragraph.length <= 1000.
    1 <= banned.length <= 100.
    1 <= banned[i].length <= 10.
    The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
    paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
    Different words in paragraph are always separated by a space.
    There are no hyphens or hyphenated words.
    Words only consist of letters, never apostrophes or other punctuation symbols.

*/

public class _0819_Most_Common_Word {

    public static void main(String[] args) {
        String paragraph = "Bob. hIt, baLl";
        String[] banned = { "bob", "hit" };
        System.out.println(new _0819_Most_Common_Word().mostCommonWord(paragraph, banned));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String mostCommonWord(String paragraph, String[] banned) {
        List<String> banList = Arrays.asList(banned);
        Set<String> banSet = banList.stream().map(String::toLowerCase).collect(Collectors.toSet());
        paragraph += " ";
        Map<String, Integer> freq = new HashMap<>();
        String maxString = "";
        int max = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder();
        for (char ch : paragraph.toCharArray()) {
            if (Character.isLetter(ch)) {
                sb.append(ch);
            } else if (sb.length() > 0) {
                String temp = sb.toString().toLowerCase();
                if (!banSet.contains(temp)) {
                    freq.put(temp, freq.getOrDefault(temp, 0) + 1);
                    if (freq.get(temp) > max) {
                        max = freq.get(temp);
                        maxString = temp;
                    }
                }
                sb.setLength(0);
            }
        }
        return maxString;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String mostCommonWord2(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> m = new HashMap<>();
        String[] p = paragraph.split("\\W+");
        String res = "";
        int num = 0;
        for (String s0 : p) {
            String s1 = s0.toLowerCase();
            if (!ban.contains(s1)) {
                int n = m.getOrDefault(s1, 0) + 1;
                m.put(s1, n);
                if (n > num) {
                    num = n;
                    res = s1;
                }
            }
        }
        return res;
    }

}
