package sample;

import Menu.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Menu/Menu_FXML/sample.fxml"));
        Parent root = (Parent) loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root, 1024, 600);
        primaryStage.setTitle("WORLD OF TANKS");
        scene.getStylesheets().add(getClass().getResource("../sample/My_Style.css").toExternalForm());
        primaryStage.setScene(scene);
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
