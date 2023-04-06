import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

// TO BE DONE : Node class, enum Type
class Game{
    public static void main(String[] args) throws NoCmdLineArgException{
    // Handles exceptions for wrong application launch
        if (args.length == 0) { throw new NoCmdLineArgException("** Please provide a filename as an argument."); }
        String filename = args[0]+".txt";

    // Preparing the game
        // Get ArrayList<String> txtArray containing the file.txt
        TxtReader fileReader2 = new TxtReader();
        ArrayList<String> txtArray = fileReader2.getNow(filename);

        // Convert txtArray to ArrayList<Node> nodeArray
        ArrayList<Node> nodeArray = new ArrayList<Node>();
        try{
            if (!txtArray.isEmpty()) {
                nodeArray = StringToNode.txtArrayListToNode(txtArray);
                // At this point, ArrayList<String> txtArray has been converted into ArrayList<Node> nodeArray
                // Each node with its attributes .type and .data (minimum) (+ .n1 and .n0 eventually)
            }
            else if (txtArray.isEmpty()) {
                FileNotFoundException e1 = new FileNotFoundException();
                throw e1;
            }
        }
        catch (FileNotFoundException e1) {
            System.out.println("** FileNotFoundException : " + e1.getMessage() + ". Please create {filename}.txt manually beforehand.");
        }
        
    // Launch of the game
        // Variables initialisation and welcome message
        Scanner scanner = GameRefresh.start();
        String userAnswer, userInput;
        int i = 1;
        
        // Prompt the user to enter a F1 team
        FirstLineReader flineReader = new FirstLineReader();
        flineReader.readNow(filename);
        userAnswer = scanner.next();

        // Beginning of the guessing game
        while (i < nodeArray.size()){
            if ((nodeArray.get(i)).type == QUESTION) {
                // Asks the user a question and prompt the user for an answer
                i = UserHandler.typeQuestion(nodeArray, i, userInput, scanner);
                continue;  
            }
            else if ((nodeArray.get(i)).type == ANSWER){
                // Gives the user an answer and checks if correct or not 
                nodeArray = UserHandler.typeAnswer(nodeArray, i, userInput, userAnswer, scanner);
                break;
            }
        }

        // Convert back the nodeArray to txtArray
        // Update the file.txt based on the new txtArray
        // ... TO BE DONE
        return;

    }
}



/*----------------------------------
* EXCEPTION CLASSES -> TO DO: documentation
* -------------------------------- */
class NoCmdLineArgException extends Exception {
    public NoCmdLineArgException() { super(); } 
    public NoCmdLineArgException(String s) { super(s); }
}

class IndexNotFoundException extends Exception {
    public IndexNotFoundException() { super(); } 
    public IndexNotFoundException(String s) { super(s); }
}

class InvalidMethodException extends Exception {
    public InvalidMethodException() { super(); } 
    public InvalidMethodException(String s) { super(s); }
}



/*----------------------------------
* READER CLASSES -> TO DO: documentation
* -------------------------------- */
abstract class ReaderClass{
    public void readNow(String filename) {

        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader (fileReader);
            
            // call the abstract extra method
            extraRead(reader);

            reader.close();
        }
        // filename.txt not found exception
        catch (FileNotFoundException e1) {
            System.out.println("** FileNotFoundException : " + e1.getMessage() + ". Please create {filename}.txt manually beforehand.");
        }
        // Other exception
        catch (IOException e2){
            System.out.println("** Thrown Exception : " + e2.getMessage());
        }    
    }

    public ArrayList<String> getNow(String filename) {
        ArrayList<String> txtArray = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader (fileReader);

            // call the abstract extra method
            extraGet(reader, txtArray);

            reader.close();
        } 
        // filename.txt not found exception
        catch (FileNotFoundException e1) {
            System.out.println("** FileNotFoundException : " + e1.getMessage() + ". Please create {filename}.txt manually beforehand.");
        }
        // Other exception
        catch (IOException e2){
            System.out.println("** Thrown Exception : " + e2.getMessage());
        }  
        return txtArray;
    }

    protected abstract void extraRead(BufferedReader reader) throws IOException ;
    protected abstract void extraGet(BufferedReader reader, ArrayList<String> txtArray) throws IOException ;
}

    // Class to read the whole file -> OK
class TxtReader extends ReaderClass {

    @Override
    protected void extraRead(BufferedReader reader) throws IOException {
        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Override
    protected void extraGet(BufferedReader reader, ArrayList<String> txtArray) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            txtArray.add(line);
        }
    }
}

    //  Class to read or get the first line
class FirstLineReader extends ReaderClass {

    @Override
    protected void extraRead(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        firstLine = firstLine.substring(2);
        System.out.println(firstLine);
    }

    @Override
    protected void extraGet(BufferedReader reader, ArrayList<String> txtArray) {
        try {
            throw new InvalidMethodException("Cannot use getNow() method for FirstLineReader type variable "+txtArray);
        }
        catch (InvalidMethodException e) {
            e.getMessage();
        }
    }
}



