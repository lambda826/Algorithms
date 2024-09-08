/**
 * @author: Yunxiang He
 * @date : 2018-10-12
 */

package questions.problems;

public class DNA {

    public static String reverse_complement(String DNA) {

        int left = 0;
        int right = DNA.length() - 1;
        char[] chs = DNA.toCharArray();
        while (left < right) {
            char temp = getCompelment(chs[left]);
            chs[left] = getCompelment(chs[right]);
            chs[right] = temp;
            left++;
            right--;
        }
        if (left == right) {
            chs[left] = getCompelment(chs[left]);
        }
        return String.valueOf(chs);
    }

    private static char getCompelment(char ch) {
        if (ch == 'A') {
            return 'T';
        } else if (ch == 'T') {
            return 'A';
        } else if (ch == 'C') {
            return 'G';
        } else {
            return 'C';
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse_complement("GTCAG"));
        System.out.println(reverse_complement("ACCGGGTTTT"));
    }
}
