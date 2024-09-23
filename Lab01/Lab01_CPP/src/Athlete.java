import java.util.Set;

class Athlete {
    private String firstName;
    private String lastName;
    private Set<String> sports;
    private int medals;
    private int age;

    public Athlete(String firstName, String lastName, Set<String> sports, int medals, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sports = sports;
        this.medals = medals;
        this.age = age;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Set<String> getSports() { return sports; }
    public int getMedals() { return medals; }
    public int getAge() { return age; }
}
