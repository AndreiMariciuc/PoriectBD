package student;

import bazaDate.ActivitateStudent;
import bazaDate.Conexiune;
import bazaDate.Student;
import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControlStudent implements Initializable {
    //Main Menu
    public Button datePersonaleButton, orarButton, carnetButton, inscriereCursuriButton, grupuriButton, deautentificareButton;
    public Pane datePersonalePane, orarPane, carnetPane, inscriereCursuriPane, grupuriPane;

    //date personale Pane
    public Label welcome;
    public Text welcomeText, CNP, nume, prenume, adresa, nrTel, email, IBAN, nrContract, username, parola, tAnStudiu, anStudiu;
    public Text tCNP, tNume, tPrenume, tAdresa, tNrTel, tEmail, tIBAN, tNrContract, tUsername, tParola, statusField, tstatusField, mesajSuccesInscriere, mesajSuccesRenuntare;
    //inscrieri cursuri
    public ComboBox<String> cursuriDisponibile;
    public Text profesorTitular;
    public ComboBox<String> boxSeminar, boxCursuri, boxLaborator;
    public Button renuntaCursButton;
    public ComboBox<String> cursurileStudentului;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /** init DATE PERSONALE **/
        initDatePersonale();
        initInscrieriCursuri();
    }

    private void initInscrieriCursuri() {
        //lista cu toate cursurile disponibile!
        ((Student) (Conexiune.getUser())).setDenumireCursuri(Conexiune.getConexiune().getCursuriDisponibile(Conexiune.getUser().getIdUser()));
        cursuriDisponibile.getItems().setAll(((Student) Conexiune.getUser()).getDenumireCursuri());
        cursurileStudentului.getItems().setAll((Conexiune.getConexiune()).getDenumireCursuriInscris(Conexiune.getUser().getIdUser()));
    }

    private void initDatePersonale() {
        welcome.setText("Bun venit, \n" + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume());
        welcomeText.setText("\t\tBun venit, " + Conexiune.getUser().getNume() + " " + Conexiune.getUser().getPrenume()
                + "!\nAcestea sunt datele dumneavoastra personale!");
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
        parola.setText(Conexiune.getUser().getParola());
        tParola.setText("Parola");
        if (((Student) Conexiune.getUser()).getIdStatus() == 1)
            statusField.setText("BUGET");
        else
            statusField.setText("TAXA");
        tstatusField.setText("Status");
        tAnStudiu.setText("Anul de studiu");
        anStudiu.setText(((Student) Conexiune.getUser()).getAnStudiu());
    }

    public void schimbaStari(ActionEvent actionEvent) throws IOException {
        //de facut fiecare pane dupa preferinte si necesitati!
        if (actionEvent.getSource() == datePersonaleButton) {
            datePersonalePane.toFront();
        } else if (actionEvent.getSource() == orarButton) {
            orarPane.toFront();
        } else if (actionEvent.getSource() == carnetButton) {
            carnetPane.toFront();
        } else if (actionEvent.getSource() == inscriereCursuriButton) {
            inscriereCursuriPane.toFront();
        } else if (actionEvent.getSource() == grupuriButton) {
            grupuriPane.toFront();
        } else if (actionEvent.getSource() == deautentificareButton) {
            Parent root = FXMLLoader.load(getClass().getResource("../autentificare/autentificare.fxml"));
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT / 1.5f));
            currentStage.show();
        }
    }

    public void seAlegeCursul(ActionEvent actionEvent) {
        //System.out.println(cursuriDisponibile.getValue());
        String profTitular = Conexiune.getConexiune().getProfesorTitular(cursuriDisponibile.getValue());
        if (profTitular != null) {
            profesorTitular.setText(profTitular);
            //System.out.println(((Student) Conexiune.getUser()).getIdProfTitular());
            ArrayList<String> listActiv = Conexiune.getConexiune().getActivitatiDisponibile(((Student) (Conexiune.getUser())).getIdProfTitular(), cursuriDisponibile.getValue(), Conexiune.getUser().getIdUser(), 1);
            if (listActiv != null)
                boxCursuri.getItems().setAll(listActiv);
            else {
                System.out.println("nu poti tine cursul!");
            }

            listActiv = Conexiune.getConexiune().getActivitatiDisponibile(((Student) (Conexiune.getUser())).getIdProfTitular(), cursuriDisponibile.getValue(), Conexiune.getUser().getIdUser(), 2);

            if (listActiv != null)
                boxSeminar.getItems().setAll(listActiv);
            else {
                System.out.println("nu poti tine seminarul");
            }

            listActiv = Conexiune.getConexiune().getActivitatiDisponibile(((Student) (Conexiune.getUser())).getIdProfTitular(), cursuriDisponibile.getValue(), Conexiune.getUser().getIdUser(), 3);

            if (listActiv != null)
                boxLaborator.getItems().setAll(listActiv);
            else {
                System.out.println("nu poti tine labul!");
            }

        } else {
            profesorTitular.setText("EROARE, NU GASIM NICIUN PROFESOR DISPONIBIL!");
            initFail();
            mesajSuccesInscriere.setText("");
        }
    }

    private void initFail() {
        boxLaborator.getItems().setAll(new ArrayList<String>());
        boxSeminar.getItems().setAll(new ArrayList<String>());
        boxCursuri.getItems().setAll(new ArrayList<String>());
        ((Student) Conexiune.getUser()).clearMap();
    }

    public void inscriereCurs(ActionEvent actionEvent) {
        if (profesorTitular != null) {
            int idStud = ((Student) Conexiune.getUser()).getIdUser();
            Integer activ1, activ2, activ3;
            activ1 = ((Student) Conexiune.getUser()).getActivitatiCurente().get(boxCursuri.getValue());
            activ2 = ((Student) Conexiune.getUser()).getActivitatiCurente().get(boxSeminar.getValue());
            activ3 = ((Student) Conexiune.getUser()).getActivitatiCurente().get(boxLaborator.getValue());
            if (activ1 != null) {
                Conexiune.getConexiune().executeSQL("call insertActivitateStudent("+ idStud + ", "+ activ1 + ");");

                if (activ2 != null) {
                    Conexiune.getConexiune().executeSQL("call insertActivitateStudent("+ idStud + ", " + activ2 + ");");
                }

                if (activ3 != null) {
                    Conexiune.getConexiune().executeSQL("call insertActivitateStudent("+ idStud + ", " + activ3 + ");");
                }

                mesajSuccesInscriere.setText("Te-ai inscris cu succes!");
                //reimprospatez conexiunile
                initInscrieriCursuri();
                initFail();
            } else {
                mesajSuccesInscriere.setText("O problema cu tehnica, revino mai tarziu, ne cerem scuze!");
            }
        } else {
            mesajSuccesInscriere.setText("Din pacate nu te poti inscrie, verifica fisa de inscriere!");
        }
    }

    public void renuntaCurs(ActionEvent actionEvent) {
        String descriere = cursurileStudentului.getValue();
        if(descriere != null) {
            Conexiune.getConexiune().executeSQL("call renuntaCurs(" + Conexiune.getUser().getIdUser() + ",'" + cursurileStudentului.getValue() + "');");
            mesajSuccesRenuntare.setText("Ai renuntat la cursul " + descriere);
            initInscrieriCursuri();
        } else
            mesajSuccesRenuntare.setText("Nu ai selectat nimic!");
    }
}
