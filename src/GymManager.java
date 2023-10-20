import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GymManager {
    private final MemberDataManager memberDataManager;
    private final TrainingSessionManager trainingSessionManager;

    // Konstruktor för GymManager-klassen.
    public GymManager() {
        // Skapar en instans av MemberDataManager för att hantera medlemsinformation.
        memberDataManager = new MemberDataManager("member_data.txt");

        // Skapar en instans av TrainingSessionManager och skickar med MemberDataManager-objektet som parameter.
        trainingSessionManager = new TrainingSessionManager(memberDataManager); // Skicka med MemberDataManager-objektet
    }

    // Hjälpmetod som används för att samla in och skapa medlemsdata från användaren
    // genom att använda Scanner för inmatning.
    private MemberData getMemberInput() {
        Scanner scanner = new Scanner(System.in);
        String id;

        System.out.print("Ange medlemmens namn: ");
        String name = scanner.nextLine();

        while(true){
            // Ber användaren att ange medlemmens personnummer.
            System.out.print("Ange medlemmens personnummer: ");
            id = scanner.nextLine();
            if(id.length() != 10){
                System.out.println("Vänligen ange ett personnummer med 10 siffror");
            } else {
                break;
            }
        }

        System.out.print("Ange träningsdatum (ÅÅÅÅ-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        return new MemberData(id, name, date);
    }

    // Huvudmetod som hanterar interaktion med användaren.
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String memberID;
        while(true){
            // Ber användaren att ange medlemmens personnummer.
            System.out.print("Ange medlemmens personnummer: ");
            memberID = scanner.nextLine();
            if(memberID.length() != 10){
                System.out.println("Vänligen ange ett personnummer med 10 siffror");
            } else {
                break;
            }
        }

        try {
            // Försöker ladda medlemsdata från en fil med namnet "member_data.txt" med hjälp av MemberDataManager.
            memberDataManager.loadMemberData();
        } catch (IOException e) {
            // Hanterar eventuella fel som kan uppstå vid inläsning av medlemsdata och skriver ut felmeddelandet.
            System.err.println("Fel vid inläsning av medlemsdata: " + e.getMessage());
            return;
        }

        // Kontrollerar medlemskapstatus för det angivna medlemsnumret med hjälp av MemberDataManager.
        MemberStatus status = memberDataManager.checkMemberStatus(memberID);

        // Skriver ut meddelandet som beskriver medlemskapstatusen.
        System.out.println(status.getMessage());

        // Om medlemskapstatusen är "GILTIG_MEDLEM," utför en interaktiv träningspassregistrering med TrainingSessionManager.
        if (status == MemberStatus.VALID_MEMBER) {
            MemberData member = getMemberInput();
            trainingSessionManager.recordInteractiveTrainingSession(member, "training_records.txt");
        }
    }
}
