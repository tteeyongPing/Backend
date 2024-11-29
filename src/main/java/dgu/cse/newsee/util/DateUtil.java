package dgu.cse.newsee.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * ISO_OFFSET_DATE_TIME 형식의 문자열에서 날짜 부분만 추출
     */
    public static String extractDate(String dateTimeString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME; // 입력 형식
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, inputFormatter); // 문자열 -> LocalDateTime 변환
        return localDateTime.toLocalDate().toString(); // LocalDateTime에서 날짜 부분만 반환
    }

    /**
     * yyyy-MM-dd HH:mm:ss 형식의 문자열에서 날짜 부분만 추출
     */
    public static String extractDateFromCustomFormat(String dateTimeString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 입력 형식
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, inputFormatter); // 문자열 -> LocalDateTime 변환
        return localDateTime.toLocalDate().toString(); // LocalDateTime에서 날짜 부분만 반환
    }

    public static Duration getDurationUntilMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT).plusDays(1);
        return Duration.between(now, midnight);
    }

}
