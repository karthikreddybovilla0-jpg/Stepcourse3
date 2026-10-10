
import java.util.Scanner;

class Node {
    String name;
    Node prev, next;

    Node(String name) {
        this.name = name;
        prev = next = null;
    }
}

public class TrainCoachesBothWays {
    Node head = null, tail = null;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    void remove(String name) {
        Node temp = head;

        while (temp != null && !temp.name.equals(name)) {
            temp = temp.next;
        }

        if (temp == null) {
            return;
        }

        if (temp == head) {
            head = temp.next;
        } else {
            temp.prev.next = temp.next;
        }

        if (temp == tail) {
            tail = temp.prev;
        } else {
            temp.next.prev = temp.prev;
        }
    }

    void printForward() {
        Node temp = head;
        System.out.print("Forward: ");

        while (temp != null) {
            System.out.print(temp.name);
            if (temp.next != null) {
                System.out.print(" <-> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    void printBackward() {
        Node temp = tail;
        System.out.print("Backward: ");

        while (temp != null) {
            System.out.print(temp.name);
            if (temp.prev != null) {
                System.out.print(" <-> ");
            }
            temp = temp.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrainCoachesBothWays train = new TrainCoachesBothWays();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            train.addLast(sc.next());
        }

        String name = sc.next();

        train.remove(name);
        train.printForward();
        train.printBackward();

        sc.close();
    }
}