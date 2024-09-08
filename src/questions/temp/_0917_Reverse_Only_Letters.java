package questions.temp;

/*

Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.


Example 1:
    Input: "ab-cd"
    Output: "dc-ba"

Example 2:
    Input: "a-bC-dEf-ghIj"
    Output: "j-Ih-gfE-dCba"

Example 3:
    Input: "Test1ng-Leet=code-Q!"
    Output: "Qedo1ct-eeLg=ntse-T!"

*/


public class _0917_Reverse_Only_Letters {

    /**
     * 1. We specify two pointers pointing to the endpoints of the string
     * 2. Move the two pointers towards mid until meet
     * 3. Whenever the two pointers are letters, swap
     */
    public String reverseOnlyLetters(String S) {
        char[] chs = S.toCharArray();
        int i = 0;
        int j = chs.length - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(chs[i])) {
                ++i;
            }
            while (i < j && !Character.isLetter(chs[j])) {
                --j;
            }
            swap(chs, i++, j--);
        }
        return new String(chs);
    }

    private void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
