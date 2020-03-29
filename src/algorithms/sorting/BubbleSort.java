///**
// *  @author Yunxiang He
// *    @date 10/21/2018
// */
//
//package algorithms.sorting;
//
//
//import java.util.Arrays;
//import java.util.Random;
//
///*
//
//Bubble sort is the simplest sorting algorithm.
//It works by iterating the input array from the first element to the last, comparing each pair of elements and swapping them if needed.
//
//*/
//public class BubbleSort {
//
//    public static int[] sort(int[] A) {
//        for (int i = 0; i < A.length - 1; i++) {
//            for (int j = 0; j < A.length - i - 1; j++) {
//                if (A[j] > A[j + 1]) {
//                    ArrayUtils.swap(A, j, j + 1);
//                }
//            }
//        }
//        return A;
//    }
//
//    public static void main(String[] args) {
//        Random random = new Random();
//        int[] A = new int[10];
//        for (int i = 0; i < A.length; i++) {
//            A[i] = random.nextInt(20);
//        }
//        System.out.println(Arrays.toString(A));
//        System.out.println(Arrays.toString(sort(A)));
//    }
//}
