import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private String number;

    public Room(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    public StandardRoom(String number) {
        super(number);
    }

    public double calculatePrice(long days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String number) {
        super(number);
    }

    public double calculatePrice(long days) {
        return days * 180;
    }
}

class Suite extends Room {
    public Suite(String number) {
        super(number);
    }

    public double calculatePrice(long days) {
        return days * 300;
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

class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean cancelled;

    public Reservation(
            Customer customer,
            Room room,
            LocalDate startDate,
            LocalDate endDate,
            LocalDate cancellationDeadline) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
        cancelled = false;
    }

    public boolean overlaps(
            LocalDate start,
            LocalDate end) {

        return !end.isBefore(startDate)
                && !start.isAfter(endDate)
                && !cancelled;
    }

    public void cancel(LocalDate date) {
        if (date.isBefore(cancellationDeadline)
                || date.isEqual(cancellationDeadline)) {

            cancelled = true;

            System.out.println(
                "Reservation for " +
                customer.getName() + ", " +
                room.getClass().getSimpleName() +
                " " + room.getNumber() +
                " (" + startDate + "-" +
                endDate +
                ") cancelled successfully.");
        } else {
            System.out.println(
                "Cancellation deadline has passed.");
        }
    }

    public Room getRoom() {
        return room;
    }
}

class Hotel {
    private ArrayList<Reservation> reservations;

    public Hotel() {
        reservations = new ArrayList<>();
    }

    public boolean isAvailable(
            Room room,
            LocalDate start,
            LocalDate end) {

        for (Reservation r : reservations) {
            if (r.getRoom() == room
                    && r.overlaps(start, end)) {
                return false;
            }
        }

        return true;
    }

    public Reservation book(
            Customer customer,
            Room room,
            LocalDate start,
            LocalDate end,
            LocalDate deadline) {

        if (!isAvailable(room, start, end)) {
            System.out.println(
                room.getClass().getSimpleName() +
                " " + room.getNumber() +
                " is not available from " +
                start + " to " + end + ".");
            return null;
        }

        long days = end.toEpochDay() -
                    start.toEpochDay();

        Reservation r = new Reservation(
                customer,
                room,
                start,
                end,
                deadline
        );

        reservations.add(r);

        System.out.println(
            "Reservation confirmed for " +
            customer.getName() + ", " +
            room.getClass().getSimpleName() +
            " " + room.getNumber() +
            " (" + start + "-" +
            end + ").");

        System.out.printf(
            "Price: $%.2f%n",
            room.calculatePrice(days)
        );

        return r;
    }
}

public class HotelBooking {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room standard = new StandardRoom("101");
        Room deluxe = new DeluxeRoom("201");

        LocalDate start1 =
                LocalDate.of(2026, 1, 1);

        LocalDate end1 =
                LocalDate.of(2026, 1, 5);

        Reservation r1 = hotel.book(
                a,
                standard,
                start1,
                end1,
                LocalDate.of(2025, 12, 30)
        );

        hotel.book(
                b,
                standard,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7),
                LocalDate.of(2026, 1, 1)
        );

        r1.cancel(LocalDate.of(2025, 12, 29));

        hotel.book(
                c,
                deluxe,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12),
                LocalDate.of(2026, 2, 5)
        );
    }
}