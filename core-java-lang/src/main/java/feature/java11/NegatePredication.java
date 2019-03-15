package feature.java11;

import java.util.*;
// new predication function
import static java.util.function.Predicate.not;

/**
 * Created by Panyaprach Tularak on Mar 16, 2019
 */
public class NegatePredication {

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("hello", "", " ", "I'm not blank");
        strs.stream()
                .filter(not(String::isBlank))
                .forEach(System.out::println);
    }

}
