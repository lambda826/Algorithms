///**
// *  @author Yunxiang He
// *  @date Jan 16, 2018 7:03:28 PM
// */
//
//package coding.leetcode.temp;
//
///*
//
//Divide two integers without using multiplication, division and mod operator.
//
//If it is overflow, return MAX_INT.
//
// */
//
//public class _0029_Divide_Two_Integers {
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Need to use negative to avoid overflow
//    public int divide_Math(int dividend, int divisor) {
//        if (dividend == Integer.MIN_VALUE && divisor == -1) {
//            return Integer.MAX_VALUE;
//        } else if (divisor == 1) {
//            return dividend;
//        } else if (dividend > 0) {
//            return -divide_Math(-dividend, divisor);
//        } else if (divisor > 0) {
//            return -divide_Math(dividend, -divisor);
//        } else {
//            return divideAux(dividend, divisor);
//        }
//    }
//
//    private int divideAux(int dividend, int divisor) {
//        if (dividend > divisor) {
//            return 0;
//        }
//        int count = 1;
//        int _divisor = divisor;
//        while (_divisor << 1 < 0 && (_divisor << 1) > dividend) {
//            _divisor <<= 1;
//            count <<= 1;
//        }
//        return count + divideAux(dividend - _divisor, divisor);
//    }
//
//    public int divide(int dividend, int divisor) {
//        if (Math.abs(dividend) > Math.abs(divisor)) {
//            return 0;
//        }
//        if (dividend > 0 && divisor > 0) {
//            int tempDivisor = divisor;
//            int count = 1;
//            while ((temDivisor << 1 < dividend)) {
//                temDivisor <<= 1;
//                count <<= 1;
//            }
//            return count + divideAux(dividend - tempDivisor, divisor);
//
//        } else if(dividend < 0 && divisor <0) {
//            return divide(Math.abs(divident), Math.abs(divisor));
//        } else if (divident > 0 || divisor < 0) {
//            return -divide(Math.abs(divident), Math.abs(divisor));
//        }
//    }
//    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//    }
//}