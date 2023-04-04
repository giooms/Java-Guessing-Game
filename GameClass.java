import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    private Node root; // The root node of the game tree
    private Scanner scanner;

    public Game(File file) throws FileNotFoundException {
        scanner = new Scanner(System.in);
        root = readTreeFromFile(file);
    }

    public void start() {
        System.out.println("Welcome to the game! Think of a Formula 1 Team and I will try to guess it :).");
        Node current = root;
        while (current.isQuestion()) {
            System.out.println(this.data + " (Y/N) ?");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                current = current.yes;
            } else {
                current = current.no;
            }
        }
        // ... 
        System.out.println("Is the team you imagined " + this.data + "?");
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("yes") || answer.equals("y")) {
            System.out.println("I win!");
        } else {
            System.out.println("I give up.");
            //Coder la suite pour améliorer la liste 
        }
    }

    private Node readTreeFromFile(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        Node root = new Node();
        Node current = root;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Node node = new Node();
            node.setData(line);
            if (current.isQuestion()) {
                current.yes = node;
                current.no = new Node();
                current.no.setN0(current.n1 + 1); // Point to next line after right child node
                current.no.setN1(current.n1 + 2);
                current = current.no;
            } else {
                current.no = node;
                current = root;
            }
        }
        fileScanner.close();
        return root;
    }

}