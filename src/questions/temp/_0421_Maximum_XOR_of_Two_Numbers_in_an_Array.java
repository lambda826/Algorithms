/**
 * @author: Yunxiang He
 * @date : 2018-07-23 18:12
 */

package questions.temp;

import java.util.HashSet;
import java.util.Set;

/*

Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
Could you do this in O(n) runtime?


Example:
    Input: [3, 10, 5, 25, 2, 8]
    Output: 28
    Explanation: The maximum result is 5 ^ 25 = 28.

*/

public class _0421_Maximum_XOR_of_Two_Numbers_in_an_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 利用异或的特性, 这题可以有更好的解法
    // 对于异或运算, 我们有
    // 如果a ^ b = c, 那么a ^ c = b
    // 根据这个定理, 我们从最高位开始找, 我们先将所有数的最高位存到一个Set中. 
    // 然后, 我们假设最终答案的最高位为1, 将数列中所有数的最高位和1进行异或运算, 异或得到的结果仍然在Set中, 那么说明最终答案的最高位一定为1, (由定理可得1 ^ x = b <==> b ^ x = 1, 对与数x, 一定存在一个数b, 使得x ^ b = 1), 否则最高位的答案一定为0.
    // 假设我们已经知道最终答案的最高k位为prefix, 我们先将数列中所有数的最高k+1位存入Set中. 
    // 然后, 我们假设下一位的值为1, 将数列中所有数的最高k+1位与prefix*2 + 1进行异或运算, 如果异或得到的结果仍然在Set中, 那么说明最终答案的第k+1位一定为1, 否则, 最高位的答案一定为0.
    // 因为x ^ (prefix*2+1) = b　<==> x ^ b = prefix*2+1, 即对于数x, 一定存在一个数b, 使得他们异或的结果为prefix*2+1.
    // 因此, 我们可以对最终答案的32位进行依次判断, 最终得到异或的最大值.
    // 该算法的时间复杂度为O(32n) = O(n)
    public int findMaximumXOR_Bit(int[] nums) {
        int max = 0;
        int mask = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 30; i >= 0; i--) {
            mask = mask | (1 << i);
            int tmp = max | (1 << i);
            for (int n : nums) {
                if (set.contains(tmp ^ (n & mask))) {
                    max = tmp;
                    break;
                }
                set.add(n & mask);
            }
            set.clear();
        }
        return max;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final TrieNode root = new TrieNode();
    private final int mask = 1073741824;

    public int findMaximumXOR_Trie(int[] nums) {
        for (int num : nums) {
            insert(num);
        }
        int max = 0;
        for (int num : nums) {
            TrieNode temp = root;
            int XOR = 0;
            for (int i = 0; i < 31; i++) {
                XOR <<= 1;
                if ((num & mask) == 0) {
                    if (temp.next[1] != null) {
                        temp = temp.next[1];
                        XOR++;
                    } else {
                        temp = temp.next[0];
                    }
                } else {
                    if (temp.next[0] != null) {
                        temp = temp.next[0];
                        XOR++;
                    } else {
                        temp = temp.next[1];
                    }
                }
                num <<= 1;
            }
            max = Math.max(max, XOR);
        }
        return max;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[2];
    }

    private void insert(int num) {
        TrieNode temp = root;
        for (int i = 0; i < 31; i++) {
            if ((num & mask) == 0) {
                if (temp.next[0] == null) {
                    temp.next[0] = new TrieNode();
                }
                temp = temp.next[0];
            } else {
                if (temp.next[1] == null) {
                    temp.next[1] = new TrieNode();
                }
                temp = temp.next[1];
            }
            num <<= 1;
        }
    }

    public static void main(String[] args) {
        //        System.out.println(Integer.toBinaryString(3));
        //        System.out.println(Integer.toBinaryString(10));
        //        System.out.println(Integer.toBinaryString(5));
        //        System.out.println(Integer.toBinaryString(25));
        //        System.out.println(Integer.toBinaryString(2));
        //        System.out.println(Integer.toBinaryString(8));
        //        System.out.println(Integer.toBinaryString(28));
        //        System.out.println((int) Math.pow(2, 30));

        int[] nums = { 3, 4 };
        _0421_Maximum_XOR_of_Two_Numbers_in_an_Array test = new _0421_Maximum_XOR_of_Two_Numbers_in_an_Array();
        System.out.println(test.findMaximumXOR_Bit(nums));
    }
}
