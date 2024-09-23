import java.util.*;

public class SportsCompetitionNoStreamApi {
    private List<Athlete> athletes;

    public SportsCompetitionNoStreamApi(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public Map<Boolean, List<Athlete>> splitBySportsCount() {
        Map<Boolean, List<Athlete>> result = new HashMap<>();
        result.put(true, new ArrayList<>()); // Multiple sports
        result.put(false, new ArrayList<>()); // Single sport

        for (Athlete athlete : athletes) {
            if (athlete.getSports().size() > 1) {
                result.get(true).add(athlete);
            } else {
                result.get(false).add(athlete);
            }
        }
        return result;
    }

    public Map<String, List<Athlete>> groupBySport() {
        Map<String, List<Athlete>> sportGroups = new HashMap<>();
        for (Athlete athlete : athletes) {
            for (String sport : athlete.getSports()) {
                sportGroups.computeIfAbsent(sport, k -> new ArrayList<>()).add(athlete);
            }
        }
        return sportGroups;
    }

    public Map<String, Integer> countAthletesWithMoreThanFiveMedals() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Athlete athlete : athletes) {
            if (athlete.getMedals() > 5) {
                for (String sport : athlete.getSports()) {
                    countMap.put(sport, countMap.getOrDefault(sport, 0) + 1);
                }
            }
        }
        return countMap;
    }

    public List<Athlete> sortAthletesByMedalsAndAge() {
        athletes.sort(Comparator.comparingInt(Athlete::getMedals).reversed()
                .thenComparingInt(Athlete::getAge));
        return athletes;
    }

    public Set<String> getUniqueSports() {
        Set<String> uniqueSports = new HashSet<>();
        for (Athlete athlete : athletes) {
            uniqueSports.addAll(athlete.getSports());
        }
        return uniqueSports;
    }

    public String findTopAthleteInSport(String sport) {
        Athlete topAthlete = null;
        for (Athlete athlete : athletes) {
            if (athlete.getSports().contains(sport)) {
                if (topAthlete == null || athlete.getMedals() > topAthlete.getMedals()) {
                    topAthlete = athlete;
                }
            }
        }
        return Optional.ofNullable(topAthlete)
                .map(a -> a.getFirstName() + " " + a.getLastName() + " with " + a.getMedals() + " medals")
                .orElse("No athlete found in " + sport);
    }
}