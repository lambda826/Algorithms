/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

/*

Imagine you have a special keyboard with the following keys:

Key 1: (A): Print one 'A' on screen.

Key 2: (Ctrl-A): Select the whole screen.

Key 3: (Ctrl-C): Copy selection to buffer.

Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation: 
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A

Example 2:
Input: N = 7
Output: 9
Explanation: 
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V

Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.

 */

public class _0651_4_Keys_Keyboard {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    分析： 
    //    首先，N<=6时最大字符数即为N，A的输入次数<=5，且只会在一开始输入，一旦开始CV操作便不会再输入。 
    //    Ctrl+A，Ctrl+C，Ctrl+V一套下来会使之前的字符数*2，如果再加一个Ctrl+V就是*3（4次操作），再加一个是*4（5次操作），最多到*5（6次操作）。 
    //    因为五次Ctrl+V（7次操作）即n*6等同于n*2*3，没有意义。 
    //    因此，例如N=12可以拆分为4（输入3次A，3次操作）*3（CtrlA，CtrlC，CtrlV，CtrlV，4次操作）*3（同上，4次操作） 
    //    可以发现，复制时的操作次数-1=复制次数，而输入的操作次数=字符数； 
    //    但输入只进行一次，因此可以在一开始就将N+1，方便后面的计算。
    //
    //    另一方面，n*5在大部分情况下可以转化： 
    //    n*3*5(n*15)=n*4*4(n*16);(4+6=5+5操作次数)，后者字符数更多 
    //    n*4*4*5(n*80)=n*3*3*3*3(n*81);(5+5+6=4+4+4+4)，后者字符数更多 
    //    n*3*4*5=n*4*4*4，同第一种情况 
    //    …… 
    //    可以看出，*5操作唯有在N=5和N=10（N+1==11，操作次数11=5+6=>4*5）时不能转化，必须使用，做一个判断即可。 
    public int maxA_DP(int N) {
        if (N <= 6) {
            return N;
        }
        int[] dp = new int[N + 1];
        for (int i = 0; i <= 6; i++) {
            dp[i] = i;
        }
        for (int i = 7; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 4] * 3, dp[i - 5] * 4);
        }
        return dp[N];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxA_DP2(int N) {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            // 1, 2, 3, 4, 5 都是加一个A
            // 从6起才是复制粘贴
            dp[i] = dp[i - 1] + 1;
            for (int k = 1; k < i - 2; k++) {
                //                dp[i] = Math.max(dp[i], dp[k] + dp[k] * (i - 2 - k));
                dp[i] = Math.max(dp[i], dp[k] * (i - 1 - k));
            }
        }
        return dp[N];
    }

}
