import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


class test{
    public static void main(String[] args) throws NoCmdLineArgException{
        /**
         * TO BE DELETED: for the sake of understanding
         * 
         * This code checks if any command line arguments were passed to the program. If args.length == 0, it means no command line arguments were passed, and the program should terminate with a message asking the user to provide a filename as an argument.
         * 
         * The return statement inside the if block ensures that the program terminates immediately if no filename was provided, rather than continuing to execute and causing errors because the necessary filename parameter is missing.
         * 
         * If a filename argument is provided, the program retrieves it from args[0] and assigns it to the filename variable. This allows the program to pass the filename to the fileWriting and fileReading methods to perform file I/O operations on the specified file.
         */
        if (args.length == 0) {
            throw new NoCmdLineArgException("** Please provide a filename as an argument.");
        }
        String filename = args[0]+".txt";
        
        // WriterClass.fileWriting(filename, "Ceci est une premiere ligne ajoutee");
        // WriterClass.fileWriting(filename, "Ceci est une seconde ligne ajoutee");

        // Print {filename}.txt -> OK
        TxtReader fileReader = new TxtReader();
        fileReader.readNow(filename);
        System.out.println("--------------------------------");
        
        // Get txtArray -> OK
        TxtReader fileReader2 = new TxtReader();
        ArrayList<String> txtArray = fileReader2.getNow(filename);
        for (int i = 0; i < txtArray.size(); i++) {
            System.out.println(txtArray.get(i));
        }
        System.out.println("--------------------------------");

        // Print first line
        FirstLineReader flineReader = new FirstLineReader();
        flineReader.readNow(filename);
        System.out.println("--------------------------------");

    }
}

/*----------------------------------
* EXCEPTION CLASSES
------------------------------------*/
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
* READER CLASSES AND METHODS 
------------------------------------*/
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

// class WriterClass{
//     public static void fileWriting(String filename, String textAdded) {
        
//         try {
//             // Check if file already exists
//             File file = new File(filename);
//             if (!file.exists()) {
//                 throw new FileNotFoundException();
//             }

//             // Creation of a fileWriter to write in the {filename}.txt
//             FileWriter fileWriter = new FileWriter(filename, true);
            
//             // Creation of bufferedWriter qui utilise le fileWriter
//             BufferedWriter writer = new BufferedWriter (fileWriter);
            
//             // ajout d’un texte à notre fichier
//             writer.write(textAdded);
//             writer.newLine();
            
//             writer.close();
//         }
//         // filename.txt not found exception
//         catch (FileNotFoundException e1) {
//             System.out.println("** FileNotFoundException : " + e1.getMessage() + ". Please create {filename}.txt manually beforehand.");
//         }
//         // Other exception
//         catch (IOException e2){
//             System.out.println("** Thrown Exception : " + e2.getMessage());
//         }
//     }

//     public static void editFirstLine(String filename){
//         // Get firstLine
//         String line = ReaderClass.firstLine(filename);

//         // Change nb of nodes (+= 2)
//         char firstChar = line.charAt(0);
//         int firstCharInt = Character.getNumericValue(firstChar);
//         firstCharInt += 2;
//         char newFirstChar = Character.forDigit(firstCharInt, 10);
//         line = newFirstChar + line.substring(1);

//         // Put back line as first in filename.txt
//         try {
//             // Check if file already exists
//             File tempFile = new File("temp.txt");
//             if (!tempFile.exists()) {
//                 throw new FileNotFoundException();
//             }

//             // Creation of a fileWriter to write in the {filename}.txt
//             FileWriter fileWriter = new FileWriter("temp.txt", true);
            
//             // Creation of bufferedWriter qui utilise le fileWriter
//             BufferedWriter writer = new BufferedWriter (fileWriter);
            
//             // ajout d’un texte à notre fichier
//             writer.write(textAdded);
//             writer.newLine();
            
//             writer.close();
//         }
//         // filename.txt not found exception
//         catch (FileNotFoundException e1) {
//             System.out.println("** FileNotFoundException : " + e1.getMessage() + ". Please create {filename}.txt manually beforehand.");
//         }
//         // Other exception
//         catch (IOException e2){
//             System.out.println("** Thrown Exception : " + e2.getMessage());
//         }

//         // Creation of a fileWriter to write in the {filename}.txt
//         FileWriter fileWriter = new FileWriter("temp.txt", true);
            
//         // Creation of bufferedWriter qui utilise le fileWriter
//         BufferedWriter writer = new BufferedWriter (fileWriter);
//     }

// }
