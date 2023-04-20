/**
 * The below Exception class is part of the classes for specified exceptions handling that we created. 
 * 
 * The classes and their tasks are :
 * @class NoCmdLineArgException : This exception is thrown when no command-line arguments are passed to the program or when there is an extension error.
 * @class IndexNotFoundException : This exception is thrown when an index is not found in a list or array.
 * @class InvalidMethodException (this one) : This exception is thrown when an invalid method is called or used in the program.
 */

package be.uliege.oop.guessgame;

public class InvalidMethodException extends Exception {
    public InvalidMethodException() { super(); } 
    public InvalidMethodException(String s) { super(s); }
}