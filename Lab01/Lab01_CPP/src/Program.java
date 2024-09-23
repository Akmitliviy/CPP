import java.util.*;

public class Program {
    public static void main(String[] args) {
        Set<String> sports1 = new HashSet<>(Arrays.asList("Basketball"));
        Set<String> sports2 = new HashSet<>(Arrays.asList("Football"));
        Set<String> sports3 = new HashSet<>(Arrays.asList("Volleyball"));
        Set<String> sports4 = new HashSet<>(Arrays.asList("Football", "Basketball"));
        Set<String> sports5 = new HashSet<>(Arrays.asList("Football", "Volleyball"));
        Set<String> sports6 = new HashSet<>(Arrays.asList("Basketball", "Volleyball"));
        Set<String> sports7 = new HashSet<>(Arrays.asList("Football", "Basketball", "Volleyball"));

        List<Athlete> athletes = Arrays.asList(
                new Athlete("John", "Doe", sports1, 8, 25),
                new Athlete("Jane", "Smith", sports2, 3, 30),
                new Athlete("Alex", "Brown", sports3, 6, 22),
                new Athlete("Michael", "Johnson", sports4, 10, 27),
                new Athlete("Emily", "Davis", sports5, 4, 28),
                new Athlete("Sarah", "Wilson", sports6, 5, 26),
                new Athlete("James", "Taylor", sports7, 12, 32),
                new Athlete("Oliver", "Miller", sports1, 7, 21),
                new Athlete("Sophia", "Jones", sports2, 2, 24),
                new Athlete("Liam", "Garcia", sports3, 9, 29),
                new Athlete("Amelia", "Martinez", sports4, 6, 23),
                new Athlete("Lucas", "Rodriguez", sports5, 8, 31),
                new Athlete("Mia", "Hernandez", sports6, 5, 26),
                new Athlete("Noah", "Lopez", sports7, 14, 30),
                new Athlete("Ava", "Gonzalez", sports1, 6, 22),
                new Athlete("Mason", "Wilson", sports2, 3, 27),
                new Athlete("Isabella", "Anderson", sports3, 7, 24),
                new Athlete("Ethan", "Thomas", sports4, 11, 29),
                new Athlete("Charlotte", "Jackson", sports5, 5, 25),
                new Athlete("William", "White", sports6, 4, 30),
                new Athlete("Henry", "Harris", sports7, 13, 28),
                new Athlete("Evelyn", "Clark", sports1, 9, 23),
                new Athlete("Lily", "Lewis", sports2, 4, 26),
                new Athlete("Jack", "Walker", sports3, 6, 32),
                new Athlete("Elijah", "Hall", sports4, 8, 31),
                new Athlete("Grace", "Young", sports5, 2, 27),
                new Athlete("Logan", "King", sports6, 7, 28),
                new Athlete("Chloe", "Wright", sports7, 10, 33),
                new Athlete("Benjamin", "Scott", sports1, 3, 24),
                new Athlete("Zoe", "Green", sports2, 5, 31)
        );

        SportsCompetitionStreamApi usingAPI = new SportsCompetitionStreamApi(athletes);
        SportsCompetitionNoStreamApi noUsingAPI = new SportsCompetitionNoStreamApi(athletes);
    }
}
