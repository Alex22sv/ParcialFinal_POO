package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ReportAController extends Controller { // 00024123 class of the report A controller that extends from father Controller
    @FXML
    private TextField clientIdPurchaseField; //00041923 id of the client who are going to search
    @FXML
    private DatePicker startDatePurchasesDatePicker; //00041923 first date to compare
    @FXML
    private DatePicker endDatePurchasesDatePicker; //0004923 second date to compare
    @FXML
    private TableView<ReportA> purchasesMadeTable; // 00024123 TableView to display the purchases
    @FXML
    private TableColumn<ReportA, Date> purchaseDateFromPurchaseTableColumn; // 00024123 TableColumn to display the Date of purchase
    @FXML
    private TableColumn<ReportA, Double> totalAmountFromPurchaseTableColumn; // 00024123 TableColumn to display the total amount spent
    @FXML
    private TableColumn<ReportA, String> DescriptionFromPurchaseTableColumn; // 00024123 TableColumn to display the description
    @FXML
    private TableColumn<ReportA, String> electronicCardFromPurchaseTableColumn; // 00024123 TableColumn to display the electronic card
    @FXML
    protected void reportA(){ //00049123 method to create the A report with help of a button
        try { //00041923 tries the code below
            purchaseDateFromPurchaseTableColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate")); // 00024123 Set the cell value factory for purchase date
            totalAmountFromPurchaseTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount")); // 00024123 Set the cell value factory for total amount spent
            DescriptionFromPurchaseTableColumn.setCellValueFactory(new PropertyValueFactory<>("description")); // 00024123 Set the cell value factory for description
            electronicCardFromPurchaseTableColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber")); // 00024123 Set the cell value factory for card numbers
            purchasesMadeTable.getItems().clear(); // 00024123 Clear the table items
            if ((!clientIdPurchaseField.getText().equals("")) && (startDatePurchasesDatePicker.getValue() != null) && (endDatePurchasesDatePicker.getValue() != null)) { // 00024123 Check if user didn't miss any fields
                ArrayList<String> data = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement ps = db.prepareStatement("SELECT Transaction.transactionId , Client.clientId, Client.name , Transaction.purchaseDate , Transaction.totalAmount, Transaction.description, Card.cardNumber FROM Client INNER JOIN Transaction ON Client.clientId = Transaction.clientId INNER JOIN Card ON Card.cardId = Transaction.cardId WHERE Client.clientId = ? AND Transaction.purchaseDate BETWEEN ? AND ?"); //00041923 query to execute where select some client and transaction attributes according to a specific Id and date range
                ps.setInt(1, Integer.valueOf(clientIdPurchaseField.getText())); //00041923 put the parameters from the prepareStatement in first index, in this case a date
                ps.setDate(2, Date.valueOf(startDatePurchasesDatePicker.getValue())); //00041923 put the parameters from the prepareStatement in second index, in this case a date
                ps.setDate(3, Date.valueOf(endDatePurchasesDatePicker.getValue())); //00041923 put the parameters from the prepareStatement in third index, in this case a date
                ResultSet rs = ps.executeQuery(); //00041923 execute the previous query
                while (rs.next()) { //00041923 continues while the table has more data
                    ReportA reportA = new ReportA(rs.getDate("purchaseDate"), rs.getDouble("totalAmount"), rs.getString("description"), Utilities.censorCardNumber(rs.getString("cardNumber"))); // 00024123 Create a new report a
                    purchasesMadeTable.getItems().add(reportA); // 00024123 Add the report A to the table
                    data.add("Transaction Id: " + rs.getString("transactionId") + " Client Id: " + rs.getString("clientId") + " Name: " + rs.getString("name") + " Date: " + rs.getString("purchaseDate") + " Transaction amount: " + rs.getString("totalAmount") + "\n"); //00041923 Add to the collection the information of the table with respect to the query
                }
                String infoToWrite = String.join("\n", data); // 00041923 join the data collected in a whole String variable
                if (!infoToWrite.isEmpty()) { //00041923 in case there are information to write in a file
                    Utilities.fileCreator("A", infoToWrite); // 00041923 make a File with code name "A" and the content is the previous variable
                    fileCreated(); // 00041923 displays an alert
                }else { //00041923 in case there is nothing to write in a file
                    emptyData(); //000 41923 displays an alert
                }
                db.close(); //00049123 close the connection with the database
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //00041923 displays an alert
        }catch (NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //00041923 displays an alert
        }catch (IOException e){ //00041923 in case an error occurs in the file creation
            fileCreator(); //00041923 displays an alert
        }
    }

}