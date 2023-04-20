/**
 * The ReaderClass is a class that provides methods for reading and returning data from a text file.
 * 
 * The class includes the following methods:
 * @method readNow: Reads the content of the text file and calls the abstract extraRead method for further processing
 * @method extraRead: Abstract method to be implemented by the subclass, allowing for further processing of the file contents after reading
 *
 * The implemented subclasses of the upper class ReaderClass are :
 * @subclass TxtReader : Reads prints and can return the content of a text file
 * @subclass FirstLineReader (this one): Reads and prints the first line of a text file
 */

package be.uliege.oop.guessgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


public class FirstLineReader extends ReaderClass {

    public FirstLineReader(){
        super();
    }

    @Override
    public void extraRead(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        String[] parts = firstLine.split("\\s+", 2);
        System.out.println(parts[1] + " and then press <return>");
    }

    @Override
    public void extraGet(BufferedReader reader, ArrayList<String> txtArray) {
        try{
            throw new InvalidMethodException("Cannot use getNow() method for FirstLineReader type variable "+txtArray);
        }
        catch (InvalidMethodException e){
            e.printStackTrace();
        }
    }
}