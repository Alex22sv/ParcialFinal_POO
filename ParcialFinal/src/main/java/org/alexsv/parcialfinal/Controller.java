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

public class Controller {//00016023 - General controller who generates the navigation between windows
    @FXML//00016023 - Define the function as an option in fxml file
    public void openHome(ActionEvent event) throws IOException {//00016023 - Option who calls the Home window
        changeScene(event, "welcome-screen.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void openClient(ActionEvent event) throws IOException {//00016023 - Option who calls the Client window
        changeScene(event, "client-screen.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void openTransaction(ActionEvent event) throws IOException {//00016023 - Option who calls the Transaction window
        changeScene(event, "transactions-screen.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void openCards(ActionEvent event) throws IOException {//00016023 - Option who calls the Card window
        changeScene(event, "cards-screen.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void openA(ActionEvent event) throws IOException {//00016023 - Option who calls the Case A window
        changeScene(event, "purchases-made-screen.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void openB(ActionEvent event) throws IOException {//00016023 - Option who calls the Case B window
        changeScene(event, "money-spent-screen.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void openC(ActionEvent event) throws IOException {//00016023 - Option who calls the Case C window
        changeScene(event, "client-cards-screen.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void openD(ActionEvent event) throws IOException {//00016023 - Option who calls the Case D window
        changeScene(event, "purchases-card-facilitator.fxml", "BCN");//00016023 - makes the change for the new window
    }

    @FXML//00016023 - Define the function as an option in fxml file
    private void exitApplication(ActionEvent event) {//00016023 - Option who calls the close window method
        Platform.exit();//00016023 - Closes the window
    }

    protected void changeScene(ActionEvent event, String fxmlFile, String title) throws IOException {//00016023 - General method who change the current window for the one who is needed
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));//00016023 - Makes a new fxml Loder
        Parent root = fxmlLoader.load();//00016023 - Modificates the root
        Scene scene = new Scene(root, 900, 600);//00016023 - Create the scene and define the size of the window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();//00016023 - Create a stage who use the information to modificate the information in the window
        stage.setTitle(title);//00016023 - Set the title
        stage.setScene(scene);//00016023 - Create the window
        stage.show();//00016023 - Invoke the window
    }
    @FXML//00016023 - Define the function as an option in fxml file
    public void successfullOperation(){ // 00024123 this method displays an alert when the operation was successfully executed
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00024123 Create a new alert with the alert type
        alert.setTitle("Success"); // 00024123 Set the alert's title
        alert.setHeaderText("The operation was successfully executed."); // 00024123 Set the alert's header text
        alert.showAndWait();  // 00024123 Display the alert
    }
    @FXML//00016023 - Define the function as an option in fxml file
    public void failedOperation(){ // 00024123 this method displays an alert when the operation resulted in error
        Alert alert = new Alert(Alert.AlertType.ERROR); // 00024123 Create a new alert with the alert type
        alert.setTitle("Error!"); // 00024123 Set the alert's title
        alert.setHeaderText("The operation resulted in an unexpected error."); // 00024123 Set the alert's header text
        alert.showAndWait();  // 00024123 Display the alert
    }
    @FXML//00016023 - Define the function as an option in fxml file
    public void emptyOperation(){ // 00024123 this method displays an alert when the operation is missing fields
        Alert alert = new Alert(Alert.AlertType.WARNING); // 00024123 Create a new alert with the alert type
        alert.setTitle("Warning!"); // 00024123 Set the alert's title
        alert.setHeaderText("Please fulfill all fields."); // 00024123 Set the alert's header text
        alert.showAndWait();  // 00024123 Display the alert
    }

    @FXML//00016023 - Define the function as an option in fxml file
    public void typeError(){ // 00041923 this method displays an alert when the type doesn't match
        Alert alert = new Alert(Alert.AlertType.WARNING); //00041923 create a new alert
        alert.setTitle("Error!"); //00041923 set the alert title
        alert.setHeaderText("Please put the right type on fields"); //00041923 set the text on the alert
        alert.showAndWait(); //00041923 Display the alert
    }

    @FXML//00016023 - Define the function as an option in fxml file
    public void fileCreator(){ //00041923 this method displays an alert when a file isn't created
        Alert alert = new Alert(Alert.AlertType.ERROR); // 00041923 create a new alert
        alert.setTitle("Error"); //00041923 set the alert title
        alert.setHeaderText("Something goes wrong with the report file"); //00041923 set the text on the alert
        alert.showAndWait(); // 00041923 displays the alert
    }

    @FXML//00016023 - Define the function as an option in fxml file
    public void fileCreated(){ //00041923 this method displays an alert when a file is created
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00041923 create a new file
        alert.setTitle("Success"); //00041923 set the alert title
        alert.setHeaderText("The file was created successfully"); //00041923 set the text on the alert
        alert.showAndWait(); //00041923 displays the alert
    }
    @FXML//00016023 - Define the function as an option in fxml file
    public void emptyData(){ //00041923 this method displays an alert when there is nothing to write in a file
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //00041923 create a new alert
        alert.setTitle("Advertise"); //00041923 set the alert title
        alert.setHeaderText("There is nothing to show and save in a file"); //0041923 set the text on alert
        alert.showAndWait(); //00041923 displays the alert
    }

    @FXML //00041923 Define the function as an option in fxml file
    public void idNotFound(){ //00041923 this method displays an alert when the id is not found
        Alert alert = new Alert(Alert.AlertType.ERROR); // create a new alert
        alert.setTitle("Error"); //00041923 set the alert title
        alert.setHeaderText("Id not found, verify the database"); //00041923 set the text on alert
        alert.showAndWait(); //00041923 displays the alert
    }
}