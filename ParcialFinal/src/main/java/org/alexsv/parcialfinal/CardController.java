package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class CardController extends Controller { //00016023 class of the card-screen controller that extends from father Controller
    //00016023 Variables with an identifier at the end (I insert, D delete, U update)
    @FXML
    private TextField idClientI; //00016023 TextField with the id of the Client who owns this card
    @FXML
    private ComboBox<String> cardTypeI; //00016023 ComboBox with information of the type of card
    @FXML
    private TextField facilitatorI; //00016023 TextField with information on the type of Card facilitator
    @FXML
    private TextField cardNumberI; //00016023  TextField with the phone information of the Card
    @FXML
    private DatePicker dateI; //00016023  DatePicker with the date of expiration
    @FXML
    private TextField idCardD; //00016023 TextField with the id of which card are going to delete
    @FXML
    private TextField idCardU; //00016023 TextField with the id of which card are going to update
    @FXML
    private ChoiceBox<String> cardUpdateChoice = new ChoiceBox<>(); // 00016023 ComboBox with the update options
    @FXML
    private TextField updateField; //00016023 TextField with the information to update
    @FXML
    private ScrollPane dataPane; //00016023 ScrollPane to see the table information
    @FXML
    private TableView<Card> cardsTableView; // 00024123 TableView to display the cards
    @FXML
    private TableColumn<Card, Integer> cardsTableViewId; // 00024123 TableColumn to display the card IDs
    @FXML
    private TableColumn<Card, String> cardsTableViewNumber; // 00024123 TableColumn to display the card number
    @FXML
    private TableColumn<Card, String> cardsTableViewExpDate; // 00024123 TableColumn to display the card IDs
    @FXML
    private TableColumn<Card, String> cardsTableViewType; // 00024123 TableColumn to display the card type
    @FXML
    private TableColumn<Card, String> cardsTableViewFacilitator; // 00024123 TableColumn to display the card facilitator
    @FXML
    private TableColumn<Card, Integer> cardsTableViewClientId;// 00024123 TableColumn to display the client ID that owns the card
    @FXML
    protected void initialize(){ //00016023 initialize some elements
        cardUpdateChoice.getItems().addAll("Client's Id","Card Type","Facilitator","Card Number", "Exp Date"); //00016023 add the options to the ChoiceBox
        cardTypeI.getItems().addAll("Credit card", "Debit card"); // 00024123 Add the options to the ComboBox
        cardsTableViewId.setCellValueFactory(new PropertyValueFactory<>("cardId")); // 00024123 Set the cell value factory for card IDs
        cardsTableViewNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber")); // 00024123 Set the cell value factory for card numbers
        cardsTableViewExpDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate")); // 00024123 Set the cell value factory for card expiration dates
        cardsTableViewType.setCellValueFactory(new PropertyValueFactory<>("cardType")); // 00024123 Set the cell value factory for card types
        cardsTableViewFacilitator.setCellValueFactory(new PropertyValueFactory<>("facilitator")); // 00024123 Set the cell value factory for card facilitator
        cardsTableViewClientId.setCellValueFactory(new PropertyValueFactory<>("clientId")); // 00024123 Set the cell value factory for the IDs of the clients that own the card
        cardsTableView.getItems().clear(); // 00024123 Clear the table items
    }
    @FXML
    protected void insert(){ // 00016023 method to insert a card
        try { //00041923 try the code below
            if ((!cardNumberI.getText().equals("")) && (dateI.getValue() != null) && (cardTypeI.getValue() != "") && (!facilitatorI.getText().equals("")) && (!idClientI.getText().equals(""))) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
                Date expirationDate = java.util.Date.from(dateI.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()); // 00024123 Get the Java Date of the expiration date field
                PreparedStatement ps = db.prepareStatement("INSERT INTO Card (cardNumber, expirationDate, type, facilitator, clientId) VALUES (?,?,?,?,?)"); //00016023 query to insert another Client into table
                ps.setString(1, Utilities.fixCardNumberFormat(cardNumberI.getText())); //00016023 put the parameters from the prepareStatement in first index, in this case is the cardNumber
                ps.setDate(2, new java.sql.Date(expirationDate.getTime())); //00016023 put the parameters from the prepareStatement in second index, in this case is the Exp Date
                ps.setString(3, (String) cardTypeI.getValue()); //00016023 put the parameters from the prepareStatement in third index, in this case is the card type
                ps.setString(4, facilitatorI.getText());//00016023 put the parameters from the prepareStatement in fourth index, in this case the Facilitator type
                ps.setInt(5, Integer.valueOf(idClientI.getText())); //00016023 put the parameters from the preparedStatement in fifth index, in this case the client ID
                ps.executeUpdate(); // 00016023 execute the previous query
                cardNumberI.setText(""); // 00024123 Clear the card number input
                dateI.setValue(null); // 00024123 Clear the expiration date input
                cardTypeI.setValue(null); // 00024123 Clear the card type input
                facilitatorI.setText(""); // 00024123 Clear the facilitator input
                idClientI.setText(""); // 00024123 Clear the client ID input
                if (!cardsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
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
    protected void update() { //00016023 method to update a card
        try { //00041923 tries the code below
            if ((cardUpdateChoice.getValue() != null) && (!updateField.getText().equals("")) && (!idCardU.getText().equals(""))) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
                String column = ""; //00016023 variable that refers to the column to update
                switch (cardUpdateChoice.getValue()) { //00016023 verify the text on ComboBox to select a column
                    case "Client's Id": // 00016023 in case the text on ComboBox is Client's Id
                        column = "clientId"; // 00016023 the column will be "clientId"
                        break; // 00016023 break the sequence
                    case "Card Type": //00016023 in case the text on ComboBox is Card Type
                        column = "type"; //00016023 the column will be "type"
                        break; // 00016023 break the sequence
                    case "Facilitator": // 00016023 in case the text on ComboBox is Facilitator
                        column = "facilitator"; // 00016023 the column will be "facilitator"
                        break; // 00016023 break the sequence
                    case "Card Number": //00016023 in case the text on ComboBox is Card Number
                        column = "cardNumber"; //00016023 the column will be "cardNumber"
                        break; // 00016023 break the sequence
                    case "Exp Date": //00016023 in case the text on ComboBox is Exp Date
                        column = "expirationDate"; //00016023 the column will be "expirationDate"
                        break; // 00016023 break the sequence
                }
                PreparedStatement ps = db.prepareStatement("UPDATE Card SET " + column + " = ? WHERE cardId = ?"); // 00024123 Create a prepared statement to update the card information
                ps.setString(1, updateField.getText()); //00016023 put the parameters from the prepareStatement in first index, in this case is the information to update
                ps.setInt(2, Integer.parseInt(idCardU.getText())); //00016023 put the parameters from the prepareStatement in second index, in this case is the id to search
                ps.executeUpdate(); //00016023 execute the previous query
                cardUpdateChoice.setValue(null); // 00024123 Update the card's update choice
                updateField.setText(""); // 00024123 Update the card's update field
                idCardU.setText(""); // 00024123 Update the card's ID input
                if (!cardsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
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
    protected void delete(){ //00016023 method to delete a card
        try { //00041923 tries the code below
            if (!idCardD.getText().equals("")) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
                PreparedStatement preparedStatement = db.prepareStatement("DELETE FROM Transaction WHERE cardId = ?"); // 00024123 Create a prepared statement to delete the card
                preparedStatement.setInt(1, Integer.valueOf(idCardD.getText())); // 00024123 Put the parameter (card ID)
                preparedStatement.executeUpdate(); // 00024123 execute the previous query
                idCardD.setText(""); // 00024123 Clear the card's ID input
                if (!cardsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
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
    protected void show(){
        try { //00041923 tries the code below
            cardsTableView.getItems().clear(); // 00024123 Clear the table which could contain old data
            Connection db = DatabaseConnection.getConnection(); // 00024123 make the connection with the database
            Statement statement = db.createStatement(); //00041923 create a statement to execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Card"); //00041923 execute the query to select all cards
            while (resultSet.next()) { //00041923 continues while the table has more data
                cardsTableView.getItems().add(new Card(resultSet.getInt("cardId"), Utilities.censorCardNumber(resultSet.getString("cardNumber")), resultSet.getString("expirationDate"), resultSet.getString("type"), resultSet.getString("facilitator"), resultSet.getInt("clientId"))); // 00024123 Add each card to the table
            }
            db.close(); //00041923 close de database connection
        }catch (SQLException e){ //00041923 in case an error occurs in the SQL execute
            failedOperation(); //00041923 displays an alert
        }
    }
}
