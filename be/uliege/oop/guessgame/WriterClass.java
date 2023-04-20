/**
 * The WriterClass is a class that provides a static method for writing an array of Strings to a file.
 * 
 * The class includes the following method:
 * @method writeLinesToFile: Writes each element of a String array to a file with the specified name using a PrintWriter object. If an IOException occurs during the write process, it is caught and printed to the console.
 */

package be.uliege.oop.guessgame;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriterClass{

    public static void writeLinesToFile(String[] txtArray, String filename) {
        try {
            // Create a new PrintWriter object to write to the file
            PrintWriter pw = new PrintWriter(new FileWriter(new File(filename)));

            // Loop through the lines and write each one to the file
            for (String line : txtArray) {
                pw.println(line);
            }

            // Close the PrintWriter to flush the output and release resources
            pw.close();

        } catch (IOException e) {
            // Handle any exceptions that occur during the write process
            e.printStackTrace();
        }
    }
}