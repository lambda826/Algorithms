///**
// *  @author Yunxiang He
// *    @date 04/05/2018
// */
//
//package algorithms.sorting;
//
//import common.utils.StdOut;
//
//import java.util.Comparator;
//
//public class SortHelper {
//    // is v < w ?
//    public static boolean less(Comparable v, Comparable w) {
//        return v.compareTo(w) < 0;
//    }
//
//    // is v < w ?
//    public static boolean less(Object v, Object w, Comparator comparator) {
//        return comparator.compare(v, w) < 0;
//    }
//
//    // exchange a[i] and a[j]
//    public static void exch(Object[] a, int i, int j) {
//        Object swap = a[i];
//        a[i] = a[j];
//        a[j] = swap;
//    }
//
//    // exchange a[i] and a[j]  (for indirect sort)
//    public static void exch(int[] a, int i, int j) {
//        int swap = a[i];
//        a[i] = a[j];
//        a[j] = swap;
//    }
//
//    public static boolean isSorted(Comparable[] a) {
//        return isSorted(a, 0, a.length);
//    }
//
//    // is the array a[lo..hi) sorted
//    public static boolean isSorted(Comparable[] a, int lo, int hi) {
//        for (int i = lo + 1; i < hi; i++) {
//            if (less(a[i], a[i - 1])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isSorted(Object[] a, Comparator comparator) {
//        return isSorted(a, 0, a.length, comparator);
//    }
//
//    // is the array a[lo..hi) sorted
//    public static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
//        for (int i = lo + 1; i < hi; i++) {
//            if (less(a[i], a[i - 1], comparator)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // print array to standard output
//    public static void show(Comparable[] a) {
//        for (int i = 0; i < a.length; i++) {
//            StdOut.println(a[i]);
//        }
//    }
//}
