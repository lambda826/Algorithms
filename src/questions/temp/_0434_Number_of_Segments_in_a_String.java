/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 00:27
 */

package questions.temp;

/*

Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:
Input: "Hello, my name is John"
Output: 5

*/

public class _0434_Number_of_Segments_in_a_String {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int countSegments(String s) {
        return ("a " + s + " a").replaceAll("\\ +", " ").split(" ").length - 2;
    }
}
