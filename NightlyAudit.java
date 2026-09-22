class LibraryMember {

    private static int membersEnrolled = 0;

    public final String memberNumber;

    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(int borrowLimit) {

        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        membersEnrolled++;

        memberNumber =
            "LIB-" + (100 + membersEnrolled);
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {

        // Reuse the no-argument method
        borrowBook();

        System.out.println(
            "Genre: " + genre
        );
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class NightlyAudit {

    static boolean isValidRenewalCode(
            String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'R') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }

    static String processNightlyAudit(
            LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (int i = 0; i < members.length; i++) {

            if (members[i] == null) {

                nullSkipped++;

            } else {

                processed++;

                if (members[i] instanceof FacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LibraryMember m1 =
            new LibraryMember(3);

        System.out.println(
            m1.memberNumber
        );

        System.out.println(
            LibraryMember.getMembersEnrolled()
        );

        System.out.println(
            isValidRenewalCode("R12A")
        );

        System.out.println(
            isValidRenewalCode("R1A")
        );

        System.out.println(
            isValidRenewalCode("X12A")
        );

        m1.borrowBook();

        m1.borrowBook("Fiction");

        System.out.println(
            "Books Borrowed: "
            + m1.getBooksBorrowed()
        );

        LibraryMember[] members = {

            new FacultyMember(
                5, "Physics"
            ),

            null,

            new LibraryMember(3)
        };

        System.out.println(
            processNightlyAudit(members)
        );
    }
}