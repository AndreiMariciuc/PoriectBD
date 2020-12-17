package admin;
import bazaDate.Conexiune;
import bazaDate.User;
import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControlAdmin implements Initializable {
    public Pane date_personale, gestUtilizatori;
    public Label welcome;
    public Text welcomeText, rol, CNP, nume, prenume, adresa, nrTel, email, IBAN, nrContract, username, parola, textUserNeselectat_v;
    public Text tRol, tCNP, tNume, tPrenume, tAdresa, tNrTel, tEmail, tIBAN, tNrContract, tUsername, tParola;
    public TextField fieldNume_v, fieldPrenume_v, fieldCNP_v;
    public ChoiceBox<String> choiceRoluri_v;
    public Button butonCautare_v;
    public Text alegere_v, fieldTextAdresa_v, fieldTextNume_v, fieldTextIBAN_v, fieldTextParola_v, fieldTextPrenume_v, fieldTextNrTelefon_v, fieldTextUsername_v, fieldTextEmail_v, mesajEroareAlegereRol_v;
    public ChoiceBox<User> resultUtilizatori_v;
    public Button butonModificareDatePersonale_v, butonStergereUser_v, butonAsaRamane_v;
    public TextField tfieldAdresa_v, tfieldNume_v, tfieldIBAN_v, tfieldPrenume_v, tfieldNrTelefon_v, tfieldUsername_v, tfieldEmail_v;
    public PasswordField tfieldParola_v;
    ArrayList<User> userList;
    public void setText () {
        welcomeText.setText("\t\tBun venit, " + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume()
                + "!\nAcestea sunt datele dumneavoastra personale!");
        rol.setText("Administrator");
        rol.setFill(Paint.valueOf("#61b15a"));
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
        textUserNeselectat_v.setText("");
        date_personale.toFront();
        welcome.setText("Bun venit:\n\t" + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume() + "!");
        setText();
        setTheUsers();
        for (User u: userList) {
            MenuItem menu = new MenuItem(u.getString());
        }
        adaugareRoluriInMeniu();
        mesajEroareAlegereRol_v.setText("");
    }
    private void adaugareRoluriInMeniu() {
        ArrayList<String> roluri = new ArrayList<String>();
        roluri.add("Orice rol");
        roluri.add("Student");
        roluri.add("Profesor");
        if (Conexiune.getUser().getIdRol() == 1) {
            roluri.add("Administrator");
        }
        for (String rol: roluri) {
            choiceRoluri_v.getItems().add(rol);
        }
    }
    public void setDatePersonale() {
        welcome.setText("Bun venit:\n\t" + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume() + "!");
        setText();
        date_personale.toFront();
    }
    public void fereastraGestiuneUtilizatori() {
        gestUtilizatori.toFront();
    }
    public void deconectare(ActionEvent actionEvent) throws IOException {
        System.out.println("Se goleste memoria si se sterg datele din conexiune, pentru a spori securitatea!");
        Conexiune.resetUser();
        Parent root = FXMLLoader.load(getClass().getResource("../autentificare/autentificare.fxml"));
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root, Ecran.WIDTH, (int)(Ecran.HEIGHT / 1.5)));
        currentStage.show();
    }
    private void setTheUsers() {
        Conexiune c = Conexiune.getConexiune();
        ArrayList<Integer> roluri = new ArrayList<Integer>();
        roluri.add(3);
        roluri.add(4);
        if (Conexiune.getUser().getIdUser() == 1) {
            roluri.add(1);
        }
        this.userList = c.getUsersList(roluri);
    }
    public void butonSearchUtilizatori() {
        resultUtilizatori_v.getItems().remove(0, resultUtilizatori_v.getItems().size());
        String resultNume = fieldNume_v.getText().toLowerCase();
        String resultPrenume = fieldPrenume_v.getText().toLowerCase();
        String resultCNP = fieldCNP_v.getText();
        String resultRol = choiceRoluri_v.getValue();
        String query = "SELECT * FROM users WHERE lower(nume) like '%" + resultNume + "%' AND lower(prenume) like '%" + resultPrenume + "%' AND CNP like '%"
                + resultCNP + "%'";
        if (resultRol == null) {
            mesajEroareAlegereRol_v.setText("*Te rog sa selectezi un rol!");
        }else {
            mesajEroareAlegereRol_v.setText("");
            if (resultRol.equals("Orice rol")) {
                query += ";";
            }
            else if (resultRol.equals("Student")) {
                query += " AND id_rol = 4;";
            } else if (resultRol.equals("Profesor")) {
                query += " AND id_rol = 3;";
            } else if (resultRol.equals("Administrator")) {
                query += " AND id_rol = 2;";
            }
        }
        ArrayList<User> forResult = Conexiune.getConexiune().getResultByQuery(query);
        for (User u: forResult) {
            resultUtilizatori_v.getItems().add(u);
        }
        tfieldNume_v.setText("");
        tfieldIBAN_v.setText("");
        tfieldParola_v.setText("");
        tfieldPrenume_v.setText("");
        tfieldNrTelefon_v.setText("");
        tfieldUsername_v.setText("");
        tfieldEmail_v.setText("");
        tfieldAdresa_v.setText("");
    }
    public void modificareDatePersonaleUser() {
        if (resultUtilizatori_v.getValue() == null) {
            textUserNeselectat_v.setText("*Te rog sa alegi un user, altfel nu se va face nicio modificare!");
        } else {
            textUserNeselectat_v.setText("");
            tfieldNume_v.setText(resultUtilizatori_v.getValue().getNume());
            tfieldIBAN_v.setText(resultUtilizatori_v.getValue().getIBAN());
            tfieldParola_v.setText(resultUtilizatori_v.getValue().getParola());
            tfieldPrenume_v.setText(resultUtilizatori_v.getValue().getPrenume());
            tfieldNrTelefon_v.setText(resultUtilizatori_v.getValue().getNrTelefon());
            tfieldUsername_v.setText(resultUtilizatori_v.getValue().getUsername());
            tfieldEmail_v.setText(resultUtilizatori_v.getValue().getEmail());
            tfieldAdresa_v.setText(resultUtilizatori_v.getValue().getAdresa());
            System.out.println(resultUtilizatori_v.getValue());
        }
    }
    public void stergereUser() {
        if (resultUtilizatori_v.getValue() == null) {
            textUserNeselectat_v.setText("*Te rog sa alegi un user, altfel nu se va face nicio modificare!");
        } else {
            textUserNeselectat_v.setText("");
            String deleteStatement = "DELETE FROM users WHERE CNP = '" + resultUtilizatori_v.getValue().getCNP() + "';";
            System.out.println(deleteStatement);
            Conexiune.getConexiune().executareQuery(deleteStatement);
        }
    }
    public void confirmareModificare() {
        if (resultUtilizatori_v.getValue() == null) {
            textUserNeselectat_v.setText("*Te rog sa alegi un user, altfel nu se va face nicio modificare!");
        } else {
            textUserNeselectat_v.setText("");
            String updateStatement = "UPDATE users SET nume = '" + tfieldNume_v.getText() + "', prenume = '" + tfieldPrenume_v.getText() +
                    "', IBAN = '" + tfieldIBAN_v.getText() + "', nr_tel = '" + tfieldNrTelefon_v.getText() + "', username = '" +
                    tfieldUsername_v.getText() + "', email = '" + tfieldEmail_v.getText() + "', adresa = '" + tfieldAdresa_v.getText() + "' WHERE CNP = '" +
                    resultUtilizatori_v.getValue().getCNP() + "';";
            System.out.println(updateStatement);
            Conexiune.getConexiune().executareQuery(updateStatement);
        }
    }
}