import java.io.FileNotFoundException;
import java.io.IOException;

public class Proj1 {
    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.err.println("Usage: java dog <filename>");
            System.exit(0);
        }
        new Parser(args[0]);


    }
}

/*
I used the dog_breeds.csv file and I removed the columns that contained fur color, character traits, and common health problems
I did this because these files had a variety of different string lengths seperated by commas
 */

//instead of parts toward BST, run commands