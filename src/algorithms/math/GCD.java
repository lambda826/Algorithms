package algorithms.math;

public class GCD {

    /**
     * 欧几里得公式 gcd(a,b)=gcd(a%b, b), a > b
     */
    public static int gcd(int lager, int smaller) {
        if (smaller == 0) {
            return lager;
        } else {
            return gcd(smaller, lager % smaller);
        }
    }

    public static void main(String[] args) {
        System.out.println(gcd(15, 12));
    }
}
