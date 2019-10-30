/**
 *    Copyright 2017 Yunxiang He
 *
 *    All right reserved.
 *
 *    Create on Oct 23, 2017 10:41:26 PM
 */
package algorithms.dynamic_programming;

/**
 * @author Yunxiang He
 * @date Oct 23, 2017 10:41:26 PM
 */
public class Segmented_Least_Squares {

    //	private static int C = 3;
    //
    //	private static double[][] error;
    //
    //	public static int segmentedLeastSquares(int n) {
    //		double m = new double[n];
    //		for(int i = 0; i < n; i++) {
    //			m[i] = Math.min(error[i][j], b)
    //		}
    //		
    //	}
    //
    //	private static void count(Point [] points) {
    //		error = new double[points.length][points.length];
    //		for(int i = 0; i < points.length; i++) {
    //			for(int j = i + 1; j < points.length; j++) {
    //				error[i][j] = minimumError(points, i, j);
    //			}
    //		}
    //	}
    //
    //	private static double minimumError(Point[] points, int i, int j) {
    //		double error = 0;
    //		double a = 0;
    //		double b = 0;
    //
    //		double a1 = 0;
    //		for (int x = i; x <= j; x++) {
    //			a1 += points[x].getX() * points[x].getY();
    //		}
    //		a1 = (j - i) * a1;
    //
    //		double a2 = 0;
    //		double _a2 = 0;
    //		double __a2 = 0;
    //		for (int x = i; x <= j; x++) {
    //			_a2 += points[x].getX();
    //			__a2 += points[x].getY();
    //		}
    //		a2 = _a2 * __a2;
    //
    //		double a3 = 0;
    //		for (int x = i; x <= j; x++) {
    //			a3 += points[x].getX() * points[x].getX();
    //		}
    //		a3 = (j - i) * a3;
    //
    //		double a4 = 0;
    //		for (int x = i; x <= j; x++) {
    //			a4 += points[x].getX();
    //		}
    //		a4 *= a4;
    //
    //		a = (a1 - a2) / (a3 - a4);
    //
    //		double b1 = 0;
    //		for (int x = i; x <= j; x++) {
    //			b1 += points[x].getY();
    //		}
    //
    //		double b2 = 0;
    //		for (int x = i; x <= j; x++) {
    //			b2 += points[x].getX();
    //		}
    //		b2 = a * b2;
    //
    //		b = (b1 - b2) / (j - i);
    //
    //		for (int x = i; x <= j; x++) {
    //			error += Math.pow(points[x].getY() - a * points[x].getX() - b, 2);
    //		}
    //		return error;
    //	}
    //
    //	public static void main(String[] args) {
    //
    //		Point p1 = new Point(1, 1.5);
    //		Point p2 = new Point(2, 2.5);
    //		Point p3 = new Point(3, 3.4);
    //		Point p4 = new Point(4, 6.7);
    //		Point p5 = new Point(5, 8.0);
    //		Point p6 = new Point(6, 11.2);
    //		Point p7 = new Point(7, 12);
    //		Point p8 = new Point(8, 13);
    //		Point p9 = new Point(9, 15.4);
    //		Point p10 = new Point(11, 16.7);
    //		Point[] points = new Point[] { p1, p2, p3, p4, p5, p6, p7, p8, p9, p10 };
    //		count(points);
    //		System.out.println();
    //	}

}
