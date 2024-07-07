package org.alexsv.parcialfinal.controllers.reports;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ReportCController {
    @FXML
    private TextField idClient; // 00041923 TextField with the id of the client who are going to search
    @FXML
    private void reportC() throws SQLException, IOException { //00041923 method to create the C report with help of a button
        ArrayList<String> creditData = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query, in this case the credit card type data
        ArrayList<String> debitData = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query, in this case the debit card type data
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        PreparedStatement ps1 = db.prepareStatement("SELECT cardNumber FROM Card where clientId = ? AND type = ?"); //00041923 query to execute where select a card number that belong to a client
        ps1.setInt(1, Integer.parseInt(idClient.getText())); //00041923 put the parameters from the prepareStatement in first index, in this case an id
        ps1.setString(2,"Credit card"); //00041923 put the parameters from the prepareStatement in second index, in this case the type is credit card
        ResultSet rs1 = ps1.executeQuery(); // 000041923 execute the previous query
        while (rs1.next()){  // 00041923 continues while the table have more data
            creditData.add(Utilities.censorCardNumber(rs1.getString("cardNumber"))+"\n"); // 00041923 add to the credit card collection the information of the card number censored
        }
        String creditTitle = "Credit cards: \n"; // 00041923 variable with a little title to add in the final report
        String infoToWrite1; //00041923 variable with the first statement to write
        if (creditData.isEmpty()){ // 00041923 verify if the collection don't have any strings (in other words if the client don't have any credit card)
            infoToWrite1 = creditTitle + "N/A"; // 0041923 in case the collection is empty the first statement only have the title and "N/A" (Nothing)
        }else{ // 0041923 if the collection have at least one item
            String creditInfo = String.join("\n",creditData); // 00041923 join whole credit card data collected in a String variable
            infoToWrite1 = creditTitle + creditInfo; // 00041923 the first statement will have the title and the credit cards
        }
        // 00041923 another variant for the select query ↓
        ps1.setInt(1, Integer.parseInt(idClient.getText())); // 00041923 put the parameters from the prepareStatement in first index, in this case an id
        ps1.setString(2,"Debit card"); //00041923 put the parameters from the prepareStatement in second index, in this case the type is debit card
        ResultSet rs2 = ps1.executeQuery(); // 000041923 execute the previous query with the variation
        while (rs2.next()){ // 00041923 continues while the table have more data
            debitData.add(Utilities.censorCardNumber(rs1.getString("cardNumber"))+"\n"); // 00041923 add to the debit card collection the information of the card number censored
        }
        String debitTitle = "Debit cards: \n"; // 00041923 variable with a little title to add in the final report
        String infoToWrite2; //00041923 variable with the second statement to write
        if (debitData.isEmpty()){ // 00041923 verify if the collection don't have any strings (in other words if the client don't have any debit card)
            infoToWrite2 = debitTitle + "N/A"; // 0041923 in case the collection is empty the second statement only have the title and "N/A" (Nothing)
        }else{ // 0041923 if the collection have at least one item
            String debitInfo = String.join("\n",debitData); // 00041923 join whole debit card data collected in a String variable
            infoToWrite2 = debitTitle + debitInfo; //00041923 the second statement will have the title and the debit cards
        }
        String wholeInfo = infoToWrite1 + infoToWrite2; //00041923 variable with both statement
        Utilities.fileCreator("C",wholeInfo); // 00041923 make a File with code name "C" and the content is the previous variable
        db.close(); //00049123 close the connection with the database
    }
}
