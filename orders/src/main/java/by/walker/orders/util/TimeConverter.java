package by.walker.orders.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TimeConverter {

    @Value("${spring.pattern.date}")
    private String datePattern;

    @Value("${spring.pattern.time}")
    private String timePattern;

    public LocalDate getDateFromString(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        return LocalDate.parse(date, dateFormatter);
    }

    public LocalTime getTimeFromString(String time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timePattern);
        return LocalTime.parse(time, timeFormatter);
    }
}
