package admin;
import bazaDate.Conexiune;
import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlAdmin implements Initializable {
    public Label welcome;
    public Text welcomeText;
    public Text rol;
    public Text CNP;
    public Text nume, prenume;
    public Text adresa;
    public Text nrTel;
    public Text email;
    public Text IBAN;
    public Text nrContract;
    public Text username;
    public Text parola;
    public Text tRol, tCNP, tNume, tPrenume, tAdresa, tNrTel, tEmail, tIBAN, tNrContract, tUsername, tParola;
    public Button bDatePersonale;
    public void setText () {
        welcomeText.setText("\t\tBun venit, " + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume()
                + "!\nAcestea sunt datele dumneavoastra personale!");
        rol.setText("Administrator");
        rol.setFill(Paint.valueOf("#b69b7d"));
        tRol.setText("Rol: ");
        CNP.setText(Conexiune.getUser().getCNP());
        tCNP.setText("CNP: ");
        nume.setText(Conexiune.getUser().getNume());
        tNume.setText("Nume: ");
        prenume.setText(Conexiune.getUser().getPrenume());
        tPrenume.setText("Prenume: ");
        adresa.setText(Conexiune.getUser().getAdresa());
        tAdresa.setText("Adresa: ");
        nrTel.setText(Conexiune.getUser().getNrTelefon());
        tNrTel.setText("Nr. de telefon:");
        IBAN.setText(Conexiune.getUser().getIBAN());
        tIBAN.setText("IBAN: ");
        email.setText(Conexiune.getUser().getEmail());
        tEmail.setText("e-mail:");
        nrContract.setText(String.valueOf(Conexiune.getUser().getNrContract()));
        tNrContract.setText("Nr. de contract: ");
        username.setText(Conexiune.getUser().getUsername());
        tUsername.setText("Username:");
        String password = new String("");
        for (int i = 0; i < Conexiune.getUser().getParola().length(); i++) {
            password += "*";
        }
        parola.setText(password);
        tParola.setText("Parola:");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome.setText("Bun venit:\n\t" + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume() + "!");
        setText();
    }
    public void setDatePersonale() {
        welcome.setText("Bun venit:\n\t" + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume() + "!");
        setText();
    }

    public void deconectare() throws IOException {
        Conexiune.resetUser();
    }

}