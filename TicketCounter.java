import java.util.*;

abstract class Seat {
    private String number;

    public Seat(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String number) {
        super(number);
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String number) {
        super(number);
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String number) {
        super(number);
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {
    private String time;
    private boolean started;
    private Set<String> bookedSeats = new HashSet<>();

    public Show(String time) {
        this.time = time;
        started = false;
    }

    public boolean isAvailable(String seat) {
        return !bookedSeats.contains(seat);
    }

    public void bookSeat(String seat) {
        bookedSeats.add(seat);
    }

    public void releaseSeat(String seat) {
        bookedSeats.remove(seat);
    }

    public void startShow() {
        started = true;
    }

    public boolean hasStarted() {
        return started;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private ArrayList<Seat> seats;
    private boolean cancelled;

    public Booking(Customer customer, Show show, ArrayList<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        cancelled = false;
    }

    public double getTotal() {
        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void cancel() {
        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }

        for (Seat seat : seats) {
            show.releaseSeat(seat.getNumber());
        }

        cancelled = true;

        System.out.println(customer.getName() +
                "'s booking cancelled.");
        System.out.print("Seats ");

        for (Seat seat : seats) {
            System.out.print(seat.getNumber() + " ");
        }

        System.out.println("released.");
    }
}

public class TicketCounter {
    static Booking book(Customer customer, Show show, Seat[] seats) {
        if (seats.length > 6) {
            System.out.println("Cannot book more than 6 seats.");
            return null;
        }

        for (Seat seat : seats) {
            if (!show.isAvailable(seat.getNumber())) {
                System.out.println("Seat " + seat.getNumber() +
                        " is already booked for this show.");
                return null;
            }
        }

        ArrayList<Seat> list = new ArrayList<>();

        for (Seat seat : seats) {
            show.bookSeat(seat.getNumber());
            list.add(seat);
        }

        Booking booking = new Booking(customer, show, list);

        System.out.print("Booking confirmed for " +
                customer.getName() + ": ");

        for (Seat seat : seats) {
            System.out.print(seat.getNumber() + " ");
        }

        System.out.println();
        System.out.printf("Total: ₹%.2f%n", booking.getTotal());

        return booking;
    }

    public static void main(String[] args) {
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Booking a = book(
                asha,
                show,
                new Seat[]{
                    new RegularSeat("A1"),
                    new RegularSeat("A2"),
                    new PremiumSeat("F5")
                }
        );

        book(
                ravi,
                show,
                new Seat[]{
                    new RegularSeat("A2")
                }
        );

        Booking r = book(
                ravi,
                show,
                new Seat[]{
                    new ReclinerSeat("R1")
                }
        );

        a.cancel();

        book(
                neha,
                show,
                new Seat[]{
                    new RegularSeat("A2")
                }
        );
    }
}