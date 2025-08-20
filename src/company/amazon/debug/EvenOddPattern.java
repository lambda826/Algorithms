/**
 * @author Yunxiang He
 * @date 02/17/2019
 */

package company.amazon.debug;

/*



 */

public class EvenOddPattern {
    public static void main(String[] args) {
        printPattern(10);
    }

    // Print Pattern：for-loop里一共两句话但是没有用大括号，所以第二句没有被包含进去
    public static void printPattern(int num) {
        int i, print = 0;
        if (num % 2 == 0) {
            print = 0;
            for (i = 0; i < num; i++) {
                System.out.println(print + " ");
                print += 2; ////////////////////////////
            }
        } else {
            print = 1;
            for (i = 0; i < num; i++) {
                System.out.println(print + " ");
                print += 2;
            }
        }
    }
}
