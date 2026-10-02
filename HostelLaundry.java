interface WashType {
    String getName();
    int getDuration();
    double getCharge();
}

class QuickWash implements WashType {
    public String getName() {
        return "Quick";
    }

    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }
}

class NormalWash implements WashType {
    public String getName() {
        return "Normal";
    }

    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }
}

class HeavyWash implements WashType {
    public String getName() {
        return "Heavy";
    }

    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
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

class WashingMachine {
    private String id;
    private boolean busy;

    public WashingMachine(String id) {
        this.id = id;
        busy = false;
    }

    public String getId() {
        return id;
    }

    public boolean isFree() {
        return !busy;
    }

    public void startWash() {
        busy = true;
    }

    public void completeWash() {
        busy = false;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public String start() {
        machine.startWash();
        return washType.getName() + " wash started on " +
               machine.getId() + " for " + student.getName() +
               " (" + washType.getDuration() + " min). Charge: ₹" +
               String.format("%.2f", washType.getCharge());
    }

    public void complete() {
        machine.completeWash();
        System.out.println(machine.getId() + " cycle completed.");
        System.out.println(machine.getId() + " is now free.");
    }
}

public class HostelLaundry {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle c1 = new WashCycle(asha, m1, new QuickWash());
        System.out.println(c1.start());

        if (!m1.isFree()) {
            System.out.println("Machine M1 is currently busy.");
        }

        WashCycle c2 = new WashCycle(ravi, m2, new HeavyWash());
        System.out.println(c2.start());

        c1.complete();

        WashCycle c3 = new WashCycle(neha, m1, new NormalWash());
        System.out.println(c3.start());
    }
}