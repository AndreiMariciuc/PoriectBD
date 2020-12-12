package autentificare;

import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlAutentificare {
    public PasswordField password;
    public TextField username;


    public void autentificare(ActionEvent actionEvent) throws IOException {
        String passwordBd = "admin";
        String userNameBd = "admin";
//        if(password.getText().equals(passwordBd) && username.getText().equals(userNameBd)) {
//        } else {
//            Parent root = FXMLLoader.load(getClass().getResource("../autentificare/autentificareEsuata.fxml"));
//            Stage curent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//            curent.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
//            curent.show();
//        }
        if(password.getText().equals("1")) {
            Parent root = FXMLLoader.load(getClass().getResource("../admin/superAdmin.fxml"));
            Stage curent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            curent.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
            curent.show();

        } else if(password.getText().equals("2")) {
            Parent root = FXMLLoader.load(getClass().getResource("../admin/admin.fxml"));
            Stage curent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            curent.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
            curent.show();

        } else if(password.getText().equals("3")) {
            Parent root = FXMLLoader.load(getClass().getResource("../profesor/profesor.fxml"));
            Stage curent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            curent.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
            curent.show();

        } else if(password.getText().equals("4")) {
            Parent root = FXMLLoader.load(getClass().getResource("../student/student.fxml"));
            Stage curent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            curent.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
            curent.show();

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("../autentificare/autentificareEsuata.fxml"));
            Stage curent = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            curent.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
            curent.show();
        }
    }
}
