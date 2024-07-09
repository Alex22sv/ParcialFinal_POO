package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportBController extends Controller { // 00024123 class of the report B controller that extends from father Controller
    @FXML
    private TextField clientIDMoneySpentTextField; //00041923 id of the client who are going to search
    @FXML
    private TextField monthChoice; //00041923 TextArea with the month from transaction to search
    @FXML
    private TextField yearChoice; //00041923 TextArea with the year from transaction to search
    @FXML
    private TableView<ReportB> MoneySpentTable;  // 00024123 TableView to display the total amount spent
    @FXML
    private TableColumn<ReportB, String> clientsNameFromMoneySpentTableColumn; // 00024123 TableColumn to display the client name
    @FXML
    private TableColumn<ReportB, String> totalSpentFromMoneySpentTableColumn; // 00024123 TableColumn to display the total amount spent
    @FXML
    protected void reportB(){ //00041923 method to create the B report with help of a button
        try { //00041923 tries the code below
            if ((!clientIDMoneySpentTextField.getText().equals("")) && (!monthChoice.getText().equals("")) && (!yearChoice.getText().equals(""))) { // 00024123 Check if user didn't miss any fields
                clientsNameFromMoneySpentTableColumn.setCellValueFactory(new PropertyValueFactory<>("clientName")); // 00024123 Set the cell value factory for client name
                totalSpentFromMoneySpentTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmountSpent")); // 00024123 Set the cell value factory for total amount spent
                MoneySpentTable.getItems().clear(); // 00024123 Clear the table items
                ArrayList<String> data = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement ps = db.prepareStatement("SELECT Client.clientId, Client.name, MONTH(Transaction.purchaseDate) as monthT, YEAR(Transaction.purchaseDate) as yearT , SUM(Transaction.totalAmount) as total_t   FROM Client INNER JOIN Transaction on Transaction.clientId = Client.clientId WHERE Client.clientId = ?   AND YEAR(Transaction.purchaseDate) = ? AND MONTH(Transaction.purchaseDate) = ? GROUP BY Client.clientId,monthT,yearT"); //00041923 query to execute where select client id and a transaction date to search how much the client spent
                ps.setInt(1, Integer.parseInt(clientIDMoneySpentTextField.getText())); //00041923 put the parameters from the prepareStatement in first index, in this case an id
                ps.setInt(3, Integer.parseInt(monthChoice.getText())); //00041923 put the parameters from the prepareStatement in second index, in this case year (number)
                ps.setInt(2, Integer.parseInt(yearChoice.getText())); //00041923 put the parameters from the prepareStatement in third index, in this case a month (number)
                ResultSet rs = ps.executeQuery(); // 000041923 execute the previous query
                while (rs.next()) { // 00041923 continues while the table have more data
                    ReportB reportB = new ReportB(rs.getString("name"), "$" + rs.getString("total_t")); // 00024123 Create a new report B
                    MoneySpentTable.getItems().add(reportB); // 00024123 Add the report B to the table
                    data.add("Client id: " + rs.getString("clientId") + "Month: " + rs.getString("monthT") + "Year: " + rs.getString("yearT") + "Total: " + rs.getString("total_t") + "\n"); // 00041923 add to the collection the information of the table with respect to the query
                }
                String infoToWrite = String.join("\n", data); // 00041923 join the data collected in a whole String variable
                if (!infoToWrite.isEmpty()) { //00041923 in case there are information to write in a file
                    Utilities.fileCreator("B", infoToWrite); // 00041923 make a File with code name "B" and the content is the previous variable
                    fileCreated(); //00041923 displays an alert
                }else { //00041923 in case there is nothing to write in a file
                 emptyData(); //000 41923 displays an alert
                }
                db.close(); //00049123 close the connection with the database
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //000 41923 displays an alert
        }catch (IOException e){ //00041923 in case an error occurs in the file creation
            fileCreator(); //000 41923 displays an alert
        }catch (NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //000 41923 displays an alert
        }
    }
}
