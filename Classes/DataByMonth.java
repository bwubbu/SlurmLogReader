package Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataByMonth { //A class to create a .txt file containing the month prompted by user.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the month that you wish to extract (in digits) : ");
        String month = sc.next();
        
        int month_int = Integer.parseInt(month); //convert string to int
        if(month_int < 6 || month_int > 12) {
            System.out.println("Enter a valid month between 06-12");
            return;
        }
        try {
            Scanner scan = new Scanner(new File("extracted_log"));
            PrintWriter writer = new PrintWriter("Extracted_Month_" + month + ".txt"); //Create a new file with the extracted month's data
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.contains("2022-" + month)) { // checking year and month
                    writer.println(line);
                }
            }
            writer.close(); //closes PrintWriter
            System.out.println("Data for " + month + " has been extracted and saved in Extracted_Month_" + month + ".txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}//ぶるぶ:3
