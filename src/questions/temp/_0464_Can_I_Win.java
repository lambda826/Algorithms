/**
 *  @author Yunxiang He
 *  @date Dec 26, 2017 11:40:25 PM
 */

package questions.temp;

import java.util.Arrays;
import java.util.HashMap;

/*

In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.

*/

public class _0464_Can_I_Win {

    // Solution 1
    // Recursion + DP + Bit manipulation
    // Guarantee to win means the opponent loses in every iteration
    // HashMap to record whether the first move can win or not
    private HashMap<Integer, Boolean> dpMap = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }

        int bit = 1 << maxChoosableInteger;
        canIWinAux(maxChoosableInteger, desiredTotal, bit);
        return dpMap.get(bit);
    }

    private boolean canIWinAux(int maxChoosableInteger, int desiredTotal, int bit) {
        if (desiredTotal <= 0) {
            return false;
        }
        if (dpMap.containsKey(bit)) {
            return dpMap.get(bit);
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            if ((1 << (i - 1) & bit) == 0) {
                // If the next move returns false, then the current move should be true
                // If one of the current move returns true (the opponent returns false), then it
                // guarantees to win
                boolean oppWin = canIWinAux(maxChoosableInteger, desiredTotal - i, bit | (1 << (i - 1)));
                if (!oppWin) {
                    dpMap.put(bit, true);
                    return true;
                }
            }
        }
        dpMap.put(bit, false);
        return false;
    }

    // Solution 2: mini-max time exceeded
    // Corner case : desiredTotal = 0;
    // Cautious for when to copy the array
    // Every iteration, have to copy the state and the array
    // Use a map to record the value that has occurred
    private HashMap<String, Integer> map = new HashMap<>();

    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) {
            return true;
        }
        int[] nums = new int[maxChoosableInteger];
        int i = 0;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while (i < maxChoosableInteger) {
            sb = sb.append("1");
            nums[i] = i + 1;
            sum += nums[i];
            i++;
        }
        String key = sb.toString();
        if (sum < desiredTotal) {
            return false;
        }
        int res = max(nums, Integer.MIN_VALUE, Integer.MAX_VALUE, desiredTotal, key);
        return res <= 0 ? false : true;
    }

    private int max(int[] nums, int alfa, int beta, int state, String key) {
        if (state <= 0) {
            map.put(key, -1);
            return -1;
        }
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int v = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                int[] _nums = Arrays.copyOf(nums, nums.length);
                int _state = state - _nums[i];
                StringBuilder sb = new StringBuilder(key);
                sb.setCharAt(i, '0');
                String _key = sb.toString();
                _nums[i] = -1;
                v = Math.max(v, min(_nums, alfa, beta, _state, _key));
                map.put(key, v);
            }
        }
        map.put(key, v);
        if (v >= beta) {
            return v;
        }
        alfa = Math.max(alfa, v);
        return v;
    }

    private int min(int[] nums, int alfa, int beta, int state, String key) {
        if (state <= 0) {
            map.put(key, 1);
            return 1;
        }
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int v = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                int[] _nums = Arrays.copyOf(nums, nums.length);
                int _state = state - _nums[i];
                StringBuilder sb = new StringBuilder(key);
                sb.setCharAt(i, '0');
                String _key = sb.toString();
                _nums[i] = -1;
                v = Math.min(v, max(_nums, alfa, beta, _state, _key));
                map.put(key, v);
            }
        }
        map.put(key, v);
        if (v <= alfa) {
            return v;
        }
        beta = Math.min(beta, v);
        return v;
    }

    public static void main(String[] args) {
        System.out.println(new _0464_Can_I_Win().canIWin(11, 79));
    }
}
