package questions._02_string;

import java.util.Arrays;

public class _1647_Minimum_Deletions_to_Make_Character_Frequencies_Unique {

    public int minDeletions(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            ++freq[ch - 'a'];
        }
        Arrays.sort(freq);
        int count = 0;
        int expectedMax = freq[25];
        for (int i = 24; i >= 0 && freq[i] != 0; --i) {
            if (expectedMax == 0) {
                count += freq[i];
            } else if (freq[i] < expectedMax) {
                expectedMax = freq[i];
            } else {
                count += freq[i] - expectedMax + 1;
                expectedMax = expectedMax - 1;
            }
        }
        return count;
    }
}
