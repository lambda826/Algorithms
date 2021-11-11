/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package questions.company.amazon.debug;

public class Digits {

    public static void main(String[] args) {
        System.out.println(countDigits(125));
    }

    public static int countDigits(int num) {
        int temp = num;
        int count = 0;
        while (num != 0) {
            num = num / 10;
            count++;
        }
        return (temp % count);
    }
}
