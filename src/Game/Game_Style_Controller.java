package Game;

/*import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;*/

import Game.GameDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Game_Style_Controller implements Initializable {

    Stage stage;
    Stage gameStage;
    @FXML
    Button exit;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setGameStage(Stage stage)
    {
        this.gameStage = stage;
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
