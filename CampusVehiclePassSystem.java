
import java.util.*;

interface Chargeable {
    void charge();
}

class Vehicle {
    String passNumber, owner, type;
    int fee;

    Vehicle(String type, String passNumber, String owner) {
        this.type = type;
        this.passNumber = passNumber;
        this.owner = owner;
        fee = (type.equals("Bike") || type.equals("EBike")) ? 300 : 1000;
    }
}

class Bike extends Vehicle {
    Bike(String id, String owner) {
        super("Bike", id, owner);
    }
}

class Car extends Vehicle {
    Car(String id, String owner) {
        super("Car", id, owner);
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String id, String owner) {
        super("EBike", id, owner);
    }

    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String id, String owner) {
        super("ECar", id, owner);
    }

    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class CampusVehiclePassSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String op = sc.next();

            if (op.equals("PASS")) {
                String type = sc.next();
                String id = sc.next();
                String owner = sc.next();

                Vehicle v = null;

                switch (type) {
                    case "Bike": v = new Bike(id, owner); break;
                    case "Car": v = new Car(id, owner); break;
                    case "EBike": v = new EBike(id, owner); break;
                    case "ECar": v = new ECar(id, owner); break;
                }

                if (v != null) {
                    vehicles.put(id, v);
                    System.out.println(id + " (" + type +
                        ") pass fee " + v.fee);
                }
            } else if (op.equals("CHARGE")) {
                String id = sc.next();

                if (vehicles.containsKey(id)) {
                    Vehicle v = vehicles.get(id);

                    if (v instanceof Chargeable) {
                        ((Chargeable) v).charge();
                    } else {
                        System.out.println(id +
                            " rejected: charging unsupported");
                    }
                } else {
                    System.out.println("Vehicle not found");
                }
            }
        }
        sc.close();
    }
}
