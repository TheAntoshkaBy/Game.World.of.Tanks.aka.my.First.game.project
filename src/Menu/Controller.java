package Menu;

import Game.GameDisplay;
import Game.Settings_Controller;
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


public class Controller implements Initializable
{

    Stage stage;
    @FXML Button out;


    public static void newMyStage() throws Exception {
        GameDisplay game1 = new GameDisplay();
        game1.start(new Stage());
    }
/** Create a new window for the game action**/

    public void NewGame(ActionEvent actionEvent) throws Exception {
        GameDisplay.NewGame = true;
        GameDisplay.Repeat = false;
        GameDisplay.Multi = false;
        GameDisplay game = new GameDisplay();
        Stage stage = new Stage();
        game.Repeat = false;
         game.start(stage);




    }

    public void Multipleer(ActionEvent actionEvent)throws Exception
    {
        GameDisplay.NewGame = false;
        GameDisplay.Repeat = false;
        GameDisplay.Multi = true;
        Stage stage = new Stage();

        GameDisplay Multi = new GameDisplay();
        Multi.start(stage);
    }
    public void Records(ActionEvent actionEvent) throws Exception {


/**Create a window for the record results**/
        Stage stage = new Stage();

        GameDisplay Repeat = new GameDisplay();
        GameDisplay.NewGame = false;
        GameDisplay.Repeat = true;
        GameDisplay.Multi = false;
        Repeat.start(stage);

}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void Settings(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Game/FXML/Settings.fxml"));
        Parent root = (Parent) loader.load();
        Settings_Controller controller = loader.getController();
        Scene scene = new Scene(root,700, 700);
        scene.getStylesheets().add(getClass().getResource("../Menu/Css/Game_Style.css").toExternalForm());

        stage.setTitle("Results");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setHeight(400);
        stage.setWidth(615);


        controller.setStage(stage);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }
}
