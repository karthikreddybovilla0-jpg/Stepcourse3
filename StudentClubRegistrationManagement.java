
import java.util.*;

class Student {
    String roll, name;

    Student(String roll, String name) {
        this.roll = roll;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;

        Student s = (Student) obj;
        return roll.equals(s.roll);
    }

    public int hashCode() {
        return roll.hashCode();
    }
}

public class StudentClubRegistrationManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Student> members = new HashSet<>();

        while (sc.hasNext()) {
            String op = sc.next();

            if (op.equals("ADD")) {
                String roll = sc.next();
                String name = sc.next();

                if (members.add(new Student(roll, name))) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (op.equals("CONTAINS")) {
                String roll = sc.next();
                String name = sc.next();

                System.out.println("contains: " +
                    members.contains(new Student(roll, name)));
            }
        }

        System.out.println("member count " + members.size());
        sc.close();
    }
}
