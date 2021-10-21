package seedu.address.commons.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Helper functions for handling dates and times.
 */
public class DateTimeUtil {

    private static final long ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000;

    /**
     * Returns true if the {@code date} contains the {@code timestamp}.
     */
    public static boolean isDateContainsTimestamp(Date date, long timestamp) {
        Timestamp dateToTimestamp = new Timestamp(date.getTime());
        long start = dateToTimestamp.getTime();
        long end = dateToTimestamp.getTime() + ONE_DAY_MILLISECONDS;
        return timestamp >= start && timestamp < end;
    }

    /**
     * Returns date converted by {@code timestamp}.
     */
    public static Date timestampToDate(long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        return new Date(stamp.getTime());
    }
}
