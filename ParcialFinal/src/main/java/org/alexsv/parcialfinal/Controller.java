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
    public void successfullOperation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("The operation was successfully executed.");
        alert.showAndWait();
    }
    @FXML
    public void failedOperation(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("The operation resulted in an unexpected error.");
        alert.showAndWait();
    }
    @FXML
    public void emptyOperation(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Please fulfill all requirements.");
        alert.showAndWait();
    }
}