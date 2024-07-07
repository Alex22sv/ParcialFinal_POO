package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class ClientController extends Controller { //00041923 class of the client-screen controller that extends from father Controller
    //every variable has an identifier at the end (I insert, D delete, U update)
    @FXML
    private TextField nameClientI; //00041923 TextField with information of Client
    @FXML
    private TextField lastnameClientI; //00041923 TextField with information of Client
    @FXML
    private TextField addressI; //00041923 TextField with information of Client
    @FXML
    private TextField phoneNumberI; //00041923  TextField with information of Client
    @FXML
    private TextField idClientD; //00041923 TextField with the id of which client are going to delete
    @FXML
    private TextField idClientU; //00041923 TextField with the id of which client are going to update
    @FXML
    private ChoiceBox<String> clientUpdateChoice = new ChoiceBox<>(); // 00041923 ComboBox with the update options
    @FXML
    private TextField updateField; //00041923 TextField with the information to update
    @FXML
    private ScrollPane dataPane; //00041923 ScrollPane to see the table information
    @FXML
    private AnchorPane informationShow; //00041923 AnchorPane to write the table information
    @FXML
    protected void initialize(){ //00041923 initialize some elements
        clientUpdateChoice.getItems().addAll("Name","Address","Phone Number"); //00041923 add the options to the ChoiceBox
    }
    @FXML
    protected void insert() throws SQLException { // 00041923 method to insert a client
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        PreparedStatement ps = db.prepareStatement("INSERT INTO Client (name,address,phoneNumber) VALUES (?,?,?)"); //00041923 query to insert another Client into table
        String fullname = nameClientI.getText() +" " + lastnameClientI.getText();
        ps.setString(1,fullname); //00041923 put the parameters from the prepareStatement in first index, in this case is the name
        ps.setString(2, addressI.getText()); //00041923 put the parameters from the prepareStatement in second index, in this case is the address
        ps.setString(3, phoneNumberI.getText()); //00041923 put the parameters from the prepareStatement in third index, in this case is the phone number
        ps.executeUpdate(); // 00041923 execute the previous query
        db.close(); // 00041923 close de database connection
    }

    @FXML
    protected void update() throws SQLException { //00041923 method to update a client
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        String column = ""; //00041923 variable that refers to the column to update
        switch (clientUpdateChoice.getValue()){ //00041923 verify the text on ComboBox to select a column
            case "Name": // 00041923 in case the text on ComboBox is Name
                column = "name"; // 00041923 the column will be "name"
                break; // 00041923 break the sequence
            case "Address": //00041923 in case the text on ComboBox is Address
                column = "address"; //00041923 the column will be "address"
                break; // 00041923 break the sequence
            case "Phone Number": //00041923 in case the text on ComboBox is Phone Number
                column = "phoneNumber"; //00041923 the column will be "phoneNumber"
                break; // 00041923 break the sequence
        }
        PreparedStatement ps = db.prepareStatement("UPDATE Client SET " + column + " = ? WHERE clientId = ?"); //00041923 query to update a Client in table
        ps.setString(1,updateField.getText()); //00041923 put the parameters from the prepareStatement in first index, in this case is the information to update
        ps.setInt(2, Integer.parseInt(idClientU.getText())); //00041923 put the parameters from the prepareStatement in second index, in this case is the id to search
        ps.executeUpdate(); //00041923 execute the previous query
        db.close(); // 00041923 close de database connection
    }

    @FXML
    protected void delete() throws SQLException { //00041923 method to delete a client
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        Statement statement = db.createStatement(); //00041923 create a statement to execute a query
        statement.execute("DELETE FROM Client WHERE clientId = " + idClientD.getText()); //00041923 execute the query deleting the client
        db.close(); //00041923 close de database connection
    }

    @FXML
    protected void show() throws SQLException { //00041923 method to show the table
        ArrayList<String> tableData = new ArrayList<>(); //00041923 variable to save the table data
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        Statement st = db.createStatement(); //00041923 create a statement to execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM Client"); //00041923 execute the query to select all clients
        while (rs.next()){ //00041923 continues while the table have more data
            tableData.add("ID: " + rs.getString("clientId") + " | Name: " + rs.getString("name") + " | Address: " + rs.getString("address") + " | Phone Number: " + rs.getString("phoneNumber") + "\n");
            //(comment from above) 00041923 add to the collection the information of the table with respect to the query
        }
        String infoToShow = String.join("\n",tableData); //00041923 join the data collected in a whole String variable
        Label label = new Label(infoToShow); //00041923 Label to put the information on AnchorPane
        informationShow.getChildren().add(label); //00041923 add the label with information to the AnchorPane
        dataPane.setContent(informationShow); // 00041923 add to the ScrollPane the AnchorPane content (the information)
        db.close(); //00041923 close de database connection
    }
}
