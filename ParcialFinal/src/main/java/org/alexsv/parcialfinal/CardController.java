package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class CardController extends Controller { //00016023 class of the card-screen controller that extends from father Controller
    //00016023 Variables with an identifier at the end (I insert, D delete, U update)
    //INSERT
    @FXML
    private TextField idClientI; //00016023 TextField with the id of the Client who owns this card
    @FXML
    private ComboBox cardTypeI; //00016023 ComboBox with information of the type of card
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
    private AnchorPane informationShow; //00016023 AnchorPane to write the table information
    @FXML
    protected void initialize(){ //00016023 initialize some elements
        cardUpdateChoice.getItems().addAll("Client's Id","Card Type","Facilitator","Card Number", "Exp Date"); //00016023 add the options to the ChoiceBox
    }
    @FXML
    protected void insert() throws SQLException { // 00016023 method to insert a card
        Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
        PreparedStatement ps = db.prepareStatement("INSERT INTO Card (cardNumber, expirationDate, type, facilitator, clientId) VALUES (?,?,?,?,?)"); //00016023 query to insert another Client into table
        ps.setString(1,cardNumberI.getText()); //00016023 put the parameters from the prepareStatement in first index, in this case is the cardNumber
        ps.setString(2, String.valueOf(dateI)); //00016023 put the parameters from the prepareStatement in second index, in this case is the Exp Date
        ps.setString(3, String.valueOf(cardTypeI)); //00016023 put the parameters from the prepareStatement in third index, in this case is the card type
        ps.setString(4, String.valueOf(facilitatorI));//00016023 put the parameters from the prepareStatement in fourth index, in this case the Facilitator type
        ps.setString(5, String.valueOf(idClientI)); //00016023 put the parameters from the
        ps.executeUpdate(); // 00016023 execute the previous query
        db.close(); // 00016023 close the database connection
    }

    @FXML
    protected void update() throws SQLException { //00016023 method to update a card
        Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
        String column = ""; //00016023 variable that refers to the column to update
        switch (cardUpdateChoice.getValue()){ //00016023 verify the text on ComboBox to select a column
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
        ps.setString(1,updateField.getText()); //00016023 put the parameters from the prepareStatement in first index, in this case is the information to update
        ps.setInt(2, Integer.parseInt(idCardU.getText())); //00016023 put the parameters from the prepareStatement in second index, in this case is the id to search
        ps.executeUpdate(); //00016023 execute the previous query
        db.close(); // 00016023 close the database connection
    }

    @FXML
    protected void delete() throws SQLException { //00016023 method to delete a card
        Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
        Statement statement = db.createStatement(); //00016023 create a statement to execute a query
        statement.execute("DELETE FROM Card WHERE cardId = " + idCardD.getText()); //00016023 execute the query deleting the card
        db.close(); //00016023 close the database connection
    }

    /*@FXML
    protected void show() throws SQLException { //00016023 method to show the table
        ArrayList<String> tableData = new ArrayList<>(); //00016023 variable to save the table data
        Connection db = DatabaseConnection.getConnection(); // 00016023 make the connection with the database
        Statement st = db.createStatement(); //00016023 create a statement to execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM Card "); //00016023 execute the query to select all cards
        while (rs.next()){ //00016023 continues while the table have more data
            tableData.add("ID: " + rs.getString("clientId") + " | Name: " + rs.getString("name") + " | Address: " + rs.getString("address") + " | Phone Number: " + rs.getString("phoneNumber") + "\n");
            //(comment from above) 00016023 add to the collection the information of the table with respect to the query
        }
        String infoToShow = String.join("\n",tableData); //00016023 join the data collected in a whole String variable
        Label label = new Label(infoToShow); //00016023 Label to put the information on AnchorPane
        informationShow.getChildren().add(label); //00016023 add the label with information to the AnchorPane
        dataPane.setContent(informationShow); // 00016023 add to the ScrollPane the AnchorPane content (the information)
        db.close(); //00016023 close de database connection
    }*/
}
