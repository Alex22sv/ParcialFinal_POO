package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDController extends Controller { // 00024123 class of the report D controller that extends from father Controller
    @FXML
    private TextField facilitatorTextField; // 00041923 TextField with the facilitator of the card who are going to search
    @FXML
    private TableView<ReportD> purchasesWithFacilitatorTable; // 00024123 TableView to display the purchases with facilitator
    @FXML
    private TableColumn<ReportD, Integer> numFrompurchasesWithFacilitatorTableColumn; // 00024123 TableColumn to display the ID
    @FXML
    private TableColumn<ReportD, String> clientsNameFrompurchasesWithFacilitatorTableColumn; // 00024123 TableColumn to display the client name
    @FXML
    private TableColumn<ReportD, Integer> totalPurchasesFrompurchasesWithFacilitatorTableColumn; // 00024123 TableColumn to display the number of purchases
    @FXML
    private TableColumn<ReportD, String> amountSpentFrompurchasesWithFacilitatorTableColumn; // 00024123 TableColumn to display the total amount spent

    @FXML
    private void reportD(){ //00041923 method to create the D report with help of a button
        try { //00041923 tries the code below
            if (!facilitatorTextField.getText().equals("")) { // 00024123 Check if user didn't miss any fields
                numFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId")); // 00024123 Set the cell value factory for transaction ID
                clientsNameFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("clientName")); // 00024123 Set the cell value factory for client name
                totalPurchasesFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalPurchases")); // 00024123 Set the cell value factory from total purchase
                amountSpentFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmountSpent")); // 00024123 Set the cell value factory for total amount spent
                purchasesWithFacilitatorTable.getItems().clear(); // 00024123 Clear the table items
                ArrayList<String> facilitatorData = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement ps = db.prepareStatement("SELECT Client.clientId, Client.name, Card.cardNumber, SUM(Transaction.totalAmount) as totalAMOUNT, COUNT(Transaction.transactionId) as purchases FROM Transaction INNER JOIN Client on Transaction.clientId = Client.clientId INNER JOIN Card on Transaction.cardId = Card.cardId WHERE Card.facilitator = ? GROUP BY Client.clientId, Client.name, Card.cardNumber"); //00041923 query to execute where select a client, his card, how many transactions did and the total spent
                ps.setString(1, facilitatorTextField.getText()); //00041923 put the parameters from the prepareStatement in first index, in this case is a facilitator
                ResultSet rs = ps.executeQuery(); //00041923 execute the previous query
                while (rs.next()) { // 00041923 continues while the table has more data
                    ReportD reportD = new ReportD(rs.getInt("clientId"), rs.getString("name"), rs.getInt("purchases"), "$"+rs.getString("totalAmount")); // 00024123 Create a new report D
                    purchasesWithFacilitatorTable.getItems().add(reportD); // 00024123 Add the report D to the table
                    facilitatorData.add("Client: " + rs.getString("Client.name") + " Card number: " + Utilities.censorCardNumber(rs.getString("Card.cardNumber")) + " Purchases: " + rs.getString("purchases") + " Total amount: " + rs.getString("totalAMOUNT") + "\n"); // 00041923 add to the collection the information of the table with respect to the query
                }
                String tittle = "Facilitator: " + facilitatorTextField.getText() + "\n"; //00041923 little title for the statement
                String info = String.join("\n", facilitatorData); //00041923 join the data collected in a whole String variable
                if (!info.isEmpty()){ //00041923 in case there is at least one in the facilitator data
                    String infoToWrite = tittle + info; //00041923 the statement will have the title and the transactions info
                    Utilities.fileCreator("D", infoToWrite); // 00041923 make a File with code name "D" and the content is the previous variable
                    fileCreated(); //00041923 displays the alert
                }else { //00041923 in case there is nothing
                    emptyData(); //00041923 displays the alert
                }
                db.close(); //00049123 close the connection with the database
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        }catch (SQLException e){ //00041923 in case an error occurs with the sql execute
            failedOperation(); //00041923 displays an alert
        }catch (IOException e){ //00041923 in case an error occurs with the file creation
            fileCreator(); //00041923 displays an alert
        }
    }
}
