public class EmployeeBonus {

    String id;
    double salary;

    // Constructor
    EmployeeBonus(String id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    // this resolves the parameter/field name clash
    void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }

    void display() {
        System.out.println(id + " | Final Salary: Rs " + salary);
    }

    public static void main(String[] args) {

        EmployeeBonus[] employees = {
            new EmployeeBonus("E-101", 40000),
            new EmployeeBonus("E-102", 55000),
            new EmployeeBonus("E-103", 62000),
            new EmployeeBonus("E-104", 48000)
        };

        for (int i = 0; i < employees.length; i++) {
            employees[i].raiseSalary(5000);
            employees[i].display();
        }
    }
}