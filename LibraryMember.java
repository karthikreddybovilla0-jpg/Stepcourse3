public class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    private boolean membershipIdSet = false;

    // Public no-argument constructor
    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    // Write-once setter
    public void setMembershipId(String id) {

        if (!membershipIdSet) {
            membershipId = id;
            membershipIdSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        // Simple deterministic one-way transformation
        securityAnswer = Integer.toHexString(
            answer.hashCode()
        );
    }

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(
            "Membership ID: " + m.getMembershipId()
        );

        m.setMembershipId("FAKE-0000");

        System.out.println(
            "After second ID attempt: "
            + m.getMembershipId()
        );

        System.out.println(
            "Premium: " + m.isPremiumMember()
        );

        m.setSecurityAnswer("BlueMountain");

        System.out.println("Security answer stored");
    }
}