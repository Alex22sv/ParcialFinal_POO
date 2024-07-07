package org.alexsv.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;

import java.sql.*;

public class ClientController {
    //every variable has an identifier at the end (I insert, D delete, U update)
    @FXML
    private TextField nameClientI; //00041923 TextField with information of Client
    @FXML
    private TextField addressI; //00041923 TextField with information of Client
    @FXML
    private TextField phoneNumberI; //00041923  TextField with information of Client
    @FXML
    private TextField idClientD; //00041923 TextField with the id of which client are going to delete
    @FXML
    private TextField idClientU; //00041923 TextField with the id of which client are going to update
    @FXML
    private ComboBox<String> clientUpdateChoice; // 00041923 ComboBox with the update options
    @FXML
    private TextField updateField; //00041923 TextField with the information to update
    @FXML
    protected void insert() throws SQLException { // 00041923 method to insert a client
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        PreparedStatement ps = db.prepareStatement("INSERT INTO Client VALUES (?,?,?)"); //00041923 query to insert another Client into table
        ps.setString(1,nameClientI.getText()); //00041923 put the parameters from the prepareStatement in first index, in this case is the name
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
        Statement statement = db.createStatement(); //00041923 create a statement to execute a query
        statement.execute("UPDATE Client SET " + column + " = " + updateField.getText() + " WHERE clientId = " + idClientU.getText()); //00041923 Execute the query with the updates in table
        db.close(); // 00041923 close de database connection
    }

    @FXML
    protected void delete() throws SQLException { //00041923 method to delete a client
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        Statement statement = db.createStatement(); //00041923 create a statement to execute a query
        statement.execute("DELETE FROM Client WHERE ID = " + idClientD.getText()); //00041923 execute the query deleting the client
        db.close(); //00041923 close de database connection
    }

    @FXML
    protected void show(){
        //need what are we using to show
    }
}
