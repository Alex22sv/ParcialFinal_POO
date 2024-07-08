package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;

import java.sql.*;

public class TransactionController extends Controller{
    //every variable has an identifier at the end (I insert, D delete, U update)
    @FXML
    private TextField clientIdI;
    @FXML
    private TextField cardIdI;
    @FXML
    private DatePicker transactionDate;
    @FXML
    private TextField descriptionI;
    @FXML
    private TextField amountI;
    @FXML
    private ChoiceBox<String> transactionUpdateChoice;
    @FXML
    private TextField updateField;
    @FXML
    private TextField transactionIdU;
    @FXML
    private TextField transactionIdD;
    @FXML
    protected void initialize(){
        transactionUpdateChoice.getItems().addAll("Purchase Date","Total Amount","Description","Client Id","Card Id");
    }

    @FXML
    protected void insert() throws SQLException {
        Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
        PreparedStatement ps = db.prepareStatement("INSERT INTO Transaction (purchaseDate,totalAmount,description,clientId,cardId) VALUES (?,?,?,?,?)");
        ps.setDate(1, Date.valueOf(transactionDate.getValue()));
        ps.setDouble(2, Double.parseDouble(amountI.getText()));
        ps.setString(3,descriptionI.getText());
        ps.setInt(4, Integer.parseInt(clientIdI.getText()));
        ps.setInt(5, Integer.parseInt(cardIdI.getText()));
        ps.executeUpdate();
        db.close();
    }

    @FXML
    protected void update() throws SQLException {
        Connection db = DatabaseConnection.getConnection();
        String column = "";

        switch (transactionUpdateChoice.getValue()){
            case "Purchase Date":
                    column = "purchaseDate";
                break;
            case "Total Amount":
                    column = "totalAmount";
                break;
            case "Description":
                column = "description";
                break;
            case "Client Id":
                    column = "clientId";
                break;
            case "Card Id":
                    column = "cardId";
                break;
        }
        PreparedStatement ps = db.prepareStatement("UPDATE Transaction SET "+ column + " = ? WHERE clientId = ?");
        ps.setString(1,updateField.getText()); //revisar esto por los tipos de datos ALEX
        ps.setInt(2, Integer.parseInt(transactionIdU.getText()));
        ps.executeUpdate();
        db.close();
    }

    @FXML
    protected void delete() throws SQLException {
        Connection db = DatabaseConnection.getConnection();
        Statement statement = db.createStatement();
        statement.execute("DELETE FROM Transaction WHERE transactionId = " + transactionIdD);
        db.close();
    }

    @FXML
    protected void show(){
        //ALEX
    }

}
