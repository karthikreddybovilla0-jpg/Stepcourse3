interface MembershipPlan {
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }

    public String getName() {
        return "Annual";
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private String status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        status = "Active";
    }

    public void checkIn() {
        if (status.equals("Active")) {
            System.out.println(member.getName() +
                    " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " +
                    member.getName() +
                    "'s membership is " + status + ".");
        }
    }

    public void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";
            System.out.println(member.getName() +
                    "'s membership frozen. Status: Frozen.");
        } else if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
        }
    }

    public void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member.getName() +
                    "'s membership unfrozen. Status: Active.");
        } else if (status.equals("Expired")) {
            System.out.println("Cannot unfreeze an Expired membership.");
        }
    }

    public void expire() {
        status = "Expired";

        System.out.println(member.getName() +
                "'s membership expired. Status: Expired.");
    }

    public double getFee() {
        return plan.calculateFee();
    }

    public String getStatus() {
        return status;
    }
}

public class FitZone {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership a = new Membership(
                asha,
                new QuarterlyPlan()
        );

        Membership r = new Membership(
                ravi,
                new MonthlyPlan()
        );

        System.out.printf(
                "Quarterly membership created for Asha. Fee: ₹%.2f. Status: %s.%n",
                a.getFee(),
                a.getStatus()
        );

        System.out.printf(
                "Monthly membership created for Ravi. Fee: ₹%.2f. Status: %s.%n",
                r.getFee(),
                r.getStatus()
        );

        a.checkIn();
        a.freeze();
        a.checkIn();

        r.expire();
        r.freeze();
    }
}