package org.alexsv.parcialfinal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    public void openHome(ActionEvent event) throws IOException {
        changeScene(event, "welcome-screen.fxml", "APP");
    }

    @FXML
    private void openClient(ActionEvent event) throws IOException {
        changeScene(event, "client-screen.fxml", "APP");
    }

    @FXML
    private void openTransaction(ActionEvent event) throws IOException {
        changeScene(event, "transactions-screen.fxml", "APP");
    }

    @FXML
    private void openCards(ActionEvent event) throws IOException {
        changeScene(event, "cards-screen.fxml", "APP");
    }

    @FXML
    private void openA(ActionEvent event) throws IOException {
        changeScene(event, "purchases-made-screen.fxml", "APP");
    }

    @FXML
    private void openB(ActionEvent event) throws IOException {
        changeScene(event, "money-spent-screen.fxml", "APP");
    }

    @FXML
    private void openC(ActionEvent event) throws IOException {
        changeScene(event, "client-cards-screen.fxml", "APP");
    }

    @FXML
    private void openD(ActionEvent event) throws IOException {
        changeScene(event, "purchases-card-facilitator.fxml", "APP");
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    protected void changeScene(ActionEvent event, String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void successfullOperation(){ // 00024123 this method displays an alert when the operation was successfully executed
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00024123 Create a new alert with the alert type
        alert.setTitle("Success"); // 00024123 Set the alert's title
        alert.setHeaderText("The operation was successfully executed."); // 00024123 Set the alert's header text
        alert.showAndWait();  // 00024123 Display the alert
    }
    @FXML
    public void failedOperation(){ // 00024123 this method displays an alert when the operation resulted in error
        Alert alert = new Alert(Alert.AlertType.ERROR); // 00024123 Create a new alert with the alert type
        alert.setTitle("Error!"); // 00024123 Set the alert's title
        alert.setHeaderText("The operation resulted in an unexpected error."); // 00024123 Set the alert's header text
        alert.showAndWait();  // 00024123 Display the alert
    }
    @FXML
    public void emptyOperation(){ // 00024123 this method displays an alert when the operation is missing requirements
        Alert alert = new Alert(Alert.AlertType.WARNING); // 00024123 Create a new alert with the alert type
        alert.setTitle("Warning!"); // 00024123 Set the alert's title
        alert.setHeaderText("Please fulfill all requirements."); // 00024123 Set the alert's header text
        alert.showAndWait();  // 00024123 Display the alert
    }
}