
import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract int getPay();
    abstract String getType();

    public String toString() {
        return "Payslip[name=" + name + ", type=" +
            getType() + ", pay=" + getPay() + "]";
    }
}

class FullTime extends Staff {
    int salary;

    FullTime(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    int getPay() {
        return salary;
    }

    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    int hours, rate;

    PartTime(String name, int hours, int rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    int getPay() {
        return hours * rate;
    }

    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    int stipend;

    Intern(String name, int stipend) {
        super(name);
        this.stipend = stipend;
    }

    int getPay() {
        return stipend;
    }

    String getType() {
        return "Intern";
    }
}

public class PayrollRegisterSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Staff> list = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();

            if (type.equals("FullTime")) {
                list.add(new FullTime(name, sc.nextInt()));
            } else if (type.equals("PartTime")) {
                int hours = sc.nextInt();
                int rate = sc.nextInt();
                list.add(new PartTime(name, hours, rate));
            } else if (type.equals("Intern")) {
                list.add(new Intern(name, sc.nextInt()));
            }
        }

        int total = 0;
        Staff topper = list.get(0);

        for (Staff s : list) {
            System.out.println(s);
            total += s.getPay();

            if (s.getPay() > topper.getPay()) {
                topper = s;
            }
        }

        System.out.println("Total " + total);
        System.out.println("top earner " + topper.name);
        sc.close();
    }
}
