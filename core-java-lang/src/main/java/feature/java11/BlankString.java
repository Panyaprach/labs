package feature.java11;

import java.util.*;

/**
 * Created by Panyaprach Tularak on Mar 16, 2019
 */
class BlankString {

    public static void main(String[] args) {
        List<String> test = Arrays.asList("not empty", "", " ");
        test.stream()
                .forEach(Printer::printBlankAndEmpty);
    }

    interface Printer {

        private static String withQuotation(String str) {
            return "\"" + str + "\"";
        }

        public static void printBlankAndEmpty(String str) {
            printIsBlank(str);
            printIsEmpty(str);
        }

        private static void printIsBlank(String str) {
            String blank = String.format(
                    "%-11s::isBlank => %s",
                    withQuotation(str),
                    str.isBlank()
            );
            System.out.println(blank);
        }

        private static void printIsEmpty(String str) {
            String empty = String.format(
                    "%-11s::isEmpty => %s",
                    withQuotation(str),
                    str.isEmpty()
            );
            System.out.println(empty);
        }
    }
}
