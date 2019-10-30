/**
 *  @author Yunxiang He
 *  @date 02/20/2019
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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

    public static void main(String[] args) {
        System.out.println(new _0140_Word_Break_II().wordBreak2("catsanddog", Arrays.asList(new String[] { "cat", "cats", "and", "sand", "dog" })));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<String, List<String>> hashResult = new HashMap<String, List<String>>();
        findRestResult(hashResult, s, wordDict);
        return hashResult.get(s);
    }

    public void findRestResult(HashMap<String, List<String>> pastResult, String currentLeftString, List<String> wordDict) {
        if (pastResult.containsKey(currentLeftString)) {
            return;
        }
        // success path based on current left string, not includes previous steps
        List<String> allSuccessPaths = new LinkedList<>();
        for (String currentDictWord : wordDict) {
            StringBuffer currentPathStr = new StringBuffer();
            if (currentLeftString.startsWith(currentDictWord)) {
                currentPathStr.append(currentDictWord);
                String leftWord = currentLeftString.substring(currentDictWord.length());
                // next node is a leaf
                if (leftWord.isEmpty()) {
                    // find one path
                    allSuccessPaths.add(currentPathStr.toString().trim());
                } else { // next node is NOT a leaf, recur call it to find it
                    findRestResult(pastResult, leftWord, wordDict);
                    // find the next node's status and update current one
                    if (pastResult.containsKey(leftWord) && !pastResult.get(leftWord).isEmpty()) {
                        for (String restStep : pastResult.get(leftWord)) {
                            allSuccessPaths.add(currentPathStr + " " + restStep);
                        }
                    }
                }
            }
        }
        pastResult.put(currentLeftString, allSuccessPaths);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        List<String>[] dp = new List[s.length() + 1];
        List<String> list = new ArrayList<>();
        list.add("");
        dp[0] = list;
        String word;
        for (int len = 1; len <= s.length(); ++len) {
            dp[len] = new ArrayList<>();
            for (int l = 0; l < len; ++l) {
                word = s.substring(l, len);
                if (wordSet.contains(word)) {
                    for (String pre : dp[l]) {
                        dp[len].add(pre + (pre.equals("") ? "" : " ") + s.substring(l, len));
                    }
                }
            }
        }
        return dp[s.length()];
    }

}
