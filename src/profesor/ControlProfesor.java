package profesor;

import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlProfesor {
    public Button datePersonaleButton;
    public Button orarButton;
    public Button catalogButton;
    public Button programareCursuriButton;
    public Button grupuriButton;
    public Button programareExameneButton;
    public Pane datePersonalePane;
    public Pane orarPane;
    public Pane catalogPane;
    public Pane programareCursuriPane;
    public Pane grupuriPane;
    public Pane programareExamenePane;
    public Button deautentificareButton;


    public void schimbareStare(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == datePersonaleButton) {
            datePersonalePane.toFront();
        } else if(actionEvent.getSource() == orarButton) {
            orarPane.toFront();
        } else if(actionEvent.getSource() == catalogButton) {
            catalogPane.toFront();
        } else if(actionEvent.getSource() == programareCursuriButton) {
            programareCursuriPane.toFront();
        } else if(actionEvent.getSource() == grupuriButton) {
            grupuriPane.toFront();
        }else if(actionEvent.getSource() == programareExameneButton) {
            programareExamenePane.toFront();
        }
        else if(actionEvent.getSource() == deautentificareButton) {
            Parent root = FXMLLoader.load(getClass().getResource("../autentificare/autentificare.fxml"));
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
            currentStage.show();
        }
    }
}
