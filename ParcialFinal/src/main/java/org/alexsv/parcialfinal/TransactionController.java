package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.sql.*;

public class TransactionController extends Controller{ //00024123 class of the transaction-screen controller that extends from father Controller
    //every variable has an identifier at the end (I insert, D delete, U update)
    @FXML
    private TextField clientIdI; //00024123 TextField with the id of the client who made the transaction
    @FXML
    private TextField cardIdI; //00024123 TextField with the id of the card used in the transaction
    @FXML
    private DatePicker transactionDate; //00024123 DatePicker with the date the transaction was made
    @FXML
    private TextField descriptionI; // 00024123 TextField with the description of the transaction
    @FXML
    private TextField amountI; // 00024123 TextField with the amount spent in the purchase
    @FXML
    private ChoiceBox<String> transactionUpdateChoice; // 00024123 ChoiceBox with the transaction update option
    @FXML
    private TextField updateField; // 00024123 TextField with the new information to be updated
    @FXML
    private TextField transactionIdU; // 00024123 TextField with the ID of the transaction to be updated
    @FXML
    private TextField transactionIdD; // 00024123 TextField with the ID of the transaction to be deleted
    @FXML
    private TableView<Transaction> transactionsTableView; // 00024123 TableView to display the transactions
    @FXML
    private TableColumn<Transaction, Integer> transactionsTableViewId; // 00024123 TableColumn to display the transaction's ID
    @FXML
    private TableColumn<Transaction, String> transactionsTableViewPurchaseDate; // 00024123 TableColumn to display the transaction's purchase dates
    @FXML
    private  TableColumn<Transaction, String> transactionsTableViewTotalAmount; // 00024123 TableColumn to display the transaction's total amount
    @FXML
    private TableColumn<Transaction, String> transactionsTableViewDescription; // 00024123 TableColumn to display the transaction's descriptions
    @FXML
    private TableColumn<Transaction, Integer> transactionsTableViewClientId; // 00024123 TableColumn to display the transaction's client ID
    @FXML
    private TableColumn<Transaction, Integer> transactionsTableViewCardId; // 00024123 TableColumn to display the transaction's card ID
    @FXML
    protected void initialize(){ //00024123 initialize some elements
        transactionUpdateChoice.getItems().addAll("Purchase Date","Total Amount","Description","Client Id","Card Id"); //00016023 add the options to the ChoiceBox
        transactionsTableViewId.setCellValueFactory(new PropertyValueFactory<>("transactionId")); // 00024123 Set the cell value factory for transaction IDs
        transactionsTableViewPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate")); // 00024123 Set the cell value factory for transaction purchase date
        transactionsTableViewTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount")); // 00024123 Set the cell value factory for transaction total amount
        transactionsTableViewDescription.setCellValueFactory(new PropertyValueFactory<>("description")); // 00024123 Set the cell value factory for transaction description
        transactionsTableViewClientId.setCellValueFactory(new PropertyValueFactory<>("clientId")); // 00024123 Set the cell value factory for transaction client ID
        transactionsTableViewCardId.setCellValueFactory(new PropertyValueFactory<>("cardId")); // 00024123 Set the cell value factory for transaction card ID
        transactionsTableView.getItems().clear(); // 00024123 Clear the table items
    }
    @FXML
    protected void insert(){ // 00024123 Method to insert a new transaction
        try { //00041923 tries the code below
            if ((transactionDate.getValue() != null) && (!amountI.getText().equals("")) && (!descriptionI.getText().equals("")) && (!clientIdI.getText().equals("")) && (!cardIdI.getText().equals(""))) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement ps = db.prepareStatement("INSERT INTO Transaction (purchaseDate,totalAmount,description,clientId,cardId) VALUES (?,?,?,?,?)"); //00016023 query to insert another transaction into table
                ps.setDate(1, Date.valueOf(transactionDate.getValue())); //00024123 put the parameters from the prepareStatement in first index, in this case is the transaction purchase date
                ps.setDouble(2, Double.parseDouble(amountI.getText())); //00024123 put the parameters from the prepareStatement in first index, in this case is the total amount
                ps.setString(3, descriptionI.getText()); //00024123 put the parameters from the prepareStatement in first index, in this case is the description
                ps.setInt(4, Integer.parseInt(clientIdI.getText())); //00024123 put the parameters from the prepareStatement in first index, in this case is the client ID
                ps.setInt(5, Integer.parseInt(cardIdI.getText())); //00024123 put the parameters from the prepareStatement in first index, in this case is the card ID
                ps.executeUpdate(); // 00024123 execute the previous query
                transactionDate.setValue(null); // 00024123 Clear the transaction date input
                amountI.setText(""); // 00024123 Clear the total amount input
                descriptionI.setText(""); // 00024123 Clear the description input
                clientIdI.setText(""); // 00024123 Clear the client ID that made the transaction input
                cardIdI.setText(""); // 00024123 Clear the card ID that was used in the transaction
                if (!transactionsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
                    show(); // 00024123 Update the table with the new changes
                }
                successfullOperation(); // 00024123 Display successfull operation alert
                db.close(); // 00041923 Close de database connection
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //00041923 displays an alert
        }catch (NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //00041923 displays an alert
        }
    }
    @FXML
    protected void update(){ // 00024123 Method to update an existing transaction
        try { //00041923 tries the code below
            if ((transactionUpdateChoice.getValue() != null) && (!updateField.getText().equals("")) && (!transactionIdU.getText().equals(""))) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                String column = ""; //00041923 variable that refers to the column to update
                switch (transactionUpdateChoice.getValue()) { //00041923 verify the text on ComboBox to select a column
                    case "Purchase Date": // 00041923 in case the text on ComboBox is Purchase Date
                        column = "purchaseDate"; // 00024123 set the column to "purchaseDate"
                        break; // 00024123 break the sequence
                    case "Total Amount": // 00041923 in case the text on ComboBox is Total Amount
                        column = "totalAmount"; // 00024123 set the column to "totalAmoun"
                        break; // 00024123 break the sequence
                    case "Description": // 00041923 in case the text on ComboBox is Description
                        column = "description"; // 00024123 set the column to "description"
                        break; // 00024123 break the sequence
                    case "Client Id": // 00041923 in case the text on ComboBox is Client Id
                        column = "clientId"; // 00024123 set the column to "clientId"
                        break; // 00024123 break the sequence
                    case "Card Id": // 00041923 in case the text on ComboBox is Card Id
                        column = "cardId"; // 00024123 set the column to "cardID"
                        break; // 00024123 break the sequence
                }
                PreparedStatement ps = db.prepareStatement("UPDATE Transaction SET "+ column + " = ? WHERE transactionId = ?"); // 00024123 Create a prepared statement to update the transaction information
                ps.setString(1, updateField.getText()); // 00024123 Put the parameter (Update field)
                ps.setInt(2, Integer.parseInt(transactionIdU.getText())); // 00024123 Put the parameter (transaction ID)
                ps.executeUpdate(); // 00024123 execute the previous query
                transactionUpdateChoice.setValue(null); // 00024123 Clear the transaction update choice
                updateField.setText(""); // 00024123 Clear the update field
                transactionIdU.setText(""); // 00024123 Clear the transaction ID input
                if (!transactionsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
                    show(); // 00024123 Update the table with the new changes
                }
                successfullOperation(); // 00024123 Display successfull operation alert
                db.close(); // 00041923 Close de database connection
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //00041923 displays an alert
        }catch (NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //00041923 displays an alert
        }
    }
    @FXML
    protected void delete() { // 00024123 Method to delete an existing transaction
        try { //00041923 tries the code below
            if(!transactionIdD.getText().equals("")){ // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00024123 make the connection with the database
                PreparedStatement preparedStatement = db.prepareStatement("DELETE FROM Transaction WHERE transactionId = ?"); // 00024123 Create a prepared statement to delete the transaction
                preparedStatement.setInt(1, Integer.valueOf(transactionIdD.getText())); // 00024123 Put the parameter (transaction ID)
                preparedStatement.executeUpdate(); // 00024123 execute the previous query
                transactionIdD.setText(""); // 00024123 Clear the transaction ID input
                if (!transactionsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
                    show(); // 00024123 Update the table with the new changes
                }
                successfullOperation(); // 00024123 Display successfull operation alert
                db.close(); // 00041923 Close de database connection
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //00041923 displays an alert
        }catch (NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //00041923 displays an alert
        }
    }
    @FXML
    protected void show() {
        try { //00041923 tries the code below
            transactionsTableView.getItems().clear(); // 00024123 Clear the table which could contain old data
            Connection db = DatabaseConnection.getConnection(); // 00024123 make the connection with the database
            Statement statement = db.createStatement(); //00041923 create a statement to execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Transaction"); //00041923 execute the query to select all transactions
            while(resultSet.next()){ //00041923 continues while the table has more data
                transactionsTableView.getItems().add(new Transaction(resultSet.getInt("transactionID"), resultSet.getString("purchaseDate"), "$" + resultSet.getString("totalAmount"), resultSet.getString("description"), resultSet.getInt("clientId"), resultSet.getInt("cardId"))); // 00024123 Add each transaction to the table
            }
            db.close(); //00041923 close de database connection
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //00041923 displays an alert
        }
    }

}
