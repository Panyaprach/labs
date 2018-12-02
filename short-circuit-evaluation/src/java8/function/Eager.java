package java8.function;

/**
 *
 * @author Panyaprach Tularak
 */
public interface Eager {

    static void match(boolean x, boolean y) {
        System.out.println(x && y ? "match" : "incompatible!");
    }
}
