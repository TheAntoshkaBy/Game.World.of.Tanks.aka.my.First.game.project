package Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Records_Controller {

    public void Add(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Menu/Menu_FXML/Zapolnenoye.fxml"));

        stage.setTitle("Zapolny_raz_or_vilkoy_V_glaz");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root,500, 600);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../Menu/Css/Zapoln.css").toExternalForm());
        stage.setHeight(180);
        stage.setWidth(500);

        stage.show();
    }
}
