/**
 * The abstract StringToNode class provides methods for converting a text file containing information about a decision tree into a list of Node objects.
 * 
 * The class includes the following methods:
 * @method infoLineToNode: Constructs a new Node object representing the information line from the given line.
 * @method questionsToNode: Constructs a new Node object representing a question node from the given line.
 * @method answersToNode: Constructs a new Node object representing an asnwer node from the given line.
 * @method convert: Convert the ArrayList of Strings received to an ArrayList of Node objects representing the decision tree
 */

package be.uliege.oop.guessgame;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StringToNode {

    public static Node infoLineToNode(String line){
        try{
            String[] parts = line.split("\\s+", 2);     // split on whitespace, max 2 parts
            int nb_node = Integer.parseInt(parts[0]);           
            String data = parts[1];
    
            Node newNode = new Node(Node.Type.DIGIT, nb_node, data);        // Here type = #
    
            return newNode;
        }
        catch (NumberFormatException e) {
            // Handle exception when parts[0] is not a valid integer
            System.out.println("NumberFormatException : "+e.getMessage()+ "\nWe expect this format for the first line: |int data|");
            System.exit(1);
            return null;
        }
    }

    public static Node questionsToNode(String line, int i){
        try {
            String[] parts = line.split("\\s+", 4);     // split on whitespace, max 4 parts
            int n1 = Integer.parseInt(parts[1]);        // second part is n1
            int n0 = Integer.parseInt(parts[2]);        // third part is n0                    
            String data = parts[3];
    
            Node newNode = new Node(Node.Type.QUESTION, n1, n0, data); 
    
            return newNode;
        }
        catch (NumberFormatException e) {
            // Handle exception when parts[1] or parts[2] is not a valid integer
            System.out.println("NumberFormatException : "+e.getMessage()+ "\nWe expect this format at line "+(i+1)+": |type n1 n0 data|");
            System.exit(1);
            return null;
        }
    }

    public static Node answersToNode(String line, int i){
        try{
            String[] parts = line.split("\\s+", 2);     // split on whitespace, max 2 parts               
            String data = parts[1];

            Node newNode = new Node(Node.Type.ANSWER, data);

            return newNode;
        }
        catch (NumberFormatException e){
            System.out.println("NumberFormatException: "+e.getMessage()+"\nWe expect this format at line "+(i+1)+": |type data|");
            System.exit(1);
            return null;
        }
    }

    public static ArrayList<Node> convert(ArrayList<String> txtArray){
        ArrayList<Node> nodeArray = new ArrayList<Node>();

        try {
            if (!txtArray.isEmpty()) {
                for (int i = 0; i < txtArray.size(); i++) {
                    String line = txtArray.get(i);
                    // Check if txt starts with "#", "?" or "="
                    if (Character.isDigit(line.charAt(0))) {
                        Node newNode = infoLineToNode(line);
        
                        nodeArray.add(newNode);
                        continue;
                    }
                    else if (line.startsWith("?")){
                        Node newNode = questionsToNode(line, i);
        
                        nodeArray.add(newNode);
                        continue;
                    }
                    else if (line.startsWith("=")){
                        Node newNode = answersToNode(line, i);
        
                        nodeArray.add(newNode);
                        continue;
                    }
                    else{
                        throw new NumberFormatException();
                    }
                }
                return nodeArray; 
            }
            else if (txtArray.isEmpty()) {
                FileNotFoundException e1 = new FileNotFoundException();
                throw e1;
            }
        }
        catch (FileNotFoundException e1) {
            System.out.println("** FileNotFoundException : " + e1.getMessage() + ". Please populate {filename}.txt manually beforehand.");
        }
        catch (NumberFormatException e2){
            System.out.println("** The structure of one of the lines in {filename}.txt/.csv is incorrect. \nExcept the first one, they should all start with -?- or -=-.");
        }

        return nodeArray;
    }

}
