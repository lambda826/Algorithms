/**
 * @author Yunxiang He
 * @date 02/20/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. 
Return all such possible sentences.


Example 1:
    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
    [
      "cats and dog",
      "cat sand dog"
    ]

Example 2:
    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
    Input:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output:
    []


Note:
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

*/

public class _0140_Word_Break_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(wordDict);
        helper(s, 0, set, new StringBuilder(), res);
        return res;
    }

    private void helper(String s, int start, Set<String> set, StringBuilder path, List<String> res) {
        if (start == s.length()) {
            res.add(new StringBuilder(path).deleteCharAt(path.length() - 1).toString());
        } else {
            for (int end = start; end <= s.length(); ++end) {
                String temp = s.substring(start, end);
                if (set.contains(temp)) {
                    path.append(temp).append(" ");
                    helper(s, end, set, path, res);
                    path.delete(path.length() - temp.length() - 1, path.length());
                }
            }
        }

    }

}