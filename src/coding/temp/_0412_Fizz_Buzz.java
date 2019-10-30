/**
 *  @author Yunxiang He
 *  @date Jan 8, 2018 9:26:58 PM
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*

Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]


*/

public class _0412_Fizz_Buzz {

    /**
     * Solution:
     * Every 3, 5, 15 add the string to the list
     * Otherwise add the number to the list
     * --------------------------------------------------------------
     *  Time complexity : O(n)
     * Space complexity : O(1)
     */
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        int three = 1;
        int five = 1;
        int fifteen = 1;
        for (int i = 1; i <= n; i++) {
            if (fifteen == 15) {
                res.add("FizzBuzz");
                fifteen = 1;
                three = 1;
                five = 1;
            } else if (three == 3) {
                res.add("Fizz");
                three = 1;
                five++;
                fifteen++;
            } else if (five == 5) {
                res.add("Buzz");
                five = 1;
                three++;
                fifteen++;
            } else {
                res.add(String.valueOf(i));
                three++;
                five++;
                fifteen++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _0412_Fizz_Buzz test = new _0412_Fizz_Buzz();
        List<String> list = test.fizzBuzz(5);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
