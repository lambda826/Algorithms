/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-30 04:06
 */

package questions.temp;

import java.util.Arrays;

/*

Koko loves to eat bananas.  
There are N piles of bananas, the i-th pile has piles[i] bananas.  
The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  
Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  
If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within H hours.


Example 1:
Input: piles = [3,6,7,11], H = 8
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], H = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], H = 6
Output: 23

Note:
1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9

*/

public class _0875_Koko_Eating_Bananas {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minEatingSpeed(int[] piles, int H) {
        long sum = 0;
        for (int i : piles) {
            sum += i;
        }
        int min = (int) (sum / H);
        if (min * H < sum) {
            min++;
        }
        while (true) {
            int hours = 0;
            for (int i : piles) {
                hours += (i - 1) / min + 1;
            }
            if (hours <= H) {
                return min;
            }
            min++;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minEatingSpeed_BinarySearch(int[] piles, int H) {
        Arrays.sort(piles);
        int lo = 1;
        int hi = piles[piles.length - 1] + 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int index = Arrays.binarySearch(piles, mid);
            if (index < 0) {
                index = get(piles, -index - 1, mid);
            } else {
                index = get(piles, index + 1, mid);
            }
            if (index > H) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    private int get(int[] piles, int index, int k) {
        int count = index;
        for (int i = index; i < piles.length; i++) {
            count += (piles[i] - 1) / k + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new _0875_Koko_Eating_Bananas().minEatingSpeed(new int[] { 312884470 }, 968709470));
    }
}
