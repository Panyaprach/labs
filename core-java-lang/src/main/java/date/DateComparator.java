package date;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Panyaprach Tularak on Apr 13, 2019
 */
public class DateComparator implements Comparator<Date> {

    public int ascending(Date a, Date b) {
        return a.compareTo(b);
    }

    public int descending(Date a, Date b) {
        return -a.compareTo(b);
    }

    @Override
    public int compare(Date a, Date b) {
        return ascending(a, b);
    }

}
