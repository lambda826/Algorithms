/**
 * @author Yunxiang He
 * @date 02/17/2019
 */

package questions.company.amazon.debug;

/*



 */

public class _DrawPattern {

    public static void main(String[] args) {
        printPattern(3);
    }

    public static void printPattern(int n) {
        int i, j, print = 1;
        for (i = 1; i < n; i++) {
            for (j = 1; j < 2 * i; j++) {
                System.out.print((print));
            }
            System.out.println();
        }
    }
}
