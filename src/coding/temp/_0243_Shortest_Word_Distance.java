/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

/*

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

*/

public class _0243_Shortest_Word_Distance {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int shortestDistance(String[] words, String word1, String word2) {
        int w1 = -1;
        int w2 = -1;
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (words[i].equals(word1)) {
                    w1 = i;
                } else {
                    w2 = i;
                }
                if (w1 != -1 && w2 != -1) {
                    shortest = Math.min(shortest, Math.abs(w1 - w2));
                }
            }
        }
        return shortest;
    }
}
