import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {

        LocalDateTime date = LocalDateTime.parse("2024-09-30T15:40:00");
        ZoneId defaultZone = ZoneId.systemDefault();

        ZoneId newZone = ZoneId.of("Europe/London");
        LocalDateTime newDateTime = date.atZone(defaultZone).withZoneSameInstant(newZone).toLocalDateTime();

        System.out.println(newDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}