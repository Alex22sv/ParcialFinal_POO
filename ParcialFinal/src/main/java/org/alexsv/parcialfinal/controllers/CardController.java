package org.alexsv.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardController {
    //every variable has an identifier at the end (I insert, D delete, U update)
    @FXML
    protected void insertCard() throws SQLException { // 00024123 Method to insert a card
        Connection db = DatabaseConnection.getConnection(); // 00024123 Make the connection with the database
        PreparedStatement ps = db.prepareStatement("INSERT INTO Card(`cardNumber`, `expirationDate`, `type`, `facilitator`, `clientId`) VALUES (?,?,?,?,?)"); //00041923 Query to insert a new card

        ps.executeUpdate(); //00024123 execute the prepared statement query
        db.close(); // 00041923 close de database connection
    }

    @FXML
    protected void deleteCard() throws SQLException { //00041923 method to delete a card
        Connection db = DatabaseConnection.getConnection(); // 00041923 Make the connection with the database
        PreparedStatement preparedStatement = db.prepareStatement("DELETE FROM Client WHERE clientId = ?"); // 00024123 Create a prepared statement to delete the card
        preparedStatement.executeUpdate(); // 00024123 Execute the update
        db.close(); //00041923 Close de database connection
    }
}
