package admin;
import bazaDate.*;
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
import java.util.ArrayList;
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

    public TextField tfieldNrOreStud_a, tfieldNume_a, tfieldCNP_a, tfieldIBAN_a, tfieldNrTel_a, tfieldPrenume_a, tfieldEmail_a, tfieldAdresa_a, tfieldNrContract_a, tfieldNrMaxOre_a, tfieldNrMinOre_a;
    public ChoiceBox<String> choiceAnStudiu_a, choiceStatus_a, choiceRol_a, choiceDepartament_a;
    public Button butonAdaugare_a;
    public Pane paneStudenti_a, paneProfesori_a, paneAdaugare, panouCautareCursuri_c;
    public Text eroareCampuriNecompletate_a, eroareTipInvalid_a, eroareLipsaRol_a, succesAdaugare_a;
    public void alegereRolClick() {
        if (choiceRol_a.getValue() != null) {
            if (choiceRol_a.getValue().equals("Student")) {
                paneStudenti_a.toFront();
                paneProfesori_a.toBack();
            } else if (choiceRol_a.getValue().equals("Profesor")) {
                paneProfesori_a.toFront();
                paneStudenti_a.toBack();
            } else if (choiceRol_a.getValue().equals("Administrator")) {
                paneStudenti_a.toBack();
                paneProfesori_a.toBack();
            }
        }
    }
    public void paneAdaugareToFront() {
        paneAdaugare.toFront();
    }
    ArrayList<User> userList;
    ArrayList<VedereCurs> listaCursuri;
    ArrayList<VedereStudentiLaCurs> listaStudentiLaCursuri;
    public ListView<String> listViewCursuri_c, listViewStudenti_c;
    public Button butonCautare_c;
    public ChoiceBox<Cursuri> choiceCurs_c;

    public ChoiceBox<Profesor> choiceProfesor_ap;
    public ChoiceBox<Cursuri> choiceMaterie_ap;
    public Button butonAsignare_ap;
    public Text eroare_ap, succes1_ap, succes2_ap, succes3_ap;
    ArrayList<Profesor> listaProfesori;
    public TextField numarOre_ap;
    public void intializareCursuri_c(){
        choiceCurs_c.getItems().clear();
        choiceMaterie_ap.getItems().clear();
        ArrayList<Cursuri> listaCursuri = Conexiune.getConexiune().getCursuri();
        for (Cursuri c: listaCursuri) {
            choiceCurs_c.getItems().add(c);
            choiceMaterie_ap.getItems().add(c);
        }
    }
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
    private void initializareListaProfesori_ac() {
        choiceProfesor_ap.getItems().clear();
        for (Profesor p: listaProfesori) {
            choiceProfesor_ap.getItems().add(p);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaProfesori = Conexiune.getConexiune().getListaProfesori();
        initializareListaProfesori_ac();
        eroare_ap.setText("");
        succes1_ap.setText("");
        succes2_ap.setText("");
        succes3_ap.setText("");
        listaCursuri = Conexiune.getConexiune().getListaCursuri();
        listaStudentiLaCursuri = Conexiune.getConexiune().getListaStudentiLaCurs();
        intializareCursuri_c();
        eroareCampuriNecompletate_a.setText("");
        eroareTipInvalid_a.setText("");
        eroareLipsaRol_a.setText("");
        textUserNeselectat_v.setText("");
        succesAdaugare_a.setText("");
        date_personale.toFront();
        welcome.setText("Bun venit:\n\t" + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume() + "!");
        setText();
        setTheUsers();
        adaugareRoluriInMeniu();
        mesajEroareAlegereRol_v.setText("");
        paneStudenti_a.toBack();
        paneProfesori_a.toBack();
        setareDepartamente();
        setareAniDeStudiu();
        setareStatusActiv();
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
            if (!rol.equals("Orice rol")) {
                choiceRol_a.getItems().add(rol);
            }
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
    private void setareDepartamente() {
        choiceDepartament_a.getItems().clear();
        ArrayList<Departamente> departamente = Conexiune.getConexiune().getDepartamente();
        for (int i = 0; i < departamente.size(); i++) {
            choiceDepartament_a.getItems().add(departamente.get(i).toString());
        }
    }
    private void setareAniDeStudiu() {
        choiceAnStudiu_a.getItems().clear();
        choiceAnStudiu_a.getItems().add("I");
        choiceAnStudiu_a.getItems().add("II");
        choiceAnStudiu_a.getItems().add("III");
        choiceAnStudiu_a.getItems().add("IV");
    }
    private void setareStatusActiv() {
        choiceStatus_a.getItems().clear();
        ArrayList<Status> status = Conexiune.getConexiune().getStatus();
        for (int i = 0; i < status.size(); i++) {
            choiceStatus_a.getItems().add(status.get(i).toString());
        }
    }
    public void adaugareUser() {
        if (choiceRol_a.getValue() != null) {
            succesAdaugare_a.setText("");
            eroareLipsaRol_a.setText("");
            eroareTipInvalid_a.setText("");
            eroareCampuriNecompletate_a.setText("");
            boolean campuriCompletate = tfieldNume_a.getText().equals("") || tfieldPrenume_a.getText().equals("") || tfieldCNP_a.getText().equals("") ||
                    tfieldIBAN_a.getText().equals("") || tfieldNrTel_a.getText().equals("") || tfieldEmail_a.getText().equals("") ||
                    tfieldAdresa_a.getText().equals("") || tfieldNrContract_a.getText().equals("");
            boolean campuriDateIncorecte = !isNumber(tfieldNrTel_a.getText());
            if (choiceRol_a.getValue().equals("Student")) {
                campuriCompletate = campuriCompletate || tfieldNrOreStud_a.getText().equals("");
                campuriDateIncorecte = campuriDateIncorecte || !isNumber(tfieldNrOreStud_a.getText());
            } else if (choiceRol_a.getValue().equals("Profesor")) {
                campuriCompletate = campuriCompletate || tfieldNrMaxOre_a.getText().equals("") || tfieldNrMinOre_a.getText().equals("");
                campuriDateIncorecte = campuriDateIncorecte || !isNumber(tfieldNrMaxOre_a.getText()) || !isNumber(tfieldNrMinOre_a.getText());
            }
            if (campuriCompletate || campuriDateIncorecte) {
                if (campuriCompletate) {
                    System.out.println("field-uri necompletate");
                    eroareCampuriNecompletate_a.setText("*Te rog sa completezi toate campurile, altfel nu se va adauga nimic!");
                }
                if (campuriDateIncorecte) {
                    System.out.println("Nu s-au folosit numere");
                    eroareTipInvalid_a.setText("*Te rog sa folosesti numere unde este cazul!");
                }
            } else {
                if (choiceRol_a.getValue().equals("Administrator")) {
                    String apelareInserareAdmin = new String("");
                    apelareInserareAdmin += "CALL `aplicatie`.`pInsertAdmin`(2, '" + tfieldCNP_a.getText() + "', '" + tfieldNume_a.getText() + "', '" +
                            tfieldPrenume_a.getText() + "', '" + tfieldAdresa_a.getText() + "', '" + tfieldNrTel_a.getText() + "', '" + tfieldEmail_a.getText() + "', '" + tfieldIBAN_a.getText().toUpperCase() + "', " + tfieldNrContract_a.getText() + ");";
                    System.out.println(apelareInserareAdmin);
                    if (Conexiune.getConexiune().executareQuery(apelareInserareAdmin)) {
                        succesAdaugare_a.setText("Un nou administrator a fost adaugat cu succes!");
                    }
                } else if (choiceRol_a.getValue().equals("Student") && choiceStatus_a.getValue() != null && choiceAnStudiu_a.getValue() != null) {
                    String apelareInserareStudent = new String("");
                    apelareInserareStudent += "CALL `aplicatie`.`pInsertStudent`(4, '" + tfieldCNP_a.getText() + "', '" + tfieldNume_a.getText() + "', '" +
                            tfieldPrenume_a.getText() + "', '" + tfieldAdresa_a.getText() + "', '" + tfieldNrTel_a.getText() + "', '" + tfieldEmail_a.getText() + "', '" + tfieldIBAN_a.getText().toUpperCase() + "', " + tfieldNrContract_a.getText() + ", " + tfieldNrOreStud_a.getText() + ", " + romanToDecimal(choiceAnStudiu_a.getValue()) + ", " + matchStatus() + ");";
                    System.out.println(apelareInserareStudent);
                    if (Conexiune.getConexiune().executareQuery(apelareInserareStudent)) {
                        succesAdaugare_a.setText("Student adaugat cu succes!");
                    }
                } else if (choiceRol_a.getValue().equals("Profesor") && choiceDepartament_a.getValue() != null) {
                    String apelareInserareProfesor = new String("");
                    apelareInserareProfesor += "CALL `aplicatie`.`pInsertProfesor`(3, '" + tfieldCNP_a.getText() + "', '" + tfieldNume_a.getText() + "', '" +
                            tfieldPrenume_a.getText() + "', '" + tfieldAdresa_a.getText() + "', '" + tfieldNrTel_a.getText() + "', '" + tfieldEmail_a.getText() + "', '" + tfieldIBAN_a.getText().toUpperCase() + "', " + tfieldNrContract_a.getText() + ", " + tfieldNrMinOre_a.getText() + ", " + tfieldNrMaxOre_a.getText() + ", " + matchDepartament() + ");";
                    System.out.println(apelareInserareProfesor);
                    if (Conexiune.getConexiune().executareQuery(apelareInserareProfesor)) {
                        succesAdaugare_a.setText("Profesor adaugat cu succes!");
                    }
                }
            }
        } else {
            eroareLipsaRol_a.setText("*Te rog sa alegi un rol pentru viitorul utilizator!");
            succesAdaugare_a.setText("");
        }
    }
    private boolean isNumber(String s) {
        try {
            int result = Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public int romanToDecimal(String s) {
        if (s.equalsIgnoreCase("I")) {
            return 1;
        } else if (s.equalsIgnoreCase("II")){
            return 2;
        } else if (s.equalsIgnoreCase("III")) {
            return 3;
        } else if (s.equalsIgnoreCase("IV")) {
            return 4;
        }
        return 0;
    }
    private int matchStatus() {
        ArrayList<Status> listaStatus = Conexiune.getConexiune().getStatus();
        for (Status s: listaStatus) {
            if (choiceStatus_a.getValue().equalsIgnoreCase(s.getDescriereStatus())) {
                return s.getIdStatus();
            }
        }
        return 0;
    }
    private int matchDepartament() {
        ArrayList<Departamente> listaDepartamente = Conexiune.getConexiune().getDepartamente();
        for (Departamente departamente: listaDepartamente) {
            if (choiceDepartament_a.getValue().equals(departamente.toString())) {
                return departamente.getIdDepartament();
            }
        }
        return 0;
    }
    public void cautareCursuri_c(){
        listViewCursuri_c.getItems().clear();
        listViewStudenti_c.getItems().clear();
        ArrayList<Integer> listaIdCA = new ArrayList<Integer>();
        for (VedereCurs c: listaCursuri) {
            if (c.getIdCurs() == choiceCurs_c.getValue().getIdCurs()) {
                listViewCursuri_c.getItems().add(c.toString());
                listaIdCA.add(c.getIdCursActivitate());
                listViewStudenti_c.getItems().add(c.toString());
                for (VedereStudentiLaCurs vsc: listaStudentiLaCursuri) {
                    if (vsc.getIdCursActivitate() == c.getIdCursActivitate()) {
                        listViewStudenti_c.getItems().add("\t  |->" + vsc.toString());
                    }
                }
            }
        }
    }
    public void toFrontCautareCursuri() {
        panouCautareCursuri_c.toFront();
    }
    public Pane asignareProfesor_ac;
    public void toFrontAsignareProfesor() {
        asignareProfesor_ac.toFront();
    }
    public void asignareProfesoriLaCurs() {
        succes1_ap.setText("");
        succes2_ap.setText("");
        succes3_ap.setText("");
        eroare_ap.setText("");
        if (choiceProfesor_ap.getValue() == null || choiceMaterie_ap.getValue() == null || !isNumber(numarOre_ap.getText())) {
            eroare_ap.setText("*Te rog sa selectezi atat un profesor cat si un curs!");
        } else {
            if (!Conexiune.getConexiune().existaProfesorAsignat(choiceProfesor_ap.getValue().getIdUser(), choiceMaterie_ap.getValue().getIdCurs())) {
                Conexiune.getConexiune().executareQuery("INSERT INTO curs_activitati (id_curs, id_activ, id_prof_titular, durata) VALUES (" + choiceMaterie_ap.getValue().getIdCurs() +", 1, " + choiceProfesor_ap.getValue().getIdUser() + ", " + numarOre_ap.getText() +")");
                succes1_ap.setText("*Profesorul X a fost asignat la cursul Y!");
                succes2_ap.setText("*Ramane la latitudinea sa daca va avea nevoie de seminare si laboratoare!");
                succes3_ap.setText("*De asemenea, tot el va stabili procentele, profesori delegati si momentul la care se tin acestea!");
                listaCursuri = Conexiune.getConexiune().getListaCursuri();
            } else {
                succes3_ap.setText("Profesorul pe care vreti sa il asignati la cursul de " + choiceMaterie_ap.getValue().getDenumireCurs() + " deja este asignat la acest curs!");
            }
        }
    }
}