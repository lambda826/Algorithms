/**
 * @author: Yunxiang He
 * @date : 2018-10-17
 */

package questions.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factorization {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Integer> fraction(int num) {
        List<Integer> res = new ArrayList<>();
        if (num < 1) {
            return res;
        } else if (num == 2) {
            res.add(2);
            return res;
        }

        int up = (int) Math.ceil(Math.sqrt(num));
        for (int i = 2; i <= up; i++) {
            while (num % i == 0) {
                res.add(i);
                num /= i;
            }
        }
        if (num > 1) {
            res.add(num);
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Integer> fermat(int n) {
        List<Integer> res = new ArrayList<>();
        while ((n & 1) == 0) {
            res.add(2);
            n /= 2;
        }
        fermat_DFS(n, res);
        Collections.sort(res);
        return res;
    }

    private static void fermat_DFS(int n, List<Integer> res) {
        // Fermat:
        int x = (int) Math.ceil(Math.sqrt(n));
        int y;
        while (true) {
            int temp = x * x - n;
            y = (int) Math.sqrt(x * x - n);
            if (y * y == temp) {
                break;
            }
            x++;
        }
        // Result:
        int[] m = new int[] { x + y, x - y };
        if (m[0] == 1 && m[1] != 1) {
            res.add(m[1]);
        } else if (m[0] != 1 && m[1] == 1) {
            res.add(m[0]);
        }
        // dfs:
        if (m[0] != 1 && m[1] != 1) {
            fermat_DFS(m[0], res);
            fermat_DFS(m[1], res);
        }
    }

    public static void main(String[] args) {
        int num = 1349582;
        System.out.println(fraction(num));
        System.out.println(fermat(num));
    }
}
