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
    //INSERT
    @FXML
    private TextField idClientI; //00016023 TextField with the id of the Client who owns this card
    @FXML
    private ComboBox<String> cardTypeI; //00016023 ComboBox with information of the type of card
    @FXML
    private TextField facilitatorI; //00016023 TextField with information on the type of Card facilitator
    @FXML
    private TextField cardNumberI; //00016023  TextField with the phone information of the Card
    @FXML
    private DatePicker dateI; //00016023  TextField with information of the Card
    //DELETE
    @FXML
    private TextField idCardD; //00016023 TextField with the id of which card are going to delete
    //UPDATE
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
    private TableColumn<Card, String> cardsTableViewNumber; // 00024123 TableColumn to display the client names
    @FXML
    private TableColumn<Card, String> cardsTableViewExpDate;
    @FXML
    private TableColumn<Card, String> cardsTableViewType;
    @FXML
    private TableColumn<Card, String> cardsTableViewFacilitator;
    @FXML
    private TableColumn<Card, Integer> cardsTableViewClientId;
    @FXML
    protected void initialize(){ //00016023 initialize some elements
        cardUpdateChoice.getItems().addAll("Client's Id","Card Type","Facilitator","Card Number", "Exp Date"); //00016023 add the options to the ChoiceBox
        cardTypeI.getItems().addAll("Credit card", "Debit card");

        cardsTableViewId.setCellValueFactory(new PropertyValueFactory<>("cardId"));
        cardsTableViewNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        cardsTableViewExpDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        cardsTableViewType.setCellValueFactory(new PropertyValueFactory<>("cardType"));
        cardsTableViewFacilitator.setCellValueFactory(new PropertyValueFactory<>("facilitator"));
        cardsTableViewClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        cardsTableView.getItems().clear();
    }
    @FXML
    protected void insert(){ // 00016023 method to insert a card
        try { //00041923 try the code below
            if ((!cardNumberI.getText().equals("")) && (dateI.getValue() != null) && (cardTypeI.getValue() != "") && (!facilitatorI.getText().equals("")) && (!idClientI.getText().equals(""))) {
                Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
                Date expirationDate = java.util.Date.from(dateI.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                PreparedStatement ps = db.prepareStatement("INSERT INTO Card (cardNumber, expirationDate, type, facilitator, clientId) VALUES (?,?,?,?,?)"); //00016023 query to insert another Client into table
                ps.setString(1, Utilities.fixCardNumberFormat(cardNumberI.getText())); //00016023 put the parameters from the prepareStatement in first index, in this case is the cardNumber
                ps.setDate(2, new java.sql.Date(expirationDate.getTime())); //00016023 put the parameters from the prepareStatement in second index, in this case is the Exp Date
                ps.setString(3, (String) cardTypeI.getValue()); //00016023 put the parameters from the prepareStatement in third index, in this case is the card type
                ps.setString(4, facilitatorI.getText());//00016023 put the parameters from the prepareStatement in fourth index, in this case the Facilitator type
                ps.setInt(5, Integer.valueOf(idClientI.getText())); //00016023 put the parameters from the
                ps.executeUpdate(); // 00016023 execute the previous query
                cardNumberI.setText("");
                dateI.setValue(null);
                cardTypeI.setValue(null);
                facilitatorI.setText("");
                idClientI.setText("");
                if (!cardsTableView.getItems().isEmpty()) {
                    show();
                }
                successfullOperation();
                db.close(); // 00016023 close the database connection
            } else {
                emptyOperation();
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
            if ((cardUpdateChoice.getValue() != null) && (!updateField.getText().equals("")) && (!idCardU.getText().equals(""))) {
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
                PreparedStatement ps = db.prepareStatement("UPDATE Card SET " + column + " = ? WHERE cardId = ?"); //00016023 query to update a Card in table
                ps.setString(1, updateField.getText()); //00016023 put the parameters from the prepareStatement in first index, in this case is the information to update
                ps.setInt(2, Integer.parseInt(idCardU.getText())); //00016023 put the parameters from the prepareStatement in second index, in this case is the id to search
                ps.executeUpdate(); //00016023 execute the previous query
                cardUpdateChoice.setValue(null);
                updateField.setText("");
                idCardU.setText("");
                if (!cardsTableView.getItems().isEmpty()) {
                    show();
                }
                successfullOperation();
                db.close(); // 00016023 close the database connection
            } else {
                emptyOperation();
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
            if (!idCardD.getText().equals("")) {
                Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
                Statement statement = db.createStatement(); //00016023 create a statement to execute a query
                statement.execute("DELETE FROM Card WHERE cardId = " + idCardD.getText()); //00016023 execute the query deleting the card
                idCardD.setText("");
                if (!cardsTableView.getItems().isEmpty()) {
                    show();
                }
                successfullOperation();
                db.close(); //00016023 close the database connection
            } else {
                emptyOperation();
            }
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //000 41923 displays an alert
        }catch (NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //000 41923 displays an alert
        }
    }
    @FXML
    protected void show(){
        try { //00041923 tries the code below
            cardsTableView.getItems().clear();
            Connection db = DatabaseConnection.getConnection(); // 00024123 make the connection with the database
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Card");
            while (resultSet.next()) {
                cardsTableView.getItems().add(new Card(resultSet.getInt("cardId"), Utilities.censorCardNumber(resultSet.getString("cardNumber")), resultSet.getString("expirationDate"), resultSet.getString("type"), resultSet.getString("facilitator"), resultSet.getInt("clientId")));
            }
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //00041923 displays an alert
        }
    }
}
