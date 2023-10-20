import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MemberDataManagerTest {

    @BeforeEach
    void setUp() {
        // Ställ in eventuell nödvändig testdata eller resurser här
    }

    @AfterEach
    void tearDown() {
        // Städa upp eller frigör resurser efter varje test
    }

    // Detta test är inte helt enligt bästa praxis eftersom det testar två olika metoder.
    // Det är svårt att identifiera vilken metod som är trasig när detta test misslyckas.
    // Men jag ser inte hur man kan verifiera att loadMemberData fungerar korrekt utan att
    // verifiera med checkMembersStatus. Jag ser heller inte hur man kan verifiera att checkMembersStatus
    // fungerar utan att först ladda in medlemmar via loadMemberData.

    @Test
    void checkThatLoadMemberDataCorrectlyLoadsMemberData() {
        // Skapa en instans av den testade klassen.
        MemberDataManager memberDataManager = new MemberDataManager("mock_data.txt");

        try {
            // Ladda medlemsdata.
            memberDataManager.loadMemberData();

            // Kontrollera medlemskaps status för två olika medlems-ID.
            MemberStatus result1 = memberDataManager.checkMemberStatus("7703021234");
            MemberStatus result2 = memberDataManager.checkMemberStatus("7703021242");

            // Kontrollera att resultaten är som förväntat.
            assertEquals(result1, MemberStatus.VALID_MEMBER);
            assertEquals(result2, MemberStatus.UNAUTHORIZED_MEMBER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
