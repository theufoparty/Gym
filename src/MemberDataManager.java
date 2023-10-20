import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;

public class MemberDataManager {

    private final String filePath;

    //En mapp för att lagra medlemsdata.
    private final Map<String, LocalDate> memberData = new HashMap<>();

    // Konstruktorn tar in en filväg som parameter för att ange vilken fil som innehåller medlemsdata.
    public MemberDataManager(String filePath) {
        this.filePath = filePath;
    }

    //Metoden loadMemberData() används för att ladda medlemsdata från filen.
    //Den tolkar filen rad för rad och separerar medlems id, namn och betalningsdatum.
    public void loadMemberData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentMember = null;
            LocalDate lastPaymentDate = null;

            while ((line = reader.readLine()) != null) {
                // Om raden matchar formatet "10 siffror, namn" antas det vara en medlemsrad.
                if (line.matches("\\d{10}, .+")) {
                    if (currentMember != null) {
                        memberData.put(currentMember, lastPaymentDate);
                    }
                    String[] parts = line.split(", ");
                    currentMember = parts[0];
                }
                // Om raden matchar formatet "ÅÅÅÅ-MM-DD" antas det vara ett datum.
                else if (line.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    lastPaymentDate = LocalDate.parse(line, DateTimeFormatter.ISO_LOCAL_DATE);
                }
            }

            // Lägg till den sista medlemmens betalningsinformation om den finns.
            if (currentMember != null) {
                memberData.put(currentMember, lastPaymentDate);
            }
        }
    }

    // Metoden checkMemberStatus(String input) används för att kontrollera medlemsstatus
    // baserat på angivet medlems id. Den jämför det senaste betalningsdatumet med ett år
    // tillbaka i tiden och avgör om medlemmen är giltig, före detta medlem eller inte auktoriserad.
    public MemberStatus checkMemberStatus(String input) {
        if (memberData.containsKey(input)) {
            LocalDate lastPaymentDate = memberData.get(input);
            LocalDate currentDate = LocalDate.now();
            LocalDate oneYearAgo = currentDate.minusYears(1);

            // Om den senaste betalningsdagen är inom det senaste året är medlemmen giltig.
            if (lastPaymentDate.isAfter(oneYearAgo)) {
                return MemberStatus.VALID_MEMBER;
            } else {
                // Annars betraktas medlemmen som före detta medlem.
                return MemberStatus.FORMER_MEMBER;
            }
        } else {
            // Om medlems id inte hittas i datan, anses användaren inte vara auktoriserad.
            return MemberStatus.UNAUTHORIZED_MEMBER;
        }
    }
}