import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TrainingSessionManagerTest {

    private MemberDataManager memberDataManager;
    private TrainingSessionManager sessionManager;

    @BeforeEach
    void setUp() {
        try {
            // Förbered testmiljön genom att ladda medlemsdata och instansiera TrainingSessionManager.
            memberDataManager = new MemberDataManager("mock_data.txt");
            memberDataManager.loadMemberData();
            sessionManager = new TrainingSessionManager(memberDataManager);
        } catch (IOException e) {
            // Fångar eventuella IOException och omvandlar dem till RuntimeException om de uppstår.
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        // Städa upp efter varje test genom att ta bort mock_output.txt-filen om den finns.
        File mockFile = new File("mock_output.txt");
        mockFile.delete();
    }

    @Test
    void recordInteractiveTrainingSession() {
        try {
            // Skapa en medlemsinstans för teständamål med förväntade värden.
            MemberData member = new MemberData("7703021234", "Alhambra Aromes", LocalDate.parse("2023-07-01", DateTimeFormatter.ISO_LOCAL_DATE));

            // Registrera ett interaktivt träningspass för medlemmen och spara det i mock_output.txt.
            sessionManager.recordInteractiveTrainingSession(member, "mock_output.txt");

            // Verifiera resultatet genom att läsa mock_output.txt och kontrollera att data är korrekt.
            BufferedReader reader = new BufferedReader(new FileReader("mock_output.txt"));
            String line = reader.readLine();
            assertEquals(line, "Alhambra Aromes, 7703021234");
            line = reader.readLine();
            assertEquals(line, "2023-07-01");
        } catch (IOException e) {
            // Fångar eventuella IOException och omvandlar dem till RuntimeException om de uppstår.
            throw new RuntimeException(e);
        }
    }
}

// Det här testet skrevs efter att jag skrivit min kod, för att kunna testa koden så behövde jag
// ändra i min original kod och bryta ut inmatning av data i terminalen vilket gjordes genom att
// skapa en ny klass MemberData. Hade jag lyckats skriva tester innan jag skulle testa min kod så
// hade jag nog tänkt annorlunda och gjort koden testbar ifrån början. :)