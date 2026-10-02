import java.util.*;

interface NotificationChannel {
    String send(String student, String message);
}

class EmailChannel implements NotificationChannel {
    public String send(String student, String message) {
        return "[Email → " + student + "] " + message;
    }
}

class SmsChannel implements NotificationChannel {
    public String send(String student, String message) {
        return "[SMS → " + student + "] " + message;
    }
}

class AppChannel implements NotificationChannel {
    public String send(String student, String message) {
        return "[App → " + student + "] " + message;
    }
}

class Student {
    private String name;
    private String department;
    private ArrayList<NotificationChannel> channels;

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
        channels = new ArrayList<>();
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {
    private String title;
    private ArrayList<String> departments;

    public Notice(String title, ArrayList<String> departments) {
        this.title = title;
        this.departments = departments;
    }

    public boolean isValid() {
        return title != null &&
               !title.isBlank() &&
               departments != null &&
               !departments.isEmpty();
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {
    private ArrayList<Student> students;

    public NoticeBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println(
                "Cannot post notice: At least one target department is required."
            );
            return;
        }

        System.out.println(
            "Notice '" + notice.getTitle() +
            "' posted to " +
            String.join(", ", notice.getDepartments()) + "."
        );

        for (Student student : students) {
            if (notice.getDepartments().contains(
                    student.getDepartment())) {

                for (NotificationChannel channel :
                        student.getChannels()) {

                    System.out.println(
                        channel.send(
                            student.getName(),
                            notice.getTitle()
                        )
                    );
                }
            }
        }
    }
}

public class NoticeBroadcaster {
    public static void main(String[] args) {
        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        ArrayList<String> cse = new ArrayList<>();
        cse.add("CSE");

        Notice n1 = new Notice(
                "Lab Closed Tomorrow",
                cse
        );

        board.postNotice(n1);

        ArrayList<String> both = new ArrayList<>();
        both.add("CSE");
        both.add("ECE");

        Notice n2 = new Notice(
                "Fee Deadline Extended",
                both
        );

        board.postNotice(n2);

        ArrayList<String> empty = new ArrayList<>();

        Notice n3 = new Notice(
                "Sports Day",
                empty
        );

        board.postNotice(n3);
    }
}