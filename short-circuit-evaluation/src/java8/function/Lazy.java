package java8.function;

import java.util.function.Supplier;

/**
 *
 * @author Panyaprach Tularak
 */
public interface Lazy {

    static void match(Supplier<Boolean> x, Supplier<Boolean> y) {
        System.out.println(x.get() && y.get() ? "match" : "incompatible!");
    }
}
