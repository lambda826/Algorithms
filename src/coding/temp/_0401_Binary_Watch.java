/**
 *  @author Yunxiang He
 *  @date Jan 5, 2018 1:22:44 AM
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:
Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

*/

public class _0401_Binary_Watch {

    /**
     * Optimal solution:
     * Java Integer api
     * --------------------------------------------------------------
     * Steps:
     * Check every possbile combination of hour and minute
     * Count the number of ones in the number
     * --------------------------------------------------------------
     * Tips:
     * Integer.bitCount(int num), count the number of ones of num
     * --------------------------------------------------------------
     *  Time complexity : O(n ^ 2)
     * Space complexity : O(1)
     */
    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 64 + m) == num) {
                    times.add(h + (m < 10 ? ":0" : ":") + m);
                }
            }
        }
        return times;
    }

    /**
     * Solution 2:
     * Compute the permutation
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    List<String> res = new ArrayList<String>();

    public List<String> solution2(int num) {
        int h = 0;
        while (h <= num && h < 4) {
            int m = num - h;
            if (m <= 5) {
                ArrayList<String> hourList = new ArrayList<String>();
                int[] hours = new int[] { 1, 2, 4, 8 };
                getHourDFS(hourList, h, hours, 0, 0);

                ArrayList<String> minList = new ArrayList<String>();
                int[] mins = new int[] { 1, 2, 4, 8, 16, 32 };
                getMinDFS(minList, m, mins, 0, 0);

                setPermutation(hourList, minList);
            }
            h++;
        }
        return res;
    }

    private void getHourDFS(ArrayList<String> hourList, int n, int[] hours, int sum, int start) {
        if (start + n <= hours.length) {
            if (n == 0) {
                if (sum < 12) {
                    hourList.add(String.valueOf(sum));
                }
            } else {
                for (int i = start; i <= hours.length - n; i++) {
                    if (hours[i] != 0) {
                        int temp = hours[i];
                        hours[i] = 0;
                        int[] _hours = Arrays.copyOf(hours, hours.length);
                        getHourDFS(hourList, n - 1, _hours, sum + temp, start + 1);
                    }
                }
            }
        }
    }

    private void getMinDFS(ArrayList<String> minList, int n, int[] mins, int sum, int start) {
        if (start + n <= mins.length) {
            if (n == 0) {
                if (sum < 60) {
                    minList.add(String.valueOf(sum < 10 ? "0" + sum : sum));
                }
            } else {
                for (int i = start; i <= mins.length - n; i++) {
                    if (mins[i] != 0) {
                        int temp = mins[i];
                        mins[i] = 0;
                        int[] _mins = Arrays.copyOf(mins, mins.length);
                        getMinDFS(minList, n - 1, _mins, sum + temp, start + 1);
                    }
                }
            }
        }
    }

    private void setPermutation(ArrayList<String> hours, ArrayList<String> mins) {
        for (String h : hours) {
            for (String m : mins) {
                res.add(h + ":" + m);
            }
        }
    }

    public static void main(String[] args) {
        _0401_Binary_Watch test = new _0401_Binary_Watch();
        System.out.println(test.solution2(5));
        System.out.println(test.readBinaryWatch(5));
    }

}
