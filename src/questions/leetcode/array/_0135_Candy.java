package questions.leetcode.array;


import java.util.ArrayList;
import java.util.List;

public class _0135_Candy {

    /**
     * Core Idea & Challenge
     * The tricky part is that a child's candy count depends on both the left and right neighbors simultaneously.
     * A simple single pass through the array might satisfy the condition for one neighbor but violate it for the other.
     * Consider ratings = [1, 3, 2, 2, 1].
     * If we only look left-to-right:
     * Child 0: 1 candy (minimum)
     * Child 1 (rating 3 > 1): 2 candies (1 + 1)
     * Child 2 (rating 2 < 3): 1 candy (minimum)
     * Child 3 (rating 2 == 2): 1 candy (minimum)
     * Child 4 (rating 1 < 2): 1 candy (minimum)
     * Resulting candies: [1, 2, 1, 1, 1]
     * But this is wrong! Child 1 (rating 3) must have more than Child 2 (rating 2). Also, Child 3 (rating 2) must have more than Child 4 (rating 1).
     * <p>
     * The Two-Pass Approach
     * The standard and most intuitive way to solve this is using two passes:
     * Pass 1: Left-to-Right
     * Initialize an array candies of the same size as ratings, giving each child 1 candy initially (satisfies Rule 1).
     * Iterate from the second child (i = 1 to n-1).
     * If ratings[i] > ratings[i-1], it means the current child i must have more candies than the child to their left (i-1). So, update candies[i] = candies[i-1] + 1.
     * This pass ensures that the condition "higher rating child gets more candies than their left neighbor" is met.
     * Pass 2: Right-to-Left
     * Now, iterate from the second-to-last child (i = n-2 down to 0).
     * If ratings[i] > ratings[i+1], it means the current child i must have more candies than the child to their right (i+1).
     * We already have a value in candies[i] from the first pass. We need to ensure candies[i] is also greater than candies[i+1].
     * So, we take the maximum: candies[i] = max(candies[i], candies[i+1] + 1).
     * Taking the max ensures we don't violate the condition established in the first pass while enforcing the right-neighbor condition.
     * This pass ensures that the condition "higher rating child gets more candies than their right neighbor" is met, without undoing the left-neighbor condition.
     * Final Step: Sum all the values in the candies array. This sum represents the minimum total number of candies required.
     */
    class Solution_Optimal {
        public int candy(int[] ratings) {
            int[] candies = new int[ratings.length];
            int sum = 0;
            candies[0] = 1;
            for (int i = 1; i < ratings.length; ++i) {
                if (ratings[i - 1] < ratings[i]) {
                    candies[i] = candies[i - 1] + 1;
                } else {
                    candies[i] = 1;
                }
            }
            for (int i = ratings.length - 2; i >= 0; --i) {
                if (ratings[i] > ratings[i + 1]) {
                    candies[i] = Math.max(candies[i], candies[i + 1] + 1);
                }
                sum += candies[i + 1];
            }
            return sum + candies[0];
        }
    }

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
