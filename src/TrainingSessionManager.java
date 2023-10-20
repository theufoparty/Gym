import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class TrainingSessionManager {

    // En referens till MemberDataManager för att kontrollera medlemsstatus.
    private final MemberDataManager memberDataManager;

    // Konstruktor för TrainingSessionManager, som tar emot en instans av MemberDataManager.
    public TrainingSessionManager(MemberDataManager memberDataManager) {
        this.memberDataManager = memberDataManager;
    }

    // Registrera en interaktiv tränings session för en gym medlem.
    public void recordInteractiveTrainingSession(MemberData member, String filename) {
        // Kontrollerar medlemskapstatus innan den registrerar träningspasset.
        MemberStatus status = memberDataManager.checkMemberStatus(member.getId());
        if (status == MemberStatus.VALID_MEMBER) {
            // Om medlemmen är giltig, lagra tränings sessionsdata i mappen.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                // Lägg till tränings sessions data i en filen "training_records.txt."
                writer.write(member.getName() + ", " + member.getId() + "\n"); // Skriv kundens namn och ID.
                writer.write(member.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "\n"); // Skriv träningsdatumet.
                System.out.println("Träningspasset är tillagt");
            } catch (IOException e) {
                System.err.println("Fel vid skrivning till tränings records filen: " + e.getMessage());
            }
        } else {
            System.out.println("Personen är inte en giltig medlem.");
        }
    }
}
