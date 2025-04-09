package questions.leetcode.array;


import java.util.ArrayList;
import java.util.List;

public class _0135_Candy {

    class Solution {
        public int candy(int[] ratings) {
            if (ratings.length == 1) {
                return 1;
            }
            List<Integer> minimas = new ArrayList<>();
            int[] candies = new int[ratings.length];
            for (int i = 0; i < ratings.length; ++i) {
                if (i > 0 && i < ratings.length - 1 && ratings[i] <= ratings[i + 1] && ratings[i] <= ratings[i - 1]
                        || i == 0 && ratings[i] <= ratings[i + 1]
                        || i == ratings.length - 1 && ratings[i] <= ratings[i - 1]) {
                    minimas.add(i);
                }
            }

            for (int min : minimas) {
                candies[min] = 1;
                int left = min;
                int right = min;
                while (left - 1 >= 0 && ratings[left] < ratings[left - 1]) {
                    candies[left - 1] = Math.max(candies[left - 1], candies[left] + 1);
                    left--;
                }

                while (right + 1 <= ratings.length - 1 && ratings[right] < ratings[right + 1]) {
                    candies[right + 1] = Math.max(candies[right + 1], candies[right] + 1);
                    right++;
                }
            }

            int sum = 0;
            for (int candy : candies) {
                sum += candy;
            }
            return sum;
        }
    }
}
