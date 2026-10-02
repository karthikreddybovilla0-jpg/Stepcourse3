import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }

    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String getName() {
        return "Bank Transfer";
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

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

class Order {
    private Customer customer;
    private ArrayList<OrderItem> items;
    private String status;

    public Order(Customer customer) {
        this.customer = customer;
        items = new ArrayList<>();
        status = "Pending";
    }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void pay(PaymentMethod payment) {
        if (isEmpty()) {
            System.out.println(
                "Cannot process payment for an empty order.");
            return;
        }

        System.out.println(
            "Payment initiated via " +
            payment.getName() +
            " for Order " +
            customer.getName() + ".");

        boolean success =
                payment.processPayment(getTotal());

        if (success) {
            status = "Paid";

            System.out.println(
                "Payment for Order " +
                customer.getName() +
                " successful.");

            System.out.println(
                "Order status: Paid.");
        } else {
            System.out.println(
                "Payment for Order " +
                customer.getName() +
                " failed.");

            System.out.println(
                "Order status: Pending.");
        }
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Customer x = new Customer("X");

        Product a = new Product("Product A", 100);
        Product b = new Product("Product B", 200);

        Order orderX = new Order(x);

        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);

        System.out.println(
            "Order created for Customer X.");

        orderX.pay(new CreditCardPayment());

        Customer y = new Customer("Y");

        Order orderY = new Order(y);

        orderY.pay(new CreditCardPayment());

        Customer z = new Customer("Z");

        Product c = new Product("Product C", 300);

        Order orderZ = new Order(z);

        orderZ.addProduct(c, 1);

        System.out.println(
            "Order created for Customer Z.");

        orderZ.pay(new PayPalPayment());
    }
}