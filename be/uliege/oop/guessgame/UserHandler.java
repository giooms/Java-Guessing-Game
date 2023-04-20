/**
 * The UserHandler class provides methods to handle user input and update the game tree 
 * 
 * The class includes the following methods:
 * @method userInputHandler: Handles user inputs to return a valid user input as either "y" or "n".
 * @method typeQuestion: Handles the case where the program asks the user a question by (1) asking the question, (2) seeking the answer and (3) returning the updated index i of new node to handle.
 * @method typeAnswer: Handles the case where the program gives the user an answer by (1) checking if given answer is correct, (2) if correct : end game and (3) if incorrect : prompt the user for new Answer and Question to add to the game tree.
 */

package be.uliege.oop.guessgame;

import java.util.Scanner;
import java.util.ArrayList;

public class UserHandler {
    public static String userInputHandler(Scanner scanner){
        String userInput = scanner.nextLine().toLowerCase();
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

        if (userInput.equals("y")){ System.out.println("I have won!\n<end of the game>"); }
        else if (userInput.equals("n")){
            System.out.println("I am unable to guess; you have won!\nWhat did you have in mind?");
            
            String newAnswer = scanner.nextLine();
            
            while(true){
                if(!newAnswer.equalsIgnoreCase(userAnswer)){
                    System.out.println("Check for a typo or I'll think you are cheating, please write correctly the idea you had in mind :");
                    newAnswer =scanner.nextLine();
                }
                else { break; }
            }
            
            System.out.println("What question could I ask to distinguish "+newAnswer+" from "+(nodeArray.get(i)).data+"?");
            String newQuestion = scanner.nextLine();

            // We check if the string contains "?"
            if (newQuestion.contains("?")) {
                int qMarkIndex = newQuestion.indexOf("?");
                // Get back only substring without "?" and trim any leading/trailing whitespaces
                newQuestion = newQuestion.substring(0, qMarkIndex).trim();

                // Transform first character to upperCase if not already
                if (!Character.isUpperCase(newQuestion.charAt(0))){
                    newQuestion = Character.toUpperCase(newQuestion.charAt(0)) + newQuestion.substring(1);
                }
            }
            
            System.out.println("For "+newAnswer+", would you answer yes or no to this question (Y/N)?");
            userInput = UserHandler.userInputHandler(scanner);
            System.out.println("The game tree has been updated. You can now play with your new answer '"+newAnswer+"'.");
            
            Node newQNode = new Node(Node.Type.QUESTION, 0, 0, newQuestion);
            Node newANode = new Node(Node.Type.ANSWER, newAnswer);

            Node temp = nodeArray.get(i);
            nodeArray.set(i, newQNode);
            int size = nodeArray.size();

            if(userInput.equals("y")){
                nodeArray.add(newANode);
                nodeArray.add(temp);
            }
            else{
                nodeArray.add(temp);
                nodeArray.add(newANode);
            }
            Node update = nodeArray.get(i);
            update.n1 = size;
            update.n0 = size+1;
            nodeArray.set(i, update);

            size = nodeArray.size() - 1;
            update = nodeArray.get(0);
            update.nb_node = size;
            nodeArray.set(0, update);
        }

        return nodeArray;
    }
}
