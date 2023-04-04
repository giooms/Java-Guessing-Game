import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


class Game{
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
        
        WriterClass.fileWriting(filename, "Ceci est une premiere ligne ajoutee");
        WriterClass.fileWriting(filename, "Ceci est une seconde ligne ajoutee");
        ReaderClass.fileReading(filename);
    }
}

class Question{

}

class EcuriesF1{

}

class NoCmdLineArgException extends Exception {
    public NoCmdLineArgException() { super(); } 
    public NoCmdLineArgException(String s) { super(s); }
}

class ReaderClass{
    public static void fileReading(String filename) {

        try {
            // Creation of a fileReader to read the {filename}.txt
            FileReader fileReader = new FileReader(filename);
        
            // Using the file via reader variable
            BufferedReader reader = new BufferedReader (fileReader);
            
            String line = reader.readLine();
            
            while(line != null) {
                // print line then read next
                System.out.println(line);
                line = reader.readLine();
            }

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
}

class WriterClass{
    public static void fileWriting(String filename, String textAdded) {
        
        try {
            // Check if file already exists
            File file = new File(filename);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }

            // Creation of a fileWriter to write in the {filename}.txt
            FileWriter fileWriter = new FileWriter(filename, true);
            
            // Creation of bufferedWriter qui utilise le fileWriter
            BufferedWriter writer = new BufferedWriter (fileWriter);
            
            // ajout d’un texte à notre fichier
            writer.write(textAdded);
            writer.newLine();
            
            writer.close();
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
}
