package org.alexsv.parcialfinal;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.alexsv.parcialfinal.Classes.DatabaseConnection;
import org.alexsv.parcialfinal.Classes.Utilities;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ReportCController extends Controller { // 00024123 class of the report C controller that extends from father Controller
    @FXML
    private TextField clientIDCardsAssociatedTextField; // 00041923 TextField with the id of the client who are going to search
    @FXML
    private TableView<ReportC> cardsAssociatedTable; // 00024123 TableView to display the cards associated to the client
    @FXML
    private TableColumn<ReportC, String> creditCardsFromCardsTableColumn; // 00024123 TableColumn to display the credit cards
    @FXML
    private TableColumn<ReportC, String> debitCardsFromCardsTableColumn; // 00024123 TableColumn to display the debit cards
    @FXML
    private void reportC(){ //00041923 method to create the C report with help of a button
        try { //00041923 tries the code below
            if (!clientIDCardsAssociatedTextField.getText().equals("")) { // 00024123 Check if user didn't miss any fields
                creditCardsFromCardsTableColumn.setCellValueFactory(new PropertyValueFactory<>("creditCard")); // 00024123 Set the cell value factory for credit cards
                debitCardsFromCardsTableColumn.setCellValueFactory(new PropertyValueFactory<>("debitCard")); // 00024123 Set the cell value factory for debit cards
                cardsAssociatedTable.getItems().clear(); // 00024123 Clear the table items
                ArrayList<String> creditData = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query, in this case the credit card type data
                ArrayList<String> debitData = new ArrayList<>(); // 00041923 Collection that helps to save the select data from the query, in this case the debit card type data
                Connection db = DatabaseConnection.getConnection(); // 00041923 make the connection with the database
                PreparedStatement ps1 = db.prepareStatement("SELECT cardNumber FROM Card where clientId = ? AND type = ?"); //00041923 query to execute where select a card number that belong to a client
                ps1.setInt(1, Integer.parseInt(clientIDCardsAssociatedTextField.getText())); //00041923 put the parameters from the prepareStatement in first index, in this case an id
                ps1.setString(2, "Credit card"); //00041923 put the parameters from the prepareStatement in second index, in this case the type is credit card
                ResultSet rs1 = ps1.executeQuery(); // 000041923 execute the previous query
                while (rs1.next()) {  // 00041923 continues while the table have more data
                    creditData.add(Utilities.censorCardNumber(rs1.getString("cardNumber")) + "\n"); // 00041923 add to the credit card collection the information of the card number censored
                }
                String creditTitle = "Credit cards: \n"; // 00041923 variable with a little title to add in the final report
                String infoToWrite1; //00041923 variable with the first statement to write
                if (creditData.isEmpty()) { // 00041923 verify if the collection don't have any strings (in other words if the client don't have any credit card)
                    infoToWrite1 = creditTitle + "N/A\n"; // 0041923 in case the collection is empty the first statement only have the title and "N/A" (Nothing)
                } else { // 0041923 if the collection have at least one item
                    String creditInfo = String.join("\n", creditData); // 00041923 join whole credit card data collected in a String variable
                    infoToWrite1 = creditTitle + creditInfo; // 00041923 the first statement will have the title and the credit cards
                }
                // 00041923 another variant for the select query ↓
                ps1.setInt(1, Integer.parseInt(clientIDCardsAssociatedTextField.getText())); // 00041923 put the parameters from the prepareStatement in first index, in this case an id
                ps1.setString(2, "Debit card"); //00041923 put the parameters from the prepareStatement in second index, in this case the type is debit card
                ResultSet rs2 = ps1.executeQuery(); // 000041923 execute the previous query with the variation
                while (rs2.next()) { // 00041923 continues while the table have more data
                    debitData.add(Utilities.censorCardNumber(rs2.getString("cardNumber")) + "\n"); // 00041923 add to the debit card collection the information of the card number censored
                }
                if (debitData.isEmpty() && creditData.isEmpty()) { // 00024123 Check if the client doesn't have credit nor debit cards
                    ReportC reportC = new ReportC("N/A", "N/A"); // 00024123 Create a new report C (without credit and debit cards)
                    cardsAssociatedTable.getItems().add(reportC); // 00024123 Add the report C to the table
                } else if (creditData.size() >= debitData.size()) { // 00024123 Check if the client has more credit cards than debit cards (or the same amount)
                    for (int i = 0; i < creditData.size(); i++) { // 00024123 Access all credit and debit cards
                        ReportC reportC = null; // 00024123 Initialize a new report C
                        if ((debitData.isEmpty()) && (i == 0)) { // 00024123 Check if client doesn't have debit cards and it's the first iteration
                            reportC = new ReportC(creditData.get(i), "N/A"); // 00024123 Create a new report C (with only credit cards)
                        } else if ((i>=debitData.size()) && (i > 0)) { // 00024123 Check if client doesn't have debit cards, and it's not the first iteration
                            reportC = new ReportC(creditData.get(i), ""); // 00024123 Create a new report C (with only credit cards)
                        } else { // 00024123 Client has both credit and debit card in this iteration
                            reportC = new ReportC(creditData.get(i), debitData.get(i)); // 00024123 Create a new report C (with both credit and debit cards)
                        }
                        cardsAssociatedTable.getItems().add(reportC); // 00024123 Add the report C to the table
                    }
                } else { // 00024123 The client has more debit cards than credit cards
                    for (int i = 0; i < debitData.size(); i++) { // 00024123 Access all credit and debit cards
                        ReportC reportC = null; // 00024123 Initialize a new report C
                        if ((creditData.isEmpty()) && (i == 0)) { // 00024123 Check if client doesn't have debit cards and it's the first iteration
                            reportC = new ReportC("N/A", debitData.get(i)); // 00024123 Create a new report C (with only debit cards)
                        } else if ((i>=creditData.size()) && (i > 0)) { // 00024123 Check if client doesn't have credit cards, and it's not the first iteration
                            reportC = new ReportC("", debitData.get(i)); // 00024123 Create a new report C (with only debit cards)
                        } else { // 00024123 Client has both debit and credit card in this iteration
                            reportC = new ReportC(creditData.get(i), debitData.get(i)); // 00024123 Create a new report C (with both debit and credit cards)
                        }
                        cardsAssociatedTable.getItems().add(reportC); // 00024123 Add the report C to the table
                    }
                }
                String debitTitle = "Debit cards: \n"; // 00041923 variable with a little title to add in the final report
                String infoToWrite2; //00041923 variable with the second statement to write
                if (debitData.isEmpty()) { // 00041923 verify if the collection don't have any strings (in other words if the client don't have any debit card)
                    infoToWrite2 = debitTitle + "N/A"; // 0041923 in case the collection is empty the second statement only have the title and "N/A" (Nothing)
                } else { // 0041923 if the collection have at least one item
                    String debitInfo = String.join("\n", debitData); // 00041923 join whole debit card data collected in a String variable
                    infoToWrite2 = debitTitle + debitInfo; //00041923 the second statement will have the title and the debit cards
                }
                String wholeInfo = infoToWrite1 + infoToWrite2; //00041923 variable with both statement
                if (Utilities.exists(Integer.parseInt(clientIDCardsAssociatedTextField.getText()))) { //00041923 in case the client id exists
                    Utilities.fileCreator("C", wholeInfo); // 00041923 make a File with code name "C" and the content is the previous variable
                    fileCreated(); // 00041923 displays an alert
                }else { //00041923 in case the id don't exist
                    fileCreator(); //00041923 displays an alert
                }
                db.close(); //00049123 close the connection with the database
            } else { // 00024123 The user forgot to fulfill all fields
                emptyOperation(); // 00024123 Display an alert to let the user know they are missing empty fields
            }
        }catch (SQLException e){ //in case an error occurs with the sql execute
            failedOperation(); //00041923 displays an alert
        }catch (IOException e){ //in case an error occurs with the file creation
            fileCreator(); //00041923 displays an alert
        }catch (NumberFormatException e){ //00041923 in case an error occurs in the id verification
            typeError(); //00041923 displays an alert
        }catch (IndexOutOfBoundsException e){ //00041923 in case an error occurs with an index in a collection
            failedOperation(); //00041923 displays an alert
        }
    }

}
