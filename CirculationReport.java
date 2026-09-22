class LibraryMember {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(
            String memberId,
            int borrowLimit) {

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void displayInfo() {
        System.out.print(
            "General | Books: "
            + booksBorrowed
        );
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }
}

class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {
        System.out.print(
            "Student | Course: "
            + course
            + " | Books: "
            + booksBorrowed
        );
    }
}

public class CirculationReport {

    static String batchPrint(
            LibraryMember[] members) {

        StringBuilder report =
            new StringBuilder();

        for (int i = 0; i < members.length; i++) {

            // Polymorphic call
            members[i].displayInfo();

            // Build the same report using StringBuilder
            if (members[i] instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) members[i];

                report.append(
                    "Student | Course: "
                    + student.getCourse()
                    + " | Books: "
                    + student.getBooksBorrowed()
                    + " [Course via downcast: "
                    + student.getCourse()
                    + "] | "
                );

            } else {

                report.append(
                    "General | Books: "
                    + members[i].getBooksBorrowed()
                    + " | "
                );
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember general =
            new LibraryMember("LB5", 3);

        StudentMember student =
            new StudentMember(
                "STU6", 3, "ECE"
            );

        LibraryMember[] members = {
            general, student
        };

        System.out.println(
            batchPrint(members)
        );
    }
}