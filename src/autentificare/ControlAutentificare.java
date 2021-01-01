package autentificare;
import bazaDate.Conexiune;
import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlAutentificare {
    public PasswordField password;
    public TextField username;
    public ImageView clouds;
    public Text incorect;
    public ImageView user;
    public ImageView pass;
    int state = 0;

    public void autentificare(ActionEvent actionEvent) throws IOException {
        String pass = "123";//username.getText()
        String userName = "andrei"; //password.getText()
        Conexiune c = Conexiune.getConexiune();
        int rol = c.getUserType(userName, pass);//(username.getText(), password.getText());
        System.out.println(rol);
        System.out.println(Conexiune.getUser().getNume());
        if (rol == 2 || rol == 1) {
            incorect.setText("Admin");
            if (state == 1) {
                changeState();
            }
            loadScene(actionEvent, "../admin/admin.fxml");
        } else if (rol == 3) {
            incorect.setText("Profesor");
            if (state == 1) {
                changeState();
            }
            //de lucrat la gui!
            loadScene(actionEvent, "../profesor/profesor.fxml");
        } else if (rol == 4) {
            incorect.setText("Student");
            if (state == 1) {
                changeState();
            }
            //de lucrat la gui
            loadScene(actionEvent, "..\\student\\student.fxml");
        } else {
            if (state == 0) {
                changeState();
            }
        }
    }

    private void changeState() {
        if (state == 0) {
            incorect.setText("Datele introduse sunt incorecte");
            Image img = new Image("img/greyclouds.png");
            clouds.setImage(img);
            state = 1;
        } else {
            state = 0;
            Image img = new Image("img/blueClouds.png");
            clouds.setImage(img);
        }
    }

    public void loadScene(ActionEvent e, String caleFXML) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(caleFXML));
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
        currentStage.show();
    }
}
