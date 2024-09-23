import java.util.*;
import java.util.stream.*;

public class SportsCompetitionStreamApi {
    private List<Athlete> athletes;

    public SportsCompetitionStreamApi(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public Map<Boolean, List<Athlete>> splitBySportsCount() {
        return athletes.stream().collect(Collectors.partitioningBy(
                athlete -> athlete.getSports().size() > 1
        ));
    }

    public Map<String, List<Athlete>> groupBySport() {
        return athletes.stream().flatMap(athlete ->
                athlete.getSports().stream().map(sport ->
                        new AbstractMap.SimpleEntry<>(sport, athlete))
        ).collect(Collectors.groupingBy(Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    public Map<String, Long> countAthletesWithMoreThanFiveMedals() {
        return athletes.stream()
                .filter(athlete -> athlete.getMedals() > 5)
                .flatMap(athlete -> athlete.getSports().stream()
                        .map(sport -> new AbstractMap.SimpleEntry<>(sport, 1)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.counting()));
    }

    public List<Athlete> sortAthletesByMedalsAndAge() {
        return athletes.stream()
                .sorted(Comparator.comparingInt(Athlete::getMedals).reversed()
                        .thenComparingInt(Athlete::getAge))
                .collect(Collectors.toList());
    }

    public Set<String> getUniqueSports() {
        return athletes.stream()
                .flatMap(athlete -> athlete.getSports().stream())
                .collect(Collectors.toSet());
    }

    public String findTopAthleteInSport(String sport) {
        return athletes.stream()
                .filter(a -> a.getSports().contains(sport))
                .max(Comparator.comparingInt(Athlete::getMedals))
                .map(a -> a.getFirstName() + " " + a.getLastName() + " with " + a.getMedals() + " medals")
                .orElse("No athlete found in " + sport);
    }
}
