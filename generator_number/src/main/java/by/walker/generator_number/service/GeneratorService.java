package by.walker.generator_number.service;

import by.walker.generator_number.util.Counter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneratorService {

    private final RedisService redisService;

    private final Counter counter;

    @Value("${spring.number.dateFormat}")
    public String datePattern;

    @Value("${spring.number.numberFormat}")
    private String numberFormat;

    @Transactional
    public String getGenNumber() {

        int number = getNextNumber();

        String date = getStringLocalDate();
        String fullNumber = String.format(numberFormat, number, date);

        saveNumber(fullNumber, date);

        return fullNumber;
    }

    private void saveNumber(String numString, String date) {
        redisService.save(numString, date);
    }

    private String getStringLocalDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return localDate.format(formatter);
    }

    private int getNextNumber() {
        counter.increment();
        return counter.getCount();
    }

    public GeneratorService(RedisService redisService, Counter counter) {
        this.redisService = redisService;
        this.counter = counter;
    }
}
