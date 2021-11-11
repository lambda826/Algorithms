/**
 *  @author: Yunxiang He
 *  @date  : 2018-08-04 21:39
 */

package questions.temp;

/*

An encoded string S is given.  
To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:
If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

Example 1:
Input: S = "leet2code3", K = 10
Output: "o"
Explanation: 
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".

Example 2:
Input: S = "ha22", K = 5
Output: "h"
Explanation: 
The decoded string is "hahahaha".  The 5th letter is "h".

Example 3:
Input: S = "a2345678999999999999999", K = 1
Output: "a"
Explanation: 
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
 

Note:
2 <= S.length <= 100
S will only contain lowercase letters and digits 2 through 9.
S starts with a letter.
1 <= K <= 10^9
The decoded string is guaranteed to have less than 2^63 letters.

*/

public class _0884_Decoded_String_at_Index {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String decodeAtIndex(String S, int K) {
        int i = 0;
        long j = 0;
        long _j = 0;
        while (i < S.length()) {
            if (Character.isLetter(S.charAt(i))) {
                j++;
            } else {
                _j = j;
                j *= S.charAt(i) - '0';
            }
            if (j == K) {
                break;
            } else if (j > K) {
                if (K % _j == 0) {
                    break;
                }
                return decodeAtIndex(S, (int) (K % _j));
            }
            i++;
        }
        while (!Character.isLetter(S.charAt(i))) {
            i--;
        }
        return String.valueOf(S.charAt(i));
    }

    public static void main(String[] args) {
        System.out.println(new _0884_Decoded_String_at_Index().decodeAtIndex("leet2", 6));
        System.out.println(new _0884_Decoded_String_at_Index().decodeAtIndex("ajx37nyx97niysdrzice4petvcvmcgqn282zicpbx6okybw93vhk782unctdbgmcjmbqn25rorktmu5ig2qn2y4xagtru2nehmsp", 976159153));
        System.out.println(new _0884_Decoded_String_at_Index().decodeAtIndex("vk6u5xhq9v", 554));
    }
}
