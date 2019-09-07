package date;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Panyaprach Tularak on Apr 12, 2019
 */
public class OrderDateExample {

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();
        long aDay = TimeUnit.DAYS.toMillis(1);
        Date today = new Date();
        Date yesterday = new Date(currentTime - aDay);
        Date tomorrow = new Date(currentTime + aDay);
        List<Date> dateList = Arrays.asList(today, tomorrow, yesterday);
        DateComparator comparator = new DateComparator();
        System.out.println("========= Start Date =========");
        dateList.forEach(System.out::println);
        System.out.println("========== Sort ASC ==========");
        dateList.sort(comparator::ascending);
        dateList.forEach(System.out::println);
        System.out.println("========= Sort DESC ==========");
        dateList.sort(comparator::descending);
        dateList.forEach(System.out::println);
    }   
}
