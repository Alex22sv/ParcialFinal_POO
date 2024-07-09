package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Client> clientsTableView; // 00024123 TableView to display the clients
    @FXML
    private TableColumn<Client, Integer> clientsTableViewClientId; // 00024123 TableColumn to display the client IDs
    @FXML
    private TableColumn<Client, String> clientsTableViewName; // 00024123 TableColumn to display the client names
    @FXML
    private TableColumn<Client, String> clientsTableViewAddress; // 00024123 TableColumn to display the client addresses
    @FXML
    private TableColumn<Client, String> clientsTableViewPhone; // 00024123 TableColumn to display the client phone numbers
    @FXML
    protected void initialize(){ //00041923 initialize some elements
        clientUpdateChoice.getItems().addAll("Name","Address","Phone Number"); //00041923 Add the options to the ChoiceBox
        clientsTableViewClientId.setCellValueFactory(new PropertyValueFactory<>("clientId")); // 00024123 Set the cell value factory for client IDs
        clientsTableViewName.setCellValueFactory(new PropertyValueFactory<>("name")); // 00024123 Set the cell value factory for client names
        clientsTableViewAddress.setCellValueFactory(new PropertyValueFactory<>("address")); // 00024123 Set the cell value factory for client addresses
        clientsTableViewPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber")); // 00024123 Set the cell value factory for client phone numbers
        clientsTableView.getItems().clear();  // 00024123 Clear the table items
    }
    @FXML
    protected void insert() { // 00041923 method to insert a client
        try { //00041923 tries the code below
            if ((!nameClientI.getText().equals("")) && (!lastnameClientI.getText().equals("")) && (!addressI.getText().equals("")) && (!phoneNumberI.getText().equals(""))) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement ps = db.prepareStatement("INSERT INTO Client (name,address,phoneNumber) VALUES (?,?,?)"); //00041923 query to insert another Client into table
                String fullname = nameClientI.getText() + " " + lastnameClientI.getText(); // 00024123 Create a String which to set the client's full name
                ps.setString(1, fullname); //00041923 put the parameters from the prepareStatement in first index, in this case is the name
                ps.setString(2, addressI.getText()); //00041923 put the parameters from the prepareStatement in second index, in this case is the address
                ps.setString(3, phoneNumberI.getText()); //00041923 put the parameters from the prepareStatement in third index, in this case is the phone number
                ps.executeUpdate(); // 00041923 execute the previous query
                nameClientI.setText("");  // 00024123 Clear the client's name input
                lastnameClientI.setText(""); // 00024123 Clear the client's last name input
                addressI.setText(""); // 00024123 Clear the client's address input
                phoneNumberI.setText(""); // 00024123 Clear the client's phone number input
                if (!clientsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
                    show(); // 00024123 Update the table with the new changes
                }
                successfullOperation(); // 00024123 Display successfull operation alert
                db.close(); // 00041923 Close de database connection
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        } catch (SQLException e) { //00041923 In case an error occurs in the SQL execute
            failedOperation(); //00041923 Displays an alert
        } catch (NumberFormatException e) { //00041923 In case an error occurs in the id verification
            typeError(); //00041923 displays an alert
        }
    }
    @FXML
    protected void update(){ //00041923 method to update a client
        try { // 00041923 tries the code below
            if ((clientUpdateChoice.getValue() != null) && (!updateField.getText().equals("")) && (!idClientU.getText().equals(""))) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                String column = ""; //00041923 variable that refers to the column to update
                switch (clientUpdateChoice.getValue()) { //00041923 verify the text on ComboBox to select a column
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
                PreparedStatement ps = db.prepareStatement("UPDATE Client SET " + column + " = ? WHERE clientId = ?");  // 00024123 Create a prepared statement to update the client information
                ps.setString(1, updateField.getText()); //00041923 put the parameters from the prepareStatement in first index, in this case is the information to update
                ps.setInt(2, Integer.parseInt(idClientU.getText())); //00041923 put the parameters from the prepareStatement in second index, in this case is the id to search
                ps.executeUpdate(); //00041923 execute the previous query
                clientUpdateChoice.setValue(null); // 00024123 Update the client's update choice
                updateField.setText(""); // 00024123 Clear the client's update field
                idClientU.setText(""); // 00024123 Clear the client's ID input
                if (!clientsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
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
    protected void delete(){ //00041923 method to delete a client
        try { //00041923 tries the code below
            if (!idClientD.getText().equals("")) { // 00024123 Check if user didn't miss any fields
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement preparedStatement = db.prepareStatement("DELETE FROM Client WHERE clientId = ?"); // 00024123 Create a prepared statement to delete the client
                preparedStatement.setInt(1, Integer.valueOf(idClientD.getText())); // 00024123 Put the parameter (client ID)
                preparedStatement.executeUpdate(); // 00024123 execute the previous query
                idClientD.setText(""); // 00024123 Clear the client's ID inout
                if (!clientsTableView.getItems().isEmpty()) { // 00024123 Check if the table view has elements
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
    protected void show(){ //00041923 method to show the table
        try { //00041923 tries the code below
            clientsTableView.getItems().clear(); // 00024123 Clear the table which could contain old data
            Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
            Statement statement = db.createStatement(); //00041923 create a statement to execute a query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Client"); //00041923 execute the query to select all clients
            while (resultSet.next()) { //00041923 continues while the table has more data
                clientsTableView.getItems().add(new Client(resultSet.getInt("clientId"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("phoneNumber"))); // 00024123 Add each client to the table
            }
            db.close(); //00041923 close de database connection
        }catch (SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //00041923 displays an alert
        }
    }
}