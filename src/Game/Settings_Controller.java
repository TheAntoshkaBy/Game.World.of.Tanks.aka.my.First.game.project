package Game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings_Controller implements Initializable {
    Stage stage;
    @FXML
    Button exit;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();

            }
        });
    }

}
