/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-10
 */

package coding.problems;

public class Jump_power {

    public static int minPower(String binary) {
        // 01001100101   3
        if (binary == null || binary.length() == 0) {
            return 0;
        }
        char target = binary.charAt(binary.length() - 1);
        int index = 0;
        int power = 1;
        int pre = 0;
        while (index != -1 && index < binary.length()) {
            pre = index;
            index = binary.indexOf(target, index) + 1;
            power = Math.max(power, index - pre);

        }
        return power;
    }

    public static void main(String[] args) {
        System.out.println(minPower("01000"));
        System.out.println(minPower("10110"));
        System.out.println(minPower("01001100101"));
        System.out.println(minPower("11110010"));
    }
}
