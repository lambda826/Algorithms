/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 11:42
 */

package coding.leetcode.todo;

/*

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. 
For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Example 1:
Input: buf = "abc", n = 4
Output: "abc"
Explanation: The actual number of characters read is 3, which is "abc".

Example 2:
Input: buf = "abcde", n = 5 
Output: "abcde"

Note:
The read function will only be called once for each test case.

*/

public class __0157_Read_N_Characters_Given_Read4 {

}

class Reader4 {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] buffer = new char[4];
        int start = 0;
        while (start <= n - 1) {
            int count = read4(buffer);
            for (int i = 0; i < count && start <= n - 1; i++) {
                buf[start++] = buffer[i];
            }
            if (count < 4) {
                break;
            }
        }
        return start;
    }

    int read4(char[] buf) {
        return 0;
    }
}
