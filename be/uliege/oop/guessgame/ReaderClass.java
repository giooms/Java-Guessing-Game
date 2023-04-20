/**
 * The ReaderClass is a class that provides methods for reading and returning data from a text file.
 * 
 * The class includes the following methods:
 * @method readNow: Reads the content of the text file and calls the abstract extraRead method for further processing
 * @method extraRead: Abstract method to be implemented by the subclass, allowing for further processing of the file contents after reading
 *
 * The implemented subclasses of the upper class ReaderClass are :
 * @subclass TxtReader : Reads prints and can return the content of a text file
 * @subclass FirstLineReader : Reads and prints the first line of a text file
 */

package be.uliege.oop.guessgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderClass{
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

    public void extraRead(BufferedReader reader) throws IOException{
        return;
    }
    public void extraGet(BufferedReader reader, ArrayList<String> txtArray) throws IOException{
        return;
    }
}
