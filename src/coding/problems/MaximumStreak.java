package coding.problems;

public class MaximumStreak {
    public static int maxStreak(int m, String[] data) {
        int maxDays = Integer.MIN_VALUE;
        int days = 0;
        for (String line : data) {
            int employee = 0;
            for (char c : line.toCharArray()) {
                if (c == 'Y') {
                    employee++;
                } else {
                    break;
                }
            }
            if (employee == m) {
                days++;
                maxDays = Math.max(maxDays, days);
            } else {
                days = 0;
            }
        }

        return maxDays > 0 ? maxDays : 0;
    }

    public static void main(String[] args) {
        int m = 3;
        String[] data = new String[] { "YYY", "YYY", "YNN", "YYN", "YYN" }; // 2
        System.out.println(maxStreak(m, data));

        int m2 = 5;
        String[] data2 = new String[] { "YYYYY", "YYYYY", "YYYYN", "YYYYY" }; // 2
        System.out.println(maxStreak(m2, data2));

        int m3 = 7;
        String[] data3 = new String[] { "YNYYYYY", "YYYYYYN", "YYYYNNN" }; // 0
        System.out.println(maxStreak(m3, data3));
    }
}
