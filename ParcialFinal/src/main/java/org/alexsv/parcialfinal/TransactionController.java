package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

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
    private TableView<Transaction> transactionsTableView;
    @FXML
    private TableColumn<Transaction, Integer> transactionsTableViewId;
    @FXML
    private TableColumn<Transaction, String> transactionsTableViewPurchaseDate;
    @FXML
    private  TableColumn<Transaction, String> transactionsTableViewTotalAmount;
    @FXML
    private TableColumn<Transaction, String> transactionsTableViewDescription;
    @FXML
    private TableColumn<Transaction, Integer> transactionsTableViewClientId;
    @FXML
    private TableColumn<Transaction, Integer> transactionsTableViewCardId;
    @FXML
    protected void initialize(){
        transactionUpdateChoice.getItems().addAll("Purchase Date","Total Amount","Description","Client Id","Card Id");

        transactionsTableViewId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        transactionsTableViewPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        transactionsTableViewTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        transactionsTableViewDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        transactionsTableViewClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        transactionsTableViewCardId.setCellValueFactory(new PropertyValueFactory<>("cardId"));
        transactionsTableView.getItems().clear();

    }

    @FXML
    protected void insert(){
        try { //00041923 tries the code below
            if ((transactionDate.getValue() != null) && (!amountI.getText().equals("")) && (!descriptionI.getText().equals("")) && (!clientIdI.getText().equals("")) && (!cardIdI.getText().equals(""))) {
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement ps = db.prepareStatement("INSERT INTO Transaction (purchaseDate,totalAmount,description,clientId,cardId) VALUES (?,?,?,?,?)");
                ps.setDate(1, Date.valueOf(transactionDate.getValue()));
                ps.setDouble(2, Double.parseDouble(amountI.getText()));
                ps.setString(3, descriptionI.getText());
                ps.setInt(4, Integer.parseInt(clientIdI.getText()));
                ps.setInt(5, Integer.parseInt(cardIdI.getText()));
                ps.executeUpdate();
                if (!transactionsTableView.getItems().isEmpty()) {
                    show();
                }
                transactionDate.setValue(null);
                amountI.setText("");
                descriptionI.setText("");
                clientIdI.setText("");
                cardIdI.setText("");
                successfullOperation();
                db.close();
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
    protected void update(){
        try { //00041923 tries the code below
            if ((transactionUpdateChoice.getValue() != null) && (!updateField.getText().equals("")) && (!transactionIdU.getText().equals(""))) {
                Connection db = DatabaseConnection.getConnection();
                String column = "";

                switch (transactionUpdateChoice.getValue()) {
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
                PreparedStatement ps = db.prepareStatement("UPDATE Transaction SET " + column + " = ? WHERE clientId = ?");
                ps.setString(2, transactionIdU.getText());
                //implementar la modificacion segun columna
                transactionUpdateChoice.setValue(null);
                updateField.setText("");
                transactionIdU.setText("");
                if (!transactionsTableView.getItems().isEmpty()) {
                    show();
                }
                successfullOperation();
                db.close();
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
    protected void delete() {
        try { //00041923 tries the code below
            if(!transactionIdD.getText().equals("")){
                Connection db = DatabaseConnection.getConnection();
                Statement statement = db.createStatement();
                statement.execute("DELETE FROM Transaction WHERE transactionId = " + transactionIdD.getText());
                successfullOperation();
                transactionIdD.setText("");
                if(!transactionsTableView.getItems().isEmpty()){
                    show();
                }
                db.close();
            } else {
                emptyOperation();
            }
        } catch(SQLSyntaxErrorException e){ //00041923 in case an error occurs in the sql statement
            failedOperation(); //000 41923 displays an alert
        } catch(SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //000 41923 displays an alert
        } catch(NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //00041923 displays an alert
        }
    }

    @FXML
    protected void show() {
        try { //00041923 tries the code below
            transactionsTableView.getItems().clear();
            Connection db = DatabaseConnection.getConnection(); // 00024123 make the connection with the database
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Transaction");
            while(resultSet.next()){
                transactionsTableView.getItems().add(new Transaction(resultSet.getInt("transactionID"), resultSet.getString("purchaseDate"), "$" + resultSet.getString("totalAmount"), resultSet.getString("description"), resultSet.getInt("clientId"), resultSet.getInt("cardId")));
            }
        } catch(SQLException e){ //00041923 in case an error occurs in the sql execute
            failedOperation(); //000 41923 displays an alert
        }
    }

}
