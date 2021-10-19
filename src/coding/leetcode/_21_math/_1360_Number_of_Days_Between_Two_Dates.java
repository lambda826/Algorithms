package coding.leetcode._21_math;

/*

Write a program to count the number of days between two dates.
The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.


Example 1:
Input: date1 = "2019-06-29", date2 = "2019-06-30"
Output: 1
Example 2:
Input: date1 = "2020-01-15", date2 = "2019-12-31"
Output: 15


Constraints:
The given dates are valid dates between the years 1971 and 2100.

*/

public class _1360_Number_of_Days_Between_Two_Dates {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 求两个数的差值可以转为成对同一基准点的距离的差值
    public int daysBetweenDates(String date1, String date2) {
        int[] mdays = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
        return Math.abs(countDays(date1, mdays) - countDays(date2, mdays));
    }


    private int countDays(String date, int[] mdays) {
        String[] dd = date.split("-");
        int year = Integer.valueOf(dd[0]);
        int month = Integer.valueOf(dd[1]);
        int day = Integer.valueOf(dd[2]);

        int days = (year - 1 - 1900) * 365 + (year - 1 - 1900) / 4;
        days += mdays[month - 1];
        days += isLeap(year) && month - 1 >= 2 ? 1 : 0;
        days += day;
        return days;
    }

    // 闰年的判定方法：
    // 普通年能被4整除且不能被100整除的为闰年。（如2004年就是闰年，1900年不是闰年）
    // 世纪年能被400整除的是闰年。（如2000年是闰年，1900年不是闰年）
    private boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400) == 0;
    }
}