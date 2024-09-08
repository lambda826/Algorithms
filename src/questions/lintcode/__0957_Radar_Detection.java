/**
 * @author: Yunxiang He
 * @date : 2018-04-06 19:51
 */

package questions.lintcode;

/*

This problem is in a contest: Weekly Mock Interview Contest #12 (For Snapchat Onsite) . Submit your code and see your ranking!

There is a bunch of radars on a 2D plane(Radar has x, y coordinates, and a radius r which is the range can be detected). 
Now, there is a car that passes through the range of y = 0 and y = 1 and cannot be detected by the radar. If the car is detected, return YES, otherwise NO.
(You can consider that the car is a line segment of length 1 and goes straight from x = 0 to the right)

Notice
The number of radars is n，n <= 1000。
The radar's coordinate x is a non-negative integer, y is an integer, and r is a positive integer.
Have you met this question in a real interview? Yes

Example
Given coordinates = [[0,2]], radius = [1], return "NO".

Explanation:
There is a radar at (0,2) that can detect a circle with a radius of 1 centered on (0,2) and the car will not be detected.

Given coordinates = [[0,2],[1,2]], radius = [1,2], return "YES"。

Explanation:
There is a radar at (0,2) that can detect a circular area with a radius of 2 with a center of (0,2). 
Radars at (1,2) can detect (1,2) as Center, circular area with 2 radius. The No. 2 radar can detect the passing of the car.

*/

public class __0957_Radar_Detection {
    /**
     * @param coordinates: The radars' coordinate
     * @param radius: Detection radius of radars
     * @return: The car was detected or not
     */
    public String radarDetection(Point[] coordinates, int[] radius) {

        for (int i = 0; i < coordinates.length; i++) {
            if ((coordinates[i].y >= 1 && coordinates[i].y <= radius[i]) || (coordinates[i].y == 0 && radius[i] >= 1)) {
                return "YES";
            }
        }
        return "NO";
    }
}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}