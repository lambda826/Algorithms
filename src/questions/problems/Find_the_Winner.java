/**
 * @author: Yunxiang He
 * @date : 2018-10-10
 */

package questions.problems;

public class Find_the_Winner {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String findTheWinnder(int[] maria, int[] andrea, String s) {
        boolean isEven = s.equals("Even");
        int score = 0;
        for (int i = 0; i < andrea.length; i++) {
            if (isEven) {
                score += andrea[i] - maria[i];
            }
            isEven = !isEven;
        }
        if (score == 0) {
            return "tie";
        } else if (score > 0) {
            return "Andrea";
        } else {
            return "Maria";
        }
    }

    public static void main(String[] args) {
        int[] a1 = new int[] { 1, 2, 3 };
        int[] b1 = new int[] { 2, 1, 3 };
        int[] a2 = new int[] { 3, 5, 6 };
        int[] b2 = new int[] { 4, 5, 7 };
        int[] a3 = new int[] { 3, 1, 2, 3 };
        int[] b3 = new int[] { 3, 2, 1, 3 };
        String s1 = "Even";
        String s2 = "Odd";
        System.out.println(new Find_the_Winner().findTheWinnder(a2, b2, s1));
    }

}
