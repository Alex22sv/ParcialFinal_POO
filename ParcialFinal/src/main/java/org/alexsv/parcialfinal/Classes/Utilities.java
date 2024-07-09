package org.alexsv.parcialfinal.Classes;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities { // 00024123 Utility class which contains static methods
    private static final String folderPath="./src/main/resources/reports/"; //00041923 path of the folder in which are going to save the reports
    public static void fileCreator(String code, String data) throws IOException { // 00024123 this method helps you create a txt file
        LocalDateTime currentDateTime = LocalDateTime.now(); //00041923 get the local date (day, month, year and hour)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"); //00041923 set the format of the day
        String formattedDateTime = currentDateTime.format(formatter); //00041923 convert the time to a String with the previous format
        String nameFile = code +" " + formattedDateTime + ".txt"; //00041923 the file name is a code with the current date
        File newFile = new File(folderPath,nameFile); //00041923 make a new file in the folder path
        FileWriter writer = new FileWriter(newFile,false); // 00041923 make a writer to write the stream of characters to the text file
        writer.write(data);//00041923 write in file the data variable value
        writer.close(); //00041923 close the writer
    }
    public static String censorCardNumber(String cardNumber){ // 00024123 this method censors the first 12 numbers and only displays the last 4
        cardNumber = cardNumber.replace(" ", ""); // 00024123 remove the spaces in the card number
        cardNumber = cardNumber.replace("-", ""); // 00024123 remove the hyphens in the card number
        String censoredCardNumber = "XXXX XXXX XXXX " + cardNumber.substring(12); // 00024123 remove the first 12 numbers of the card and replace them with X
        return censoredCardNumber; // 00024123 return the censored card number
    }
    public static String fixCardNumberFormat(String cardNumber){ //00024123 this method fixes the card number format (when the user separates the numbers with hyphens)
        String fixedCardNumber = cardNumber.replace("-", " "); // 00024123 replace the hyphens with spaces in the credit card number
        return fixedCardNumber; // 00024123 return the fixed credit card number
    }

    public static boolean exists(int idSearch) throws SQLException { //00041923 this method verify if the id to search exists
        Connection db = DatabaseConnection.getConnection(); //00041923 make the database connection
        PreparedStatement ps = db.prepareStatement("SELECT EXISTS(SELECT 1 FROM Client WHERE clientId = ?)"); //00041923 query to execute where verify if the client exists
        ps.setInt(1,idSearch); //00041923 put the parameters from the prepareStatement in first index, in this case an id to search
        ResultSet rs = ps.executeQuery(); //00041923 execute the previous query
        boolean exist = false; //00041923 variable that represent the exists status
        if (rs.next()){ //00041923 if the result has the data
            exist = rs.getBoolean(1); //00041923 sets the value of the previous variable to true
        }
        db.close(); //00041923 close the database connection
        return exist; //00041923 return the variable
    }
}
