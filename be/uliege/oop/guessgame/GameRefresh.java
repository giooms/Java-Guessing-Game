/**
 * The GameRefresh class provides methods for refreshing the game.
 * 
 * The class includes the following methods:
 * @method init : Initialize the game by converting the content file to an ArrayList of Node. 
 * @method start : Welcomes the player and returns a scanner for user's input.
 * @method gameLoop : Starts the game and prompt the user for inputs for the game to continue.
 * @method updateTree : Updates the game tree by returning a new ArrayList of Node representing the tree.
 */

package be.uliege.oop.guessgame;

import java.util.ArrayList;
import java.util.Scanner;

public class GameRefresh {

    public static ArrayList<Node> init(String filename){
        // Get ArrayList<String> txtArray containing the file.txt
        TxtReader fileReader = new TxtReader();
        ArrayList<String> txtArray = fileReader.getNow(filename);

            // Convert ArrayList<String> txtArray to ArrayList<Node> nodeArray
        ArrayList<Node> nodeArray = new ArrayList<Node>();
        nodeArray = StringToNode.convert(txtArray);
        if (nodeArray.isEmpty()){ return null; }

        return nodeArray;
    }

    public static Scanner start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game!");
        return scanner;
    }

    public static ArrayList<Node> gameLoop(ArrayList<Node> nodeArray, Scanner scanner){

        String userInput = "y";
        int i = 1;
        String userAnswer = scanner.nextLine();
        
        while (i < nodeArray.size()){
            if ((nodeArray.get(i)).type == Node.Type.QUESTION) {
                // Asks the user a question and prompt the user for an answer
                i = UserHandler.typeQuestion(nodeArray, i, userInput, scanner);
                continue;  
            }
            else if ((nodeArray.get(i)).type == Node.Type.ANSWER){
                // Gives the user an answer and checks if correct or not 
                nodeArray = UserHandler.typeAnswer(nodeArray, i, userInput, userAnswer, scanner);
                break;
            }
        }

        return nodeArray;
    }

    public static String[] updateTree(ArrayList<Node> nodeArray) {
        String[] stringList = new String[nodeArray.size()];

        // loop through each node in the nodeList
        for (int i = 0; i < nodeArray.size(); i++) {
            Node currentNode = nodeArray.get(i);
            String currentNodeString = "";
            
            if (currentNode.type == Node.Type.QUESTION) {
                currentNodeString += "? " + currentNode.n1 + " " + currentNode.n0 + " ";
            } else if (currentNode.type == Node.Type.ANSWER) {
                currentNodeString += "= ";
            } else if (currentNode.type == Node.Type.DIGIT) {
                String nb_node_str = Integer.toString(currentNode.nb_node);
                currentNodeString += nb_node_str + " ";
            }
            currentNodeString += currentNode.data;
            
            stringList[i] = currentNodeString;
        }      
        
        return stringList;
    }

}

