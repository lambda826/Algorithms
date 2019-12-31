/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:52:19 PM
 */

package coding.temp;

/*

You are given a string representing an attendance record for a student. 
The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True

Example 2:
Input: "PPALLL"
Output: False

*/

public class _0551_Student_Attendance_Record_I {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkRecord(String s) {
        return (s.indexOf('A') == -1 || s.indexOf('A') == s.lastIndexOf('A')) && s.indexOf("LLL") == -1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkRecord2(String s) {
        int A = 0;
        int L = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                A++;
                L = 0;
                if (A > 1) {
                    return false;
                }
            } else if (c == 'L') {
                L++;
                if (L > 2) {
                    return false;
                }
            } else {
                L = 0;
            }
        }
        return true;
    }
}
