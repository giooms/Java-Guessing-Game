/**
 * Game.
 * 
 * Main class of the guessing game project.
 * 
 * @Course : INFO0062-1 - Object Oriented Programming
 * @Instructor : Pr. Bernard Boigelot
 * --------------------------------------------------------
 * @author Gilles Ooms (s192136) & Martin Dengis (s193348)
 * @AcademicYear 2022-2023
 */

import be.uliege.oop.guessgame.*;

import java.util.ArrayList;
import java.util.Scanner;


class Game{
    public static void main(String[] args){

        // Checking for wrong entry in terminal and game initialization
        String filename = NoCmdLineArgException.getFileName(args);
        ArrayList<Node> nodeArray = GameRefresh.init(filename);
        
        // Launch of the game
            // Variables initialisation and welcome message
        Scanner scanner = GameRefresh.start();
        FirstLineReader flineReader = new FirstLineReader();
        flineReader.readNow(filename);
        
            // Prompt the user to enter a F1 team and launch the game
        nodeArray = GameRefresh.gameLoop(nodeArray, scanner);

        // End of the game and updating tree
        String[] txtArray_update = new String[nodeArray.size()];
        txtArray_update = GameRefresh.updateTree(nodeArray); 

        WriterClass.writeLinesToFile(txtArray_update, filename);

        return;
    }
}
