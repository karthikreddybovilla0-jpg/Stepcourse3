class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null ||
            memberId.trim().length() < 4) {

            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
            "Standard Member | Sessions: "
            + sessionsAttended
        );
    }

    public static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++) {

            try {
                new GymMember(memberIds[i], monthlyFee);
                signedUp++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp
                + " | Rejected: " + rejected;
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }
}

public class GymFoundation {

    public static void main(String[] args) {

        try {
            GymMember m =
                new GymMember("GM1", 1000);

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Construction rejected"
            );
        }

        PremiumMember p =
            new PremiumMember(
                "MEM01",
                2000,
                "Coach Riya"
            );

        p.attendSession();
        p.attendSession();

        System.out.println(
            p.getSessionsAttended()
        );

        String[] ids = {
            "MEM1", "GM1", "MEM2", " ", "MEM3"
        };

        System.out.println(
            GymMember.signUpBatch(ids, 1000)
        );
    }
}