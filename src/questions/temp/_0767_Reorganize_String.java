/**
 * @author Yunxiang He
 * @date 05/23/2018
 */

package questions.temp;

import java.util.PriorityQueue;

/*

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
If possible, output any possible result.  If not possible, return the empty string.


Example 1:
    Input: S = "aab"
    Output: "aba"

Example 2:
    Input: S = "aaab"
    Output: ""


Note:
    S will consist of lowercase letters and have length in range [1, 500].

*/

public class _0767_Reorganize_String {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 0. if the count of some character is greater than the half of the total count, return ""
    // 1. maxHeap, order by the cnt of characters DESC
    // 2. poll two characters from the heap, append to the res, offer back to the heap with the right count
    public String reorganizeString(String S) {
        int[] cnt = new int[26];
        for (char ch : S.toCharArray()) {
            ++cnt[ch - 'a'];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> cnt[b] - cnt[a]);
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > (S.length() + 1) / 2) {
                return "";
            }
            if (cnt[i] != 0) {
                maxHeap.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() > 1) {
            int ch1 = maxHeap.poll();
            int ch2 = maxHeap.poll();
            sb.append((char) (ch1 + 'a'));
            sb.append((char) (ch2 + 'a'));
            if (--cnt[ch1] > 0) {
                maxHeap.offer(ch1);
            }
            if (--cnt[ch2] > 0) {
                maxHeap.offer(ch2);
            }
        }
        if (maxHeap.size() > 0) {
            sb.append((char) (maxHeap.poll() + 'a'));
        }
        return sb.toString();
    }
}
