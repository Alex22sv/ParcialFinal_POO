package org.alexsv.parcialfinal.controllers.reports;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportBController {
    @FXML
    private TextField idClient; //00041923 id of the client who are going to search
    @FXML
    private TextArea monthTransaction; //00041923 TextArea with the month from transaction to search
    @FXML
    private TextArea yearTransaction; //00041923 TextArea with the year from transaction to search

    @FXML
    protected void reportB() throws SQLException, IOException { //00041923 method to create the B report with help of a button
        ArrayList<String> data = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        PreparedStatement ps = db.prepareStatement("SELECT Client.clientId, MONTH(Transaction.purchaseDate) as monthT, YEAR(Transaction.purchaseDate) as yearT"
                +", SUM(Transaction.totalAmount) as total_t " +" FROM Client INNER JOIN Transaction on Transaction.clientId = Client.clientId WHERE Client.clientId = ? " +
                " AND YEAR(Transaction.purchaseDate) = ? AND MONTH(Transaction.purchaseDate) = ? GROUP BY Client.clientId,monthT,yearT"); //00041923 query to execute where select client id and a transaction date to search how much the client spent
        ps.setInt(1, Integer.parseInt(idClient.getText())); //00041923 put the parameters from the prepareStatement in first index, in this case an id
        ps.setInt(2, Integer.parseInt(yearTransaction.getText())); //00041923 put the parameters from the prepareStatement in second index, in this case year (number)
        ps.setInt(3, Integer.parseInt(monthTransaction.getText())); //00041923 put the parameters from the prepareStatement in third index, in this case a month (number)
        ResultSet rs = ps.executeQuery(); // 000041923 execute the previous query
        while (rs.next()){ // 00041923 continues while the table have more data
            data.add("Client id: " + rs.getString("clientId") + "Month: " + rs.getString("monthT") + "Year: " + rs.getString("yearT") + "Total: " + rs.getString("total_t") +"\n");
            //(comment of above) 00041923 add to the collection the information of the table with respect to the query
        }
        String infoToWrite = String.join("\n",data); // 00041923 join the data collected in a whole String variable
        Utilities.fileCreator("B",infoToWrite); // 00041923 make a File with code name "B" and the content is the previous variable
        db.close(); //00049123 close the connection with the database
    }

}
