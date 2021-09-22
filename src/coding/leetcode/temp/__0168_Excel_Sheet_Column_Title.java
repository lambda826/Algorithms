package coding.leetcode.temp;

/*

Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...


Example 1:
    Input:
        columnNumber = 1
    Output:
        "A"

Example 2:
    Input:
        columnNumber = 28
    Output:
        "AB"

Example 3:
    Input:
        columnNumber = 701
    Output:
        "ZY"

Example 4:
    Input:
        columnNumber = 2147483647
    Output:
        "FXSHRXW"


Constraints:
    1 <= columnNumber <= 2^31 - 1

*/

public class __0168_Excel_Sheet_Column_Title {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1 -> A
    // 2 -> B
    // 3 -> C
    // ...
    // 26 -> Z
    // 27 -> AA
    // 28 -> AB 
    // 
    // 0 -> A
    // 1 -> B
    // 2 -> C
    // ...
    // 25 -> Z
    // 26 -> AA
    // 27 -> AB
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            --columnNumber;
            sb.insert(0, (char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.toString();
    }
}