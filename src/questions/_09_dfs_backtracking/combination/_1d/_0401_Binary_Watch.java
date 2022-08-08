package questions._09_DFS_backtracking.combination._1d;

import java.util.ArrayList;
import java.util.List;

/*

A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
Each LED represents a zero or one, with the least significant bit on the right.
    For example, the below binary watch reads "4:51".


Given an integer turnedOn which represents the number of LEDs that are currently on, return all possible times the watch could represent. You may return the answer in any order.
The hour must not contain a leading zero.
    For example, "01:00" is not valid. It should be "1:00".
The minute must be consist of two digits and may contain a leading zero.
    For example, "10:2" is not valid. It should be "10:02".


Example 1:
    Input:
        turnedOn = 1
    Output:
        ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]

Example 2:
    Input:
        turnedOn = 9
    Output:
        []


Constraints:
    0 <= turnedOn <= 10

*/

public class _0401_Binary_Watch {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Top-down: choose from hours and minutes, whose total number equals to the input
    public List<String> readBinaryWatch_BackTracking_2d(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= turnedOn; ++i) {
            int j = turnedOn - i;
            if (i <= 4 && j <= 6) {
                List<Integer> hourBag = new ArrayList<>();
                List<Integer> minBag = new ArrayList<>();
                DFS(12, 4, i, 0, 0, hourBag);
                DFS(60, 6, j, 0, 0, minBag);
                for (int hour : hourBag) {
                    for (int min : minBag) {
                        res.add(hour + ":" + (min < 10 ? "0" + min : min));
                    }
                }
            }
        }
        return res;
    }

    private void DFS(int cap, int end, int count, int start, int visited, List<Integer> bag) {
        if (visited < cap) {
            if (count == 0) {
                bag.add(visited);
            } else {
                for (int i = start; i + count <= end; ++i) {
                    DFS(cap, end, count - 1, i + 1, visited | (1 << i), bag);
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Top-down: view hours and minutes as a whole, and the total number to choose equals to the input
    public List<String> readBinaryWatch_BackTracking_1d(int turnedOn) {
        List<String> res = new ArrayList<>();
        DFS(0, turnedOn, 0, 0, res);
        return res;
    }

    private void DFS(int start, int count, int hour, int min, List<String> res) {
        if (count == 0) {
            res.add(hour + (min < 10 ? ":0" + min : ":" + min));
        } else {
            for (int i = start; i + count < 11; ++i) {
                if (i < 4) {
                    int _hour = hour | 1 << i;
                    if (_hour < 12) {
                        DFS(i + 1, count - 1, _hour, min, res);
                    }
                } else {
                    int _min = min | 1 << (i - 4);
                    if (_min < 60) {
                        DFS(i + 1, count - 1, hour, _min, res);
                    }
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Check every possible combination of hour and minute
    // Count the number of ones in the number
    // Takeaways:
    //      Integer.bitCount
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
}