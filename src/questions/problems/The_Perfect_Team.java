/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-12
 */

package questions.problems;

public class The_Perfect_Team {

    public static int differentTeams(String skills) {
        int res = 100000000;
        int[] total = new int[5];
        for (char ch : skills.toCharArray()) {
            switch (ch) {
                case 'p':
                    total[0]++;
                    break;
                case 'c':
                    total[1]++;
                    break;
                case 'm':
                    total[2]++;
                    break;
                case 'b':
                    total[3]++;
                    break;
                default:
                    total[4]++;
                    break;
            }
        }
        for (int min : total) {
            res = Math.min(res, min);
        }
        return res == 100000000 ? 0 : res;

    }

    public static void main(String[] args) {
        // write your code here
        System.out.println(differentTeams("pcmbz"));
        System.out.println(differentTeams("pcmbzpcmbz"));
        System.out.println(differentTeams("pcmbzpcmbzpmbbz"));
    }
}
