import java.time.LocalDate;

// Klassen MemberData används för att lagra medlemsdata.
public class MemberData {
    // Instansvariabler (fält) som håller medlemsens ID, namn och födelsedatum.
    private final String id;
    private final String name;
    private final LocalDate date;

    // Konstruktor för att skapa en ny MemberData-instans med givna värden.
    public MemberData(String id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    // Tre getter-metoder som används för att hämta värdena för ID, namn och födelsedatum från en MemberData-instans.

    // Getter-metod för att hämta medlemsens ID.
    public String getId() {
        return id;
    }

    // Getter-metod för att hämta medlemsens namn.
    public String getName() {
        return name;
    }

    // Getter-metod för att hämta medlemsens födelsedatum.
    public LocalDate getDate() {
        return date;
    }
}
