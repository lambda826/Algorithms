/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-12
 */

package questions.problems;

public class Remove_Chocalate {

    public static int numberofWays(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        int one = 1;
        int two = 1;
        int three = 2;
        int four = 0;
        for (int i = 4; i <= n; i++) {
            four = three + one;
            one = two;
            two = three;
            three = four;
        }
        return four % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(numberofWays(4));
    }
}
