package paradigm.functional;

import java.util.function.BiFunction;

/**
 *
 * @author Panyaprach Tularak
 */
public class RecursiveFunction {

    public static void main(String[] args) {
        System.out.println("3! is 3 x 2 x 1 = " + factorial(3));
        System.out.println("GDC of 12 16 24 = " + find(RecursiveFunction::gcd, 12, 16, 24));
        System.out.println("LCM of 12 16 24 = " + find(RecursiveFunction::lcm, 12, 16, 24));
    }

    static int factorial(int n) {
        assert n > 0;

        return n <= 2 ? n : n * factorial(n - 1);
    }

    static int find(BiFunction<Integer, Integer, Integer> function, int... numbers) {
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = function.apply(result, numbers[i]);
        }
        return result;
    }

    static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
        //return a == 0 || b == 0 ? 0 : a == b ? a : a > b ? gcd(a-b,b) : gcd(a, b-a);
    }

}
