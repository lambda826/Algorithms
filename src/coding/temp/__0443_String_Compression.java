/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 00:37
 */

package coding.temp;

/*

Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.

Example 1:
Input:
['a','a','b','b','c','c','c']
Output:
Return 6, and the first 6 characters of the input array should be: ['a','2','b','2','c','3']
Explanation:
'aa' is replaced by 'a2'. 'bb' is replaced by 'b2'. 'ccc' is replaced by 'c3'.

Example 2:
Input:
['a']
Output:
Return 1, and the first 1 characters of the input array should be: ['a']
Explanation:
Nothing is replaced.

Example 3:
Input:
['a','b','b','b','b','b','b','b','b','b','b','b','b']
Output:
Return 4, and the first 4 characters of the input array should be: ['a','b','1','2'].
Explanation:
Since the character 'a' does not repeat, it is not compressed. 'bbbbbbbbbbbb' is replaced by 'b12'.
Notice each digit has it's own entry in the array.

Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.

*/

public class __0443_String_Compression {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int compress(char[] chars) {
        int index = -1;
        int count = 0;
        char pivot = chars[0];
        for (char c : chars) {
            if (c == pivot) {
                count++;
            } else {
                index = modify(chars, count, index + 1, pivot);
                pivot = c;
                count = 1;
            }
        }
        index = modify(chars, count, index + 1, pivot);
        return index + 1;
    }

    private int modify(char[] chars, int count, int index, char pivot) {
        chars[index] = pivot;
        String s = String.valueOf(count);
        if (count > 1) {
            for (char c : s.toCharArray()) {
                chars[++index] = c;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(new __0443_String_Compression().compress(new char[] { 'a', 'a', 'b', 'b', 'c', 'c', 'c' }));
        System.out.println(new __0443_String_Compression().compress(new char[] { 'a', 'a', 'a', 'b', }));
        System.out.println(new __0443_String_Compression().compress(new char[] { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' }));
        System.out.println(new __0443_String_Compression().compress(new char[] { 'a', }));
    }
}
