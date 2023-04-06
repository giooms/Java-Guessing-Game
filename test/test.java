import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


class test{
    public static void main(String[] args) {

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

    public static void editFirstLine(String filename){
        // Get firstLine
        String line = ReaderClass.firstLine(filename);

        // Change nb of nodes (+= 2)
        char firstChar = line.charAt(0);
        int firstCharInt = Character.getNumericValue(firstChar);
        firstCharInt += 2;
        char newFirstChar = Character.forDigit(firstCharInt, 10);
        line = newFirstChar + line.substring(1);

        // Put back line as first in filename.txt
        try {
            // Check if file already exists
            File tempFile = new File("temp.txt");
            if (!tempFile.exists()) {
                throw new FileNotFoundException();
            }

            // Creation of a fileWriter to write in the {filename}.txt
            FileWriter fileWriter = new FileWriter("temp.txt", true);
            
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

        // Creation of a fileWriter to write in the {filename}.txt
        FileWriter fileWriter = new FileWriter("temp.txt", true);
            
        // Creation of bufferedWriter qui utilise le fileWriter
        BufferedWriter writer = new BufferedWriter (fileWriter);
    }

}
