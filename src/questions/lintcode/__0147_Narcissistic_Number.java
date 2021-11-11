package questions.lintcode;

import java.util.ArrayList;
import java.util.List;

/*

Narcissistic Number is a number that is the sum of its own digits each raised to the power of the number of digits.

For example the 3-digit decimal number 153 is a narcissistic number because 153 = 13 + 53 + 33.

And the 4-digit decimal number 1634 is a narcissistic number because 1634 = 14 + 64 + 34 + 44.

Given n, return all narcissistic numbers with n digits.


Example
Example 1:
	Input: 1
	Output: [0,1,2,3,4,5,6,7,8,9]

Example 2:
	Input:  2
	Output: []
	
	Explanation: 
	There is no Narcissistic Number with 2 digits.


Notice
	You may assume n is smaller than 8.

*/
public class __0147_Narcissistic_Number {

    public static void main(String[] args) {
        System.out.println(new __0147_Narcissistic_Number().getNarcissisticNumbers(1));
    }

    public List<Integer> getNarcissisticNumbers(int n) {
        List<Integer> list = new ArrayList<>();
        int[] powers = new int[10];
        for (int i = 1; i < 10; ++i) {
            powers[i] = (int) Math.pow(i, n);
        }
        int start = (int) Math.pow(10, n - 1);
        int end = (int) Math.pow(10, n);
        if (n == 1) {
            start = 0;
        }
        for (int i = start; i < end; ++i) {
            int _i = i;
            int d;
            int sum = 0;
            while (_i != 0) {
                d = _i % 10;
                sum += powers[d];
                _i /= 10;
            }
            if (sum == i) {
                list.add(i);
            }
        }
        return list;
    }
}
