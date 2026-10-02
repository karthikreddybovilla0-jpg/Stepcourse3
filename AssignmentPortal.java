import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.10;
        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.20;
        return marks * (1 - penalty);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, LocalDate date) {
        this.student = student;
        this.assignment = assignment;
        submissionDate = date;
        status = "Submitted";
    }

    public void grade(double awardedMarks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        long lateDays = 0;

        if (submissionDate.isAfter(assignment.getDueDate())) {
            lateDays = ChronoUnit.DAYS.between(
                assignment.getDueDate(), submissionDate
            );
        }

        finalMarks = assignment.applyPenalty(awardedMarks, lateDays);
        status = "Graded";

        System.out.println(student.getName() + " graded: " +
                String.format("%.0f", finalMarks) + "/" +
                assignment.getMaxMarks() + ". Status: Graded.");
    }

    public void resubmit(LocalDate date) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.getTitle() + "' has already been graded.");
        } else {
            submissionDate = date;
            status = "Submitted";
        }
    }

    public String getStatus() {
        return status;
    }
}

public class AssignmentPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab",
                50,
                LocalDate.of(2026, 3, 10)
        );

        Assignment written = new WrittenAssignment(
                "Design Essay",
                50,
                LocalDate.of(2026, 3, 12)
        );

        Submission s1 = new Submission(
                asha, coding, LocalDate.of(2026, 3, 10)
        );

        Submission s2 = new Submission(
                ravi, written, LocalDate.of(2026, 3, 14)
        );

        System.out.println("Asha's submission for 'Linked List Lab' received (on time). Status: "
                + s1.getStatus() + ".");

        System.out.println("Ravi's submission for 'Design Essay' received (2 days late). Status: "
                + s2.getStatus() + ".");

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(LocalDate.of(2026, 3, 11));
    }
}