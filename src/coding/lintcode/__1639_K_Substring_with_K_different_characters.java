/**
 *  @author Yunxiang He
 *  @date 02/16/2019
 */

package coding.lintcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given a string S and an integer K.
Calculate the number of substrings of length K and containing K different characters


Example
    String: "abcabc"
    K: 3
    Answer: 3
    substrings:  ["abc", "bca", "cab"]

    String: "abacab"
    K: 3
    Answer: 2
    substrings: ["bac", "cab"]

*/

public class __1639_K_Substring_with_K_different_characters {

    public static void main(String[] args) {
        System.out.println(new __1639_K_Substring_with_K_different_characters().KSubstring2("abacabc", 3));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sliding window
    // Fix the window size, record the number of distinct characters
    // If the number equals k then get substring from left to right 
    public List<String> KSubstring(String stringIn, int K) {
        char[] chs = stringIn.toCharArray();
        int[] dup = new int[256];
        Set<String> set = new HashSet<>();
        int num = 0;
        int l = 0;
        int r = 0;
        while (r < chs.length && r < K) {
            if (++dup[chs[r++]] == 1) {
                ++num;
            }
        }
        if (num == K) {
            set.add(new String(chs, l, K));
        }
        while (r < chs.length) {
            if (++dup[chs[r++]] == 1) {
                ++num;
            }
            if (--dup[chs[l++]] == 0) {
                --num;
            }
            if (num == K) {
                set.add(new String(chs, l, K));
            }
        }
        return new ArrayList<>(set);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sliding window
    // Slide right pointer
    // Ensure no duplicates within the window
    // Ensure the window size is not greater than k
    // Goal test: add element to result set if window size equals k
    // Slide left pointer for one index
    public int KSubstring2(String stringIn, int k) {
        Set<String> set = new HashSet<>();
        char[] chs = stringIn.toCharArray();
        int l = 0;
        int r = 0;
        boolean[] dup = new boolean[256];
        while (r < chs.length) {
            while (r < chs.length && r - l < k && !dup[chs[r]]) {
                dup[chs[r++]] = true;
            }
            if (r - l == k) {
                set.add(new String(chs, l, k));
            }
            dup[chs[l++]] = false;
        }
        return set.size();
    }
}
