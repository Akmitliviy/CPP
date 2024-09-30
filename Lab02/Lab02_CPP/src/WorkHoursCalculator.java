import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkHoursCalculator {


    public static long calculateWorkHours(LocalDate startDate, LocalDate endDate, Map<DayOfWeek, LocalTime[]> workSchedule) {
        long totalHours = 0;

        // Проходимо по кожному дню
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            Set<LocalDate> holidays = GetDatesFromFile();

            // Якщо це робочий день, додаємо години
            if (workSchedule.containsKey(dayOfWeek) && !holidays.contains(date)) {
                LocalTime[] workTimes = workSchedule.get(dayOfWeek);
                if (workTimes != null) {
                    Duration workDuration = Duration.between(workTimes[0], workTimes[1]);
                    totalHours += workDuration.toHours();
                }
            }
        }

        return totalHours;
    }

    private static Set<LocalDate> GetDatesFromFile(){
        Set<LocalDate> dates = new HashSet<>();

        FileHandler handler = new FileHandler("Dates.txt");

        String text;
        try{
            text = handler.readFile();
        }catch(IOException e){
            System.out.println("Error in reading file");
            return dates;
        }

        String regex = "([1-2]\\d{3})\\.(0[1-9]|1[0-2])\\.([0-2][0-9]|3[0-1])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String temp = matcher.group().replace('.', '-');
            dates.add(LocalDate.parse(temp));
        }

        return dates;
    }
}