/**
 * The StringToNode class provides methods for converting a text file containing information about a decision tree into a list of Node objects.
 * 
 * The class includes the following methods:
 * @method infoLineToNode: Parses a line of text representing a node with no children and returns a new Node object
 * @method questionsToNode: Parses a line of text representing a node with two children (yes and no) and returns a new Node object
 * @method answersToNode: Parses a line of text representing a leaf node and returns a new Node object
 * @method txtArrayListToNode: Takes an ArrayList of Strings as input and returns an ArrayList of Node objects representing the decision tree
 */
class StringToNode {
    /**
    * Convert a string representation of a node to an actual Node object.
    *
    * @param linethe string to convert
    * @return a Node object
    */
    public static Node infoLineToNode(String line){
        try{
            String[] parts = line.split("\\s+", 2);     // split on whitespace, max 2 parts
            int type = Integer.parseInt(parts[0]);           
            String data = parts[1];
    
            Node newNode = new Node(type, data);        // Here type = #
    
            return newNode;
        }
        catch (NumberFormatException e) {
            // Handle exception when parts[0] is not a valid integer
            System.out.println("NumberFormatException : "+e.getMessage());
        }
    }

    /**
     * Constructs a new Node object representing a question node from the given line.
     * 
     * @param line a string containing information about the question node in the following format: "type n1 n0 data".
     * @return a new Node object representing a question node with the specified information.
     * @throws NumberFormatException if either n1 or n0 cannot be parsed as an integer.
     */
    public static Node questionsToNode(String line){
        try {
            String[] parts = line.split("\\s+", 4);     // split on whitespace, max 4 parts
            String type = parts[0];                     // first part is the question mark
            int n1 = Integer.parseInt(parts[1]);        // second part is n1
            int n0 = Integer.parseInt(parts[2]);        // third part is n0                    
            String data = parts[3];
    
            Node newNode = new Node(type, n1, n0, data); 
    
            return newNode;
        }
        catch (NumberFormatException e) {
            // Handle exception when parts[1] or parts[2] is not a valid integer
            System.out.println("NumberFormatException : "+e.getMessage());
        }
    }

    /**
    * Converts a string representing an answer node to an actual Node object.
    *
    * @param line the string to be parsed, containing the node type and data
    * @return a Node object representing the answer node
    */
    public static Node answersToNode(String line){
        String[] parts = line.split("\\s+", 2);     // split on whitespace, max 2 parts
        String type = parts[0];                
        String data = parts[1];

        Node newNode = new Node(type, data);

        return newNode;
    }

    /**
    * Converts an ArrayList of Strings into an ArrayList of Nodes by parsing each String and creating a corresponding Node object.
    *
    * @param txtArray ArrayList of Strings to be converted into Nodes
    * @return ArrayList of Nodes representing the parsed data from txtArray
    */
    public static ArrayList<Node> txtArrayListToNode(ArrayList<String> txtArray){
        ArrayList<Node> nodeArray = new ArrayList<Node>();
        for (int i = 0; i < txtArray.size(); i++) {
            String line = txtArray.get(i);
            // Check if txt starts with "#", "?" or "="
            if (Character.isDigit(line.charAt(0))) {
                Node newNode = new Node();
                newNode = infoLineToNode(line);

                nodeArray.add(newNode);
                continue;
            }
            else if (line.startsWith("?")){
                Node newNode = new Node();
                newNode = questionsToNode(line);

                nodeArray.add(newNode);
                continue;
            }
            else if (line.startsWith("=")){
                Node newNode = new Node();
                newNode = answersToNode(line);

                nodeArray.add(newNode);
                continue;
            }
        }
        return nodeArray;
    }

}



/**
 * The GameRefresh class provides methods for refreshing the game.
 * 
 * The class includes the following methods:
 * @method start : Welcomes the player and returns a scanner for user's input.
 * @method updateTree : Updates the game tree by returning a new nodeArray.
 */
class GameRefresh {
    /**
     * Starts the game.
     * 
     * @return Scanner object to handle user input
     */
    public static Scanner start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game! Think of a Formula 1 Team and I will try to guess it :).");
        return scanner;
    }

    /**
    * Updates the decision tree represented by the given ArrayList of nodes with new question and answer nodes at the specified index i.
    *
    * @param nodeArray the ArrayList of nodes representing the decision tree to be updated
    * @param newQNode the new question node to be added at index i
    * @param newANode the new answer node to be added at index i+1
    * @param i the index at which the new question and answer nodes are to be added
    *
    * @return the updated ArrayList of nodes representing the decision tree
    */
    public static ArrayList<Node> updateTree(ArrayList<Node> nodeArray, Node newQNode, Node newANode, int i, String userInput) {
        int currIndex;

        // Initialize new decision tree auxNodeArray
        ArrayList<Node> auxNodeArray = nodeArray;
        auxNodeArray.add(i, newQNode);
        auxNodeArray.add(i+1, newANode);

        for(currIndex = 0; currIndex < auxNodeArray.size(); currIndex++){

            // Found the new Question -> Set correct n1 and n0
            if (currIndex == i){
                if (userInput.equals("y")) { 
                    (auxNodeArray.get(currIndex)).n1 = currIndex + 1; 
                    (auxNodeArray.get(currIndex)).n0 = i;
                }
                else if (userInput.equals("n")) { 
                    (auxNodeArray.get(currIndex)).n1 = i; 
                    (auxNodeArray.get(currIndex)).n0 = currIndex +1 ;
                }
            }

            // Found a question at index currIndex that is not new Question <=> already existing Question
            else if((auxNodeArray.get(currIndex)).type == QUESTION && (auxNodeArray.get(currIndex)).data != (auxNodeArray.get(i)).data){
                int j = 0, a = 0, b = 0;
                int auxN1, auxN0;
                // Search for the index j of that question in nodeArray
                while(auxNodeArray.get(currIndex) != nodeArray.get(j)) { j++; }
                // Once found, get back original n1 and n0
                auxN1 = nodeArray.get(j).n1;
                auxN0 = nodeArray.get(j).n0;
                // And get back the nodes at those indexes (n1 and n0)
                Node temp1 = new Node();
                Node temp2 = new Node();
                temp1 = nodeArray.get(auxN1);
                temp2 = nodeArray.get(auxN0);
                // Loop through auxNodeArray until identical nodes found : get back their index (=a and =b)
                while (auxNodeArray.get(a) != temp1){ a++; }
                while (auxNodeArray.get(b) != temp2){ b++; }
                // Set (n1;n0) from currIndex question as those new indexes a and b
                auxNodeArray.get(currIndex).n1 = a;
                auxNodeArray.get(currIndex).n0 = b;
            }    
        }
        return auxNodeArray;
    }

}



/*----------------------------------
* USERHANDLER CLASS -> TO DO: documentation
* -------------------------------- */
class UserHandler {
    public static String userInputHandler(Scanner scanner){
        String userInput = scanner.next();
        if (userInput.length() > 1){
            userInput = String.valueOf(userInput.charAt(0)).toLowerCase();
        }
        if (!userInput.equals("y") && !userInput.equals("n")){
            System.out.println("Invalid input. Try again ! (Y/N)");
            userInput = userInputHandler(scanner);
        }
        return userInput;
    }

    public static int typeQuestion(ArrayList<Node> nodeArray, int i, String userInput, Scanner scanner){
        System.out.println((nodeArray.get(i)).data+" ? (Y/N)");
        userInput = UserHandler.userInputHandler(scanner);
        
        if(userInput.equals("y")){ i = (nodeArray.get(i)).n1; }
        else if (userInput.equals("n")){ i = (nodeArray.get(i)).n0; }

        return i;
    }

    public static ArrayList<Node> typeAnswer(ArrayList<Node> nodeArray, int i, String userInput, String userAnswer, Scanner scanner){
        System.out.println("Is it "+(nodeArray.get(i)).data+" ? (Y/N)");
        userInput = UserHandler.userInputHandler(scanner);

        if (userInput.equals("y")){ System.out.println("I have won!"); }
        else if (userInput.equals("n")){
            System.out.println("I am unable to guess; you have won!\nWhat did you choose?");
            String newAnswer = scanner.next();
            
            System.out.println("What question could I ask to distinguish "+userAnswer+" from "+(nodeArray.get(i)).data+"?");
            String newQuestion = scanner.next();
            
            System.out.println("For "+userAnswer+", would you answer yes or no to this question (Y/N)?");
            userInput = UserHandler.userInputHandler(scanner);
            
            Node newQNode = new Node("?", 0, 0, newQuestion);
            Node newANode = new Node("=", newAnswer);

            /**
            * METHOD TO BE IMPLEMENTED -> Has been implemented but kept the description for the sake of understanding
            *
            * @param : nodeArray, newQNode, newANode, i, userInput
            * 
            * - Initialiser un { ArrayList<Node> auxNodeArray }
            * - Boucler à travers l'ancien nodeArray et recopier dans auxNodeArray jusqu'à index i-1 (set variable temp = i)
            * - A l'index (i) de auxNodeArray, insérer newQNode (avec n1 et n0 initialisé à 0), suivi de newANode (à (i+1)), puis recopier tous les anciens nodes dans auxNodeArray en bouclant dans nodeArray depuis la variable temp
            * - Boucler à nouveau en // dans les 2 array pour màj n1 et n0 de chaque question node dans auxNodeArray (utiliser des variables tampons probablement)
            * - Return auxNodeArray
            */
            nodeArray = GameRefresh.updateTree(nodeArray, newQNode, newANode, i, userInput); 
        }

        return nodeArray;
    }
}

