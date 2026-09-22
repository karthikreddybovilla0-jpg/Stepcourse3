public class SrmStudent {

    static String collegeName;
    static int academicYear;

    String name;

    // Static block runs only once
    static {
        collegeName = "SRM";
        academicYear = 2026;

        System.out.println("College info loaded");
    }

    SrmStudent(String name) {
        this.name = name;
    }

    void display() {
        System.out.println("Student record created: " + name);
    }

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya",
            "Anitha"
        };

        for (int i = 0; i < names.length; i++) {

            SrmStudent student =
                new SrmStudent(names[i]);

            student.display();
        }
    }
}