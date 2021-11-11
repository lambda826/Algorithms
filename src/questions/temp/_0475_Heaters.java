/**
 *  @author Yunxiang He
 *  @date Jan 17, 2018 4:15:31 PM
 */

package questions.temp;

import java.util.Arrays;

/*

Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, 
find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, 
and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.

Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

*/

public class _0475_Heaters {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the nearest heater to every house
    // The maximum distance from the heater to every house is what is needed
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0;
        int minMax = 0;
        for (int house : houses) {
            while (i < heaters.length - 1 && Math.abs(house - heaters[i]) >= Math.abs(house - heaters[i + 1])) {
                i++;
            }
            minMax = Math.max(minMax, Math.abs(house - heaters[i]));
        }
        return minMax;
    }

    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int min = 0;
        if (heaters[0] > houses[0]) {
            min = heaters[0] - houses[0];
        }
        if (heaters[heaters.length - 1] < houses[houses.length - 1]) {
            min = Math.max(min, houses[houses.length - 1] - heaters[heaters.length - 1]);
        }
        int h = 1;
        for (int house : houses) {
            while (h < heaters.length - 1 && heaters[h] < house) {
                h++;
            }
            if (h <= heaters.length - 1) {
                min = Math.max(min, Math.min(heaters[h] - house, house - heaters[h - 1]));
            }
        }
        return min;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The minimum radius for each house is the minimum distance from the left heater and from t he right heater
    // The goal is to find the maximum radius among these houses
    public int findRadius_BinarySearch(int[] houses, int[] heaters) {
        int min = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            if (house <= heaters[0]) {
                min = Math.max(heaters[0] - house, min);
            } else if (house >= heaters[heaters.length - 1]) {
                min = Math.max(house - heaters[heaters.length - 1], min);
            } else {
                min = Math.max(min, binarySearch(heaters, house));
            }
        }
        return min;
    }

    // Find the greatest num that less than the target
    private int binarySearch(int[] heaters, int house) {
        int lo = 0;
        int hi = heaters.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (house == heaters[mid]) {
                return 0;
            } else if (house > heaters[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return Math.min(heaters[lo] - house, house - heaters[hi]);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findRadius_BinarySearch2(int[] houses, int[] heaters) {
        int min = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            if (house <= heaters[0]) {
                min = Math.max(heaters[0] - house, min);
            } else if (house >= heaters[heaters.length - 1]) {
                min = Math.max(house - heaters[heaters.length - 1], min);
            } else {
                int index = Arrays.binarySearch(heaters, house);
                if (index < 0) {
                    min = Math.max(min, Math.min(heaters[-index - 1] - house, house - heaters[-index - 2]));
                }
            }
        }
        return min;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findRadius_BinarySearch3(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        Arrays.sort(houses);
        // Range should start from the largest possbile value
        int lo = 0;
        int hi = Math.max(houses[houses.length - 1], heaters[heaters.length - 1]);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isInRange(houses, heaters, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean isInRange(int[] houses, int[] heaters, int radius) {
        int i = 0;
        for (int h : heaters) {
            // Watch out for overflow here
            long loRange = h - radius;
            long hiRange = (long) (h + radius);
            while (houses[i] >= loRange && houses[i] <= hiRange) {
                if (i >= houses.length - 1) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _0475_Heaters test = new _0475_Heaters();
        int[] house = { 1, 2, 3 };
        int[] heaters = { 2 };
        System.out.println(test.findRadius(house, heaters));
        System.out.println(test.findRadius_BinarySearch3(house, heaters));
    }
}
