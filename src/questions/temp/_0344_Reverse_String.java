package questions.temp;

/*

Write a function that reverses a string. The input string is given as an array of characters char[].
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
You may assume all the characters consist of printable ascii characters.

*/

public class _0344_Reverse_String {

    /**
     * 1. Swap from the two ends of the char array until the two pointers meet
     */
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        char temp;
        while (i < j) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            ++i;
            --j;
        }
    }

}
