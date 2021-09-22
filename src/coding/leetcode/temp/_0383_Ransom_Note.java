/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.leetcode.temp;

/*

Given an arbitrary ransom note string and another string containing letters from all the magazines, 
write a function that will return true if the ransom note can be constructed from the magazines; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

*/

public class _0383_Ransom_Note {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] _magazine = new int[26];
        int[] _ransomNote = new int[26];
        for (char c : magazine.toCharArray()) {
            _magazine[c - 97]++;
        }
        for (char c : ransomNote.toCharArray()) {
            _ransomNote[c - 97]++;
        }
        for (int i = 0; i < 26; i++) {
            if (_magazine[i] < _ransomNote[i]) {
                return false;
            }
        }
        return true;
    }
}
