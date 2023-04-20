/**
 * The below Exception class is part of the classes for specified exceptions handling that we created. 
 * 
 * The classes and their tasks are :
 * @class NoCmdLineArgException (this one) : This exception is thrown when no command-line arguments are passed to the program or when there is an extension error.
 * @class IndexNotFoundException : This exception is thrown when an index is not found in a list or array.
 * @class InvalidMethodException : This exception is thrown when an invalid method is called or used in the program.
 * 
 * The methods in this class consists of two constructors and the
 * @method getFileName : Checking for wrong entry in terminal and returns the file name
 */

package be.uliege.oop.guessgame;

public class NoCmdLineArgException extends Exception {
    public NoCmdLineArgException() { super(); } 
    public NoCmdLineArgException(String s) { super(s); }

    public static String getFileName(String[] args){
        // Handles exceptions for wrong application launch
        try {
            if (args.length == 0) { 
                throw new NoCmdLineArgException(); 
            }

            String filename = args[0];

            String extension = filename.substring(filename.lastIndexOf(".") + 1);
            if (!(extension.equals("txt") || extension.equals("csv"))){
                throw new NoCmdLineArgException();
            }
    
            return filename;
            
        }
        catch (NoCmdLineArgException e) {
            System.out.println("** Please provide a valid filename as an argument. You should use a .txt or .csv.");
         }
        
        return null;
    }
}

