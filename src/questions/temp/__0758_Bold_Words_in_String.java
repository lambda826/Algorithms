/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:46:33 PM
 */

package questions.temp;

/*

Given a set of keywords words and a string S, make all appearances of all keywords in S bold. 
Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". 
Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:
words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.

*/

public class __0758_Bold_Words_in_String {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String boldWords(String[] words, String S) {
        int N = S.length();
        boolean[] bold = new boolean[N];

        for (String word : words) {
            int startIndex = 0;
            int start = S.indexOf(word, startIndex);
            while (start != -1) {
                for (int i = start; i < start + word.length(); i++) {
                    bold[i] = true;
                }
                startIndex = start + 1;
                start = S.indexOf(word, startIndex);
            }
        }

        boolean makeBold = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (bold[i]) {
                if (!makeBold) {
                    sb.append("<b>");
                }
                makeBold = true;
            } else {
                if (makeBold) {
                    sb.append("</b>");
                }
                makeBold = false;
            }
            sb.append(S.charAt(i));
        }
        if (makeBold) {
            sb.append("</b>");
        }
        return sb.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String boldWords2(String[] words, String S) {
        String b = "<b>";
        String _b = "</b>";
        StringBuilder sb = new StringBuilder(S);
        int start = 0;
        int end = -2;
        int k = 0;
        for (int i = 0; i < S.length(); i++) {
            for (String word : words) {
                if (S.startsWith(word, i)) {
                    start = i;
                    if (start > end) {
                        if (end > 0) {
                            sb.insert(end + k, _b);
                            k += 4;
                        }
                        sb.insert(start + k, b);
                        k += 3;
                    }
                    end = Math.max(end, start + word.length());
                }
            }
        }
        if (end > 0) {
            sb.insert(end + k, _b);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new __0758_Bold_Words_in_String().boldWords(new String[] { "ccb", "b", "d", "cba", "dc" }, "eeaadadadc"));
        System.out.println(new __0758_Bold_Words_in_String().boldWords(new String[] { "ab", "cd", "d", "cba", "dc" }, "abcd"));
    }
}