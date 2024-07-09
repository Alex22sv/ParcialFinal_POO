package org.alexsv.parcialfinal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {//00016023 - main class who create the welcome window
    @Override//00016023 - Redefine the star method for the new window
    public void start(Stage stage) throws IOException {//00016023 - Function who initialize the program
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("welcome-screen.fxml"));//00016023 - Define the fxml loader with the name of the file that we want to use
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);//00016023 - Define the scene with the main root and the size of the window
        stage.setTitle("BCN");//00016023 - Define the title of the window
        stage.setScene(scene);//00016023 - Put the scene in the stage
        stage.resizableProperty().setValue(false);//00016023 - Define the resizable property as false
        stage.show();//00016023 - Show the window
    }
    public static void main(String[] args) { // 00024123 Main method
        launch(); // 0002413 Launch a standalone application
    }
}