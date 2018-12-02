package com.spantech.laziness;

import java.util.function.Supplier;

/**
 *
 * @author Panyaprach Tularak
 */
/*
    Lazy evaluation is an evaluation strategy which delays the evaluation of
    an expression until its value is needed. The opposite of this is eager evaluation,
    where an expression is evaluated as soon as it is bound to a variable.
 */
public class ArgumentsEvaluation {

    public static void main(String args[]) {
        String a = "Ant", b = "Bird";
        // Before Java8
        Eager.match(exec(a), exec(b));
        // Since Java8
        Lazy.match(() -> exec(a), () -> exec(b));
    }

    static boolean exec(String str) {
        System.out.println("executing.. " + str);
        return str.contains("a");
    }

}

interface Eager {

    static void match(boolean x, boolean y) {
        System.out.println(x && y ? "match" : "incompatible!");
    }
}

interface Lazy {

    static void match(Supplier<Boolean> x, Supplier<Boolean> y) {
        System.out.println(x.get() && y.get() ? "match" : "incompatible!");
    }
}
