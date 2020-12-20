package student;

import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlStudent {

    public Button datePersonaleButton;
    public Button orarButton;
    public Button carnetbutton;
    public Button gestionareCursuriButton;
    public Button grupuriButton;
    public Button deautentificareButton;
    public Pane datePersonalePane;
    public Pane orarPane;
    public Pane carnetPane;
    public Pane gestionareCursuriPane;
    public Pane grupuriPane;


    public void schimbaStari(ActionEvent actionEvent) throws IOException {
        //de facut fiecare pane dupa preferinte si necesitati!
        if(actionEvent.getSource() == datePersonaleButton) {
            datePersonalePane.toFront();
        } else if(actionEvent.getSource() == orarButton) {
            orarPane.toFront();
        } else if(actionEvent.getSource() == carnetbutton) {
            carnetPane.toFront();
        } else if(actionEvent.getSource() == gestionareCursuriButton) {
            gestionareCursuriPane.toFront();
        } else if(actionEvent.getSource() == grupuriButton) {
            grupuriPane.toFront();
        } else if(actionEvent.getSource() == deautentificareButton) {
            Parent root = FXMLLoader.load(getClass().getResource("../autentificare/autentificare.fxml"));
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
            currentStage.show();
        }
    }
}
