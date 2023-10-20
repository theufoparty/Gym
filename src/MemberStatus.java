public enum MemberStatus {
    // Enum MemberStatus definierar möjliga medlemsstatusar och tillhörande meddelanden.

    VALID_MEMBER("Personen är en godkänd medlem."), // Status: Godkänd medlem
    FORMER_MEMBER("Personen är en före detta medlem."), // Status: Före detta medlem
    UNAUTHORIZED_MEMBER("Personen finns inte i filen och är obehörig."); // Status: Obehörig medlem

    private final String message;

    // Constructor som kopplar varje medlemsstatus till dess meddelande.
    MemberStatus(String message) {
        this.message = message;
    }

    // En metod som returnerar meddelandet för en specifik medlemsstatus.
    public String getMessage() {
        return message;
    }
}
