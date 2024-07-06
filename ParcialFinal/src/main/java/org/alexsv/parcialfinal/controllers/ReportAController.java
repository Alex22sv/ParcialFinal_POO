package org.alexsv.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class ReportAController {
    @FXML
    private TextArea idClient; //00041923 id of the client who are going to search
    @FXML
    private TextArea firstDate; //00041923 first date to compare
    @FXML
    private TextArea secondDate; //0004923 second date to compare
    @FXML
    protected void reportA() throws SQLException, ParseException, IOException { //00049123 method to create the A report with help of a button
        ArrayList<String> data = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        PreparedStatement ps = db.prepareStatement("SELECT Transaction.transactionId , Client.clientId, Client.name , Transaction.purchaseDate , Transaction.totalAmount" +
                " FROM Client INNER JOIN Transaction on Client.clientId = Transaction.clientId" +
                " where Client.clientId ="+idClient.getText() +" and Transaction.purchaseDate between ? and ?"); //00041923 query to execute where select some client and transaction attributes according to a specific Id and date range
        ps.setDate(1, Utilities.dateFormat(firstDate)); //00041923 put the parameters from the prepareStatement in first index, in this case a date
        ps.setDate(2,Utilities.dateFormat(secondDate)); //00041923 put the parameters from the prepareStatement in second index, in this case a date
        ResultSet rs = ps.executeQuery(); //00041923 execute the previous query
        while (rs.next()){ //00041923 continues while the table have more data
            data.add("Transaction Id: " + rs.getString("transactionId") + "client Id: " + rs.getString("clientId")
                    + "Name: " + rs.getString("name") + "Date: " + rs.getString("purchaseDate") + "Transaction amount: "+ rs.getString("totalAmount") +"\n"); //00041923 Add to the collection the information of the table with respect to the query
        }
        String infoToWrite = String.join("\n",data); // 00041923 join the data collected in a whole String variable
        Utilities.fileCreator("A",infoToWrite); // 00041923 make a File with code name "A" and the content is the previous variable
        db.close(); //00049123 close the connection with the database
    }
}