import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Iterator;


public class Parser {

    // Create a BST tree of your class type (Note: Replace "Object" with your class type) so String?
    private BST<DogData> mybst = new BST<>();

    public Parser(String filename) throws IOException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws IOException {
        File file = new File("./output.txt");
        if (file.delete()) {
            file.createNewFile();
        } else {
            throw new FileNotFoundException();
        }
        //call operate_BST method;
        Scanner scnr = new Scanner(new File("./src/dogbreeds.csv"));
        Scanner inputscnr = new Scanner(input);
        //remove header in file
        if (scnr.hasNext()) {
            scnr.nextLine();
        }
        while (scnr.hasNext()) {
            String line = scnr.nextLine();
            String[] parts = line.split(","); //split the string into multiple parts
            //Check if the dogs match
            //if (parts[0].equals("Breed")) {
                //Create new DogData object
                DogData data = new DogData(
                        parts[0], //breed
                        parts[1], //originCountry
                        parts[2], //furColor
                        parts[3], //height
                        parts[4], //eyeColor
                        parts[5] //longevity
                );
                mybst.insert(data); //add the data onto the ArrayList

            //}
        }
        while(inputscnr.hasNext()) {
            String line = inputscnr.nextLine().trim().replaceAll("\\s+"," ");
            if (line.length() == 0){
                continue;
            }
            String[] command = line.split(",");
            operate_BST(command);
        }
    }


    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        System.out.println(command[0]);
        switch (command[0]) {
            // add your cases here
            case "insert" -> {
                String breed = command[1];
                String country = command[2];
                String color = command[3];
                String height = command[4];
                String weight = command[5];
                String eyes = command[6];
                DogData temp = new DogData(breed, country, color, height, weight, eyes );
                mybst.insert(temp);
                writeToFile("inserted " + command[1], "./output.txt");
            }
            case "remove" -> {
                String breed = command[1];
                String country = command[2];
                String color = command[3];
                String height = command[4];
                String weight = command[5];
                String eyes = command[6];
                DogData temp = new DogData(breed, country, color, height, weight, eyes );
                DogData removedDog = mybst.remove(temp);
                if (removedDog == null) {
                    writeToFile("remove failed", "./output.txt");
                } else {
                    writeToFile("removed " + command[1], "./output.txt");
                }
            }
            case "search" -> {
                String breed = command[1];
                String country = command[2];
                String color = command[3];
                String height = command[4];
                String weight = command[5];
                String eyes = command[6];
                DogData temp = new DogData(breed, country, color, height, weight, eyes );
                if (mybst.search(temp) == null) {
                    writeToFile("search failed", "./output.txt");
                } else {
                    writeToFile("found " + command[1], "./output.txt");
                }
            }
            case "print" -> {
                Iterator<Node<DogData>> iterator = mybst.iterator();
                boolean first = true;
                String temp = "";
                while (iterator.hasNext()) {
                    if (!first) {
                        temp += " ";
                        first = false;
                    }
                    temp += iterator.next().value() + "\n";
                }
                writeToFile(temp, "./output.txt"); // call writeToFile
            }

            // default case for Invalid Command
            default -> writeToFile("Invalid Command", "./output.txt");
        }
    }


    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
            boolean emptyFile = new File(filePath).length() == 0;
            if (!emptyFile) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
