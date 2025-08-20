/**
 * @author Yunxiang He
 * @date 02/17/2019
 */

package company.amazon.debug;

public class GradingSystem {
    public static void main(String[] args) {
        System.out.println(checkGrad(78));
        System.out.println(checkGrad(66));
        System.out.println(checkGrad(99));
        System.out.println(checkGrad(55));
    }

    public static char checkGrad(int marks) {
        if (marks <= 60) {
            return 'D';
        } else if ((61 <= marks) && (marks <= 75)) {
            return 'C';
        } else if ((76 <= marks) && (marks <= 90)) {
            return 'B';
        } else {
            return 'A';
        }
    }
}
