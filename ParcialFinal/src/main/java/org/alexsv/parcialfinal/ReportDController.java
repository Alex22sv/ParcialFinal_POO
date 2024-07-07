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

public class ReportDController extends Controller {
    @FXML
    private TextField facilitatorTextField; // 00041923 TextField with the facilitator of the card who are going to search
    @FXML
    private TableView<ReportD> purchasesWithFacilitatorTable;
    @FXML
    private TableColumn<ReportD, Integer> numFrompurchasesWithFacilitatorTableColumn;
    @FXML
    private TableColumn<ReportD, String> clientsNameFrompurchasesWithFacilitatorTableColumn;
    @FXML
    private TableColumn<ReportD, Integer> totalPurchasesFrompurchasesWithFacilitatorTableColumn;
    @FXML
    private TableColumn<ReportD, Double> amountSpentFrompurchasesWithFacilitatorTableColumn;

    @FXML
    private void reportD() throws SQLException, IOException { //00041923 method to create the D report with help of a button
        numFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        clientsNameFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        totalPurchasesFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalPurchases"));
        amountSpentFrompurchasesWithFacilitatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmountSpent"));
        purchasesWithFacilitatorTable.getItems().clear();

        ArrayList<String> facilitatorData = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        PreparedStatement ps = db.prepareStatement("SELECT Transaction.transactionId, Client.name, Card.cardNumber, SUM(Transaction.totalAmount) as totalAMOUNT, COUNT(Transaction.transactionId) as purchases" +
                " FROM Transaction INNER JOIN Client on Transaction.clientId = Client.clientId INNER JOIN Card on Transaction.cardId = Card.cardId" +
                " WHERE Card.facilitator = ? GROUP BY Transaction.transactionId, Client.name, Card.cardNumber, Client.name, Card.cardNumber"); //00041923 query to execute where select a client, his card, how many transactions did and the total spent
        ps.setString(1,facilitatorTextField.getText()); //00041923 put the parameters from the prepareStatement in first index, in this case is a facilitator
        ResultSet rs = ps.executeQuery(); //00041923 execute the previous query
        while (rs.next()){ // 00041923 continues while the table have more data
            ReportD reportD = new ReportD(rs.getInt("transactionId"), rs.getString("name"), rs.getInt("purchases"), rs.getDouble("totalAmount"));
            purchasesWithFacilitatorTable.getItems().add(reportD);

            facilitatorData.add("Client: " + rs.getString("Client.name") + " Card number: " + Utilities.censorCardNumber(rs.getString("Card.cardNumber"))
            + "purchases: " + rs.getString("purchases") + " Total amount: " + rs.getString("totalAMOUNT") + "\n");
            // (comment of above) 00041923 add to the collection the information of the table with respect to the query
        }
        String tittle = "Facilitator: " + facilitatorTextField.getText() + "\n"; //00041923 little title for the statement
        String info = String.join("\n",facilitatorData); //00041923 join the data collected in a whole String variable
        String infoToWrite = tittle + info; //00041923 the statement will have the title and the transactions info
        //Utilities.fileCreator("D",infoToWrite); // 00041923 make a File with code name "D" and the content is the previous variable
        db.close(); //00049123 close the connection with the database
    }
}
