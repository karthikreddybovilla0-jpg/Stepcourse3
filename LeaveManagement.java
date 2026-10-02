import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(long days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(long days) {
        return days > 0;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(long days) {
        return days > 0;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    public boolean canTakeLeave(long days) {
        return days > 0;
    }
}

class LeaveRequest {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public LeaveRequest(Employee employee,
                        LocalDate startDate,
                        LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        status = "Pending";
    }

    public void submit() {
        long days = ChronoUnit.DAYS.between(
                startDate, endDate) + 1;

        if (employee.canTakeLeave(days)) {
            System.out.println(
                "Leave request submitted for " +
                employee.getName() + " (" +
                startDate + "-" + endDate +
                "). Status: Pending.");
        }
    }

    public void approve() {
        if (status.equals("Pending")) {
            status = "Approved";

            System.out.println(
                employee.getName() +
                "'s leave request (" +
                startDate + "-" + endDate +
                ") approved. Status: Approved.");
        }
    }

    public void reject() {
        if (status.equals("Pending")) {
            status = "Rejected";

            System.out.println(
                employee.getName() +
                "'s leave request (" +
                startDate + "-" + endDate +
                ") rejected. Status: Rejected.");
        }
    }

    public void changeToPending() {
        if (!status.equals("Pending")) {
            System.out.println(
                "Cannot change leave request status from " +
                status + " to Pending.");
        }
    }
}

public class LeaveManagement {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 = new LeaveRequest(
                john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5)
        );

        r1.submit();
        r1.approve();
        r1.changeToPending();

        LeaveRequest r2 = new LeaveRequest(
                jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11)
        );

        r2.submit();
        r2.reject();
    }
}