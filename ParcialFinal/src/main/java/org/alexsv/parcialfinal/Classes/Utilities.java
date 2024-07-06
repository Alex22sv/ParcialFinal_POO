package org.alexsv.parcialfinal.Classes;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {
    private static final String folderPath=""; //00041923 path of the folder in which are going to save the reports
    //still don't have this ↑
    public static java.sql.Date dateFormat(TextArea date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // 00041923 set the format of the date (day - month - year)
        java.util.Date dateText = dateFormat.parse(date.getText()); // 0041923 make the TextArea text a date type
        return new Date(dateText.getTime()); // 00041923 make the previous java date type to a sql date type and return that date
    }

    public static void fileCreator(String code, String data) throws IOException {
        LocalDateTime currentDateTime = LocalDateTime.now(); //00041923 get the local date (day, month, year and hour)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss"); //00041923 set the format of the day
        String formattedDateTime = currentDateTime.format(formatter); //00041923 convert the time to a String with the previous format
        String nameFile = code +" " + formattedDateTime ; //00041923 the file name is a code with the current date
        File newFile = new File(folderPath,nameFile); //00041923 make a new file in the folder path
        FileWriter writer = new FileWriter(newFile,false); // 00041923 make a writer to write the stream of characters to the text file
        writer.write(data);//00041923 write in file the data variable value
        writer.close(); //00041923 close the writer
    }
    public static String censorCardNumber(String cardNumber){
        String censoredCardNumber = "";
        cardNumber = cardNumber.replace(" ", "");
        cardNumber = cardNumber.replace("-", "");
        censoredCardNumber = "XXXX XXXX XXXX " + cardNumber.substring(12);
        return censoredCardNumber;
    }
    public static String fixCardNumberFormat(String cardNumber){
        String fixedCardNumber = "";
        fixedCardNumber = cardNumber.replace("-", " ");
        return fixedCardNumber;
    }
}
