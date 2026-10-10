
import java.util.Scanner;

class Node {
    String name;
    Node next;

    Node(String name) {
        this.name = name;
        this.next = null;
    }
}

public class RoundRobinGameTurns {
    Node tail = null;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    void printTurns(int turns) {
        if (tail == null) {
            return;
        }

        Node temp = tail.next;

        for (int i = 0; i < turns; i++) {
            System.out.print(temp.name + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobinGameTurns game = new RoundRobinGameTurns();

        System.out.print("Enter number of players: ");
        int n = sc.nextInt();

        System.out.println("Enter player names:");
        for (int i = 0; i < n; i++) {
            game.addLast(sc.next());
        }

        System.out.print("Enter number of turns: ");
        int turns = sc.nextInt();

        game.printTurns(turns);
        sc.close();
    }
}
