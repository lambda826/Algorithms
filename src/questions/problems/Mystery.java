/**
 * @author: Yunxiang He
 * @date : 2018-10-12
 */

package questions.problems;

/*



 */

public class Mystery {

    public static void main(String[] args) {
        int x = 2437;
        int y = 875;
        while (x != y) {
            if (x > y) {
                x -= y;
            } else {
                y -= x;
            }
        }
        System.out.println(x);
    }

    public void compute() {

    }
}
