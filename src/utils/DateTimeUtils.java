package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    private DateTimeUtils(){}

    public static String format(LocalDateTime time){
        return FORMATTER.format(time);
    }
}
