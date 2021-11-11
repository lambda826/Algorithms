/**
 *  @author Yunxiang He
 *  @date 02/16/2019
 */

package questions.lintcode;

import java.util.Arrays;

/*

In order to prevent passengers from being too bored during the flight, LQ Airlines decided to play two movies during the flight. 
Since the movie cannot be played during the take-off and landing of the aircraft, 
LQ Airlines must ensure that the duration of the two movies to be less than or equal to the flight duration minus 30 minutes, 
and the total length of the two movies should be as long as possible. Now given t ,the flight duration(minutes), and array dur[],the length of movies. 

Please output the length of the two movies in order of length. 
If there are multiple groups of the same length, select the one which contains the longest single moive.
It is guarantee that there is a least one solution.


Example
    Given t=87,dur=[20,25,19,37],return[20,37]
    Explanation:
    87-30=57
    20+25=45,57-45=12
    20+19=39,57-39=19
    20+37=57,57-57=0
    25+19=44,57-44=13
    25+37=62,57<62
    19+37=56,57-56=1

    Givent=67,dur=[20,17,19,18],return[17,20]
    Explanation:
    67-30=37
    17+20=37,18+19=37
    The longest movie in the first group is 20，and 19 in the second grouo, so output`[17,20]`


Notice
    30<t<=1000
    dur[i]<=1000
    1<=len(dur)<=100000

*/

public class __1636_Aerial_Movie {

    public static void main(String[] args) {
        int[] dur = { 50, 17, 46, 42, 22, 28, 13, 12, 54, 52, 19, 12 };
        new __1636_Aerial_Movie().aerial_Movie(127, dur);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] aerial_Movie(int t, int[] dur) {
        t -= 30;
        Arrays.parallelSort(dur);
        int l = 0;
        int r = dur.length - 1;
        int[] res = new int[2];
        int max = Integer.MIN_VALUE;
        int sum;
        while (l < r) {
            sum = dur[l] + dur[r];
            if (sum == t) {
                res[0] = dur[l];
                res[1] = dur[r];
                break;
            } else if (sum > t) {
                --r;
            } else {
                if (sum > max) {
                    max = sum;
                    res[0] = dur[l];
                    res[1] = dur[r];
                }
                ++l;
            }
        }
        return res;
    }
}
