/**
 *  @author Yunxiang He
 *  @date 11/23/2018
 */

package coding.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*

650个号码牌，如果戴反了就会和别人的重复，注意一定是重复！！！！比方说68，戴反了就是89，把所有的可能与别人重复的数字放到一个array里面或者list里面。注意的是，如果是110，变成011，不考虑，leading 0都不考虑。

*/

public class Duplicate_Within_650 {

    Map<Integer, Integer> map = new HashMap<>();
    int number;
    int reverse;
    Set<Integer> set = new HashSet<>();

    public Set<Integer> getAllDupilicate(int n) {
        map.put(1, 1);
        map.put(0, 0);
        map.put(8, 8);
        map.put(6, 9);
        map.put(9, 6);
        int digis = getDigits(n);
        for (int i = 1; i <= digis; i++) {
            getNum_DFS(i, 0, 0, 0, n);
        }
        return set;
    }

    private void getNum_DFS(int i, int j, int number, int reverse, int limit) {
        if (i == j) {
            if (number <= limit && reverse <= limit && number != reverse) {
                set.add(number);
                set.add(reverse);
            }
        } else {
            for (int n : map.keySet()) {
                if (n != 0 || j != 0 && j != i - 1) {
                    getNum_DFS(i, j + 1, number + n * (int) Math.pow(10, j), reverse + map.get(n) * (int) Math.pow(10, i - j - 1), limit);
                }
            }
        }
    }

    private int getDigits(int n) {
        int digis = 0;
        while (n > 0) {
            n /= 10;
            digis++;
        }
        return digis;
    }

    public static void main(String[] args) {
        Duplicate_Within_650 test = new Duplicate_Within_650();
        System.out.println(test.getAllDupilicate(768));
    }
}
