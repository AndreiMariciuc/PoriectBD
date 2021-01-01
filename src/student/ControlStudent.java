package student;

import bazaDate.*;
import constante.Ecran;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Date;
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
    //orar
    public TableView<Calendar> tabelToateActivitatile;
    public TableColumn<Calendar, String> coloanaDisciplinaToate;
    public TableColumn<Calendar, String> coloanaActivitateToate;
    public TableColumn<Calendar, String> coloanaPerioadaToate;
    public TableColumn<Calendar, String> coloanaZiToate;
    public TableColumn<Calendar, Integer> coloanaOraToate;
    public TableColumn<Calendar, Integer> coloanaDurataToate;
    public TableColumn<Calendar, String> colanaProfesorToate;

    public TableView<Calendar> tabelAziActivitatile;
    public TableColumn<Calendar, String> coloanaDisciplinaAzi;
    public TableColumn<Calendar, String> coloanaActivitateAzi;
    public TableColumn<Calendar, String> coloanaPerioadaAzi;
    public TableColumn<Calendar, String> coloanaZiAzi;
    public TableColumn<Calendar, Integer> coloanaOraAzi;
    public TableColumn<Calendar, Integer> coloanaDurataAzi;
    public TableColumn<Calendar, String> colanaProfesorAzi;
    public Button bsaveToate, bsaveAzi;
    // carnet
    public TableView<Carnet> notePartial;
    public TableColumn<Carnet, String> coloanaDisciplinaPartial;
    public TableColumn<Carnet, String> coloanaActivitatePartial;
    public TableColumn<Carnet, String> coloanaProfesorPartial;
    public TableColumn<Carnet, Date> coloanadDataPartial;
    public TableColumn<Carnet, String> coloanaNotaPartial;
    public TableColumn<Carnet, Integer> coloanaProcentPartial;

    public TableView<Carnet> noteFinal;
    public TableColumn<Carnet, String> coloanaDisciplinaFinal;
    public TableColumn<Carnet, String> coloanaProfesorFinal;
    public TableColumn<Carnet, String> coloanaNotaFinal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /** init DATE PERSONALE **/
        initDatePersonale();
        initInscrieriCursuri();
        initCalendar();
        initCarnet();
        refresh();
    }

    private void initCarnet() {
        coloanaDisciplinaPartial.setCellValueFactory(new PropertyValueFactory<Carnet, String>("disciplina"));
        coloanaActivitatePartial.setCellValueFactory(new PropertyValueFactory<Carnet, String>("activitate"));
        coloanaProfesorPartial.setCellValueFactory(new PropertyValueFactory<Carnet, String>("numeProfesor"));
        coloanadDataPartial.setCellValueFactory(new PropertyValueFactory<Carnet, Date>("data"));
        coloanaNotaPartial.setCellValueFactory(new PropertyValueFactory<Carnet, String>("nota"));
        coloanaProcentPartial.setCellValueFactory(new PropertyValueFactory<Carnet, Integer>("procent"));

        coloanaDisciplinaFinal.setCellValueFactory(new PropertyValueFactory<Carnet, String>("disciplina"));
        coloanaProfesorFinal.setCellValueFactory(new PropertyValueFactory<Carnet, String>("numeProfesor"));
        coloanaNotaFinal.setCellValueFactory(new PropertyValueFactory<Carnet, String>("nota"));

        refreshCarnet();
    }

    private void refreshCarnet() {
        Conexiune.getConexiune().carnet();
        noteFinal.getItems().setAll(((Student) Conexiune.getUser()).getNoteFinale());
        notePartial.getItems().setAll(((Student) Conexiune.getUser()).getNotePartiale());
    }

    private void initCalendar() {
        coloanaDisciplinaToate.setCellValueFactory(new PropertyValueFactory<Calendar, String>("disciplina"));
        coloanaActivitateToate.setCellValueFactory(new PropertyValueFactory<Calendar, String>("activitate"));
        coloanaPerioadaToate.setCellValueFactory(new PropertyValueFactory<Calendar, String>("perioada"));
        coloanaZiToate.setCellValueFactory(new PropertyValueFactory<Calendar, String>("zi"));
        coloanaOraToate.setCellValueFactory(new PropertyValueFactory<Calendar, Integer>("ora"));
        coloanaDurataToate.setCellValueFactory(new PropertyValueFactory<Calendar, Integer>("durata"));
        colanaProfesorToate.setCellValueFactory(new PropertyValueFactory<Calendar, String>("numeProfesor"));

        coloanaDisciplinaAzi.setCellValueFactory(new PropertyValueFactory<Calendar, String>("disciplina"));
        coloanaActivitateAzi.setCellValueFactory(new PropertyValueFactory<Calendar, String>("activitate"));
        coloanaPerioadaAzi.setCellValueFactory(new PropertyValueFactory<Calendar, String>("perioada"));
        coloanaZiAzi.setCellValueFactory(new PropertyValueFactory<Calendar, String>("zi"));
        coloanaOraAzi.setCellValueFactory(new PropertyValueFactory<Calendar, Integer>("ora"));
        coloanaDurataAzi.setCellValueFactory(new PropertyValueFactory<Calendar, Integer>("durata"));
        colanaProfesorAzi.setCellValueFactory(new PropertyValueFactory<Calendar, String>("numeProfesor"));

        refreshCalendar();
    }

    private void refreshCalendar() {
        Conexiune.getConexiune().calendar();
        tabelToateActivitatile.getItems().setAll(((Student) Conexiune.getUser()).getToateActivitati());
        tabelAziActivitatile.getItems().setAll(((Student) Conexiune.getUser()).getAziActivitati());
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
                if (activ3 != null)
                    Conexiune.getConexiune().executeSQL("call insertActivitateStudent(" + idStud + ", " + activ3 + ");");
                else if (boxLaborator.getItems().size() > 0) {
                    mesajSuccesInscriere.setText("Alege un lab!");
                    return;
                }

                if (activ2 != null)
                    Conexiune.getConexiune().executeSQL("call insertActivitateStudent(" + idStud + ", " + activ2 + ");");
                else if (boxSeminar.getItems().size() > 0) {
                    mesajSuccesInscriere.setText("Alege un seminar!");
                    return;
                }

                Conexiune.getConexiune().executeSQL("call insertActivitateStudent(" + idStud + ", " + activ1 + ");");
                mesajSuccesInscriere.setText("Te-ai inscris cu succes!");
                //reimprospatez conexiunile
                initInscrieriCursuri();
                initFail();
                refreshCalendar();
                refreshCarnet();
            } else
                mesajSuccesInscriere.setText("Nu ai selectat cursul!");
        } else
            mesajSuccesInscriere.setText("Nu gasim profesor disponibil, revio mai tarziu!!");
    }

    public void renuntaCurs(ActionEvent actionEvent) {
        String descriere = cursurileStudentului.getValue();
        if (descriere != null) {
            Conexiune.getConexiune().executeSQL("call renuntaCurs(" + Conexiune.getUser().getIdUser() + ",'" + cursurileStudentului.getValue() + "');");
            mesajSuccesRenuntare.setText("Ai renuntat la cursul " + descriere);
            initInscrieriCursuri();
            refreshCalendar();
            refreshCarnet();
        } else
            mesajSuccesRenuntare.setText("Nu ai selectat nimic!");
    }

    private FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        return fileChooser;
    }

    private void saveFile(String result, File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(result);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("save file!");
        }
    }

    public void saveListe(ActionEvent e) {
        if (bsaveToate == e.getSource()) {
            Stage s = (Stage) (((Node) (e.getSource())).getScene().getWindow());
            File file = getFileChooser().showOpenDialog(s);
            if (file != null) {
                String result = "";
                for (Calendar c : ((Student) Conexiune.getUser()).getToateActivitati())
                    result += c + "\n";
                saveFile(result, file);
            } else
                System.out.println("nu am fisier!");

        } else if (bsaveAzi == e.getSource()) {
            Stage s = (Stage) (((Node) (e.getSource())).getScene().getWindow());
            File file = getFileChooser().showOpenDialog(s);
            if (file != null) {
                String result = "";
                for (Calendar c : ((Student) Conexiune.getUser()).getAziActivitati())
                    result += c + "\n";
                saveFile(result, file);
            } else
                System.out.println("nu am fisier");
        }
    }
    
    /*
        Grupe
     */
    public ChoiceBox<Grupe> choiceBoxGrupur_vg, choiceBoxGrupuri_tmg, choiceBoxGrupuri_ag;
    public ChoiceBox<Mesaj> choiceBoxMesaje_vg;
    public TextArea textAreaGrupuri_vg;
    public void incarcareGrupe() {
        choiceBoxGrupur_vg.getItems().clear();
        choiceBoxGrupuri_tmg.getItems().clear();
        choiceBoxGrupuri_ag.getItems().clear();
        grupeLaCareEste_irg.getItems().clear();
        ArrayList<Grupe> grupe = Conexiune.getConexiune().getGrupeStudent();
        for (Grupe grupa: grupe) {
            choiceBoxGrupur_vg.getItems().add(grupa);
            choiceBoxGrupuri_ag.getItems().add(grupa);
            choiceBoxGrupuri_tmg.getItems().add(grupa);
            grupeLaCareEste_irg.getItems().add(grupa);
        }
    }

    public void inserareMesaje() {
        if (choiceBoxGrupur_vg.getValue() != null) {
            choiceBoxMesaje_vg.getItems().clear();
            ArrayList<Mesaj> mesaje = Conexiune.getConexiune().getMesaje(choiceBoxGrupur_vg.getValue().getIdGrupa());
            for (Mesaj mesaj: mesaje) {
                choiceBoxMesaje_vg.getItems().add(mesaj);
            }
            butonParticipareActivitate_vg.setDisable(true);
        }
    }
    public Button butonParticipareActivitate_vg;
    public void vizualizareMesaj() {
        if (choiceBoxMesaje_vg.getValue() != null){
            textAreaGrupuri_vg.setText(choiceBoxMesaje_vg.getValue().getWholeMsg());
            if (choiceBoxMesaje_vg.getValue().getProgramare() != 0) {
                butonParticipareActivitate_vg.setDisable(false);
            } else {
                butonParticipareActivitate_vg.setDisable(true);
            }
        }
    }
    public void incaUnul() {
        Conexiune.getConexiune().participare(choiceBoxMesaje_vg.getValue().getProgramare());
        butonParticipareActivitate_vg.setDisable(true);
    }

    public TextField textFieldSubiect_tmg;
    public TextArea textFieldMesaj_tmg;
    public Text textSucces_tms;
    public void trimiteMesaj() {
        if (choiceBoxGrupuri_tmg.getValue() != null) {
            String subiect = textFieldSubiect_tmg.getText();
            if (subiect.length() >= 256) {
                subiect = subiect.substring(0, 255);
            }
            //(int pidGrupa, String subiect, String continutMesaj, int programare)
            Conexiune.getConexiune().sendMessage(choiceBoxGrupuri_tmg.getValue().getIdGrupa(), subiect, textFieldMesaj_tmg.getText(), 0);
            renuntareTrimitereMesaj();
            textSucces_tms.setText("*Mesaj Trimis cu succes!");
        }
    }

    public void renuntareTrimitereMesaj() {
        textFieldSubiect_tmg.setText("");
        textFieldMesaj_tmg.setText("");
        textSucces_tms.setText("*Mesajul a fost anulat!");
    }

    public ChoiceBox<Cursuri> choiceBoxMaterii_cg;
    public Text textSuccesCreareGrupa;

    private void adaugareCursuri() {
        textSuccesCreareGrupa.setText("");
        ArrayList<Cursuri> cursuri = Conexiune.getConexiune().getCursuri();
        choiceBoxMaterii_cg.getItems().clear();
        for (Cursuri curs: cursuri) {
            choiceBoxMaterii_cg.getItems().add(curs);
        }
    }

    public void creareGrupa() {
        if (choiceBoxMaterii_cg.getValue() != null) {
            Conexiune.getConexiune().creareGrupa(choiceBoxMaterii_cg.getValue().getId());
            textSuccesCreareGrupa.setText("Grupa creata cu succes pentru materia " + choiceBoxMaterii_cg.getValue().getDescriere() + "!");
        }
    }

    public ChoiceBox<Grupe> choiceBoxGrupuriBune_ig;
    public void adaugareGrupeBune() {
        textSuccesInscriereGrupa_ig.setText("");
        choiceBoxGrupuriBune_ig.getItems().clear();
        ArrayList<Grupe> grupe = Conexiune.getConexiune().getGrupeLaCareNuE();
        System.out.println(grupe);
        for (Grupe grupa: grupe) {
            choiceBoxGrupuriBune_ig.getItems().add(grupa);
        }
    }

    public Text textSuccesInscriereGrupa_ig;
    public void inscriereInGrupa() {
        if (choiceBoxGrupuriBune_ig.getValue() != null) {
            if (Conexiune.getConexiune().inscriereInGrupa(choiceBoxGrupuriBune_ig.getValue().getIdGrupa())) {
                textSuccesInscriereGrupa_ig.fillProperty().setValue(Paint.valueOf("#00AA00"));
                textSuccesInscriereGrupa_ig.setText("*Felicitari te-ai inscris in grupa: " + choiceBoxGrupuriBune_ig.getValue());
            } else {
                textSuccesInscriereGrupa_ig.fillProperty().setValue(Paint.valueOf("#AA0000"));
                textSuccesInscriereGrupa_ig.setText("Din pacate nu te-ai putut inscrie! Posibil sa fie prea multi studenti in aceasta grupa!");
            }
            refresh();
        }
    }

    public ChoiceBox<Grupe> grupeLaCareEste_irg;
    public Text textSuccesRenuntareGrupa_irg;

    public void refresh() {
        incarcareGrupe();
        adaugareGrupeBune();
        textSuccesInscriereGrupa_ig.setText("");
        textSuccesRenuntareGrupa_irg.setText("");
        incarcareProfesori();
        mesajCorectitudine_ig.setText("");
        incarcareGrupeAdministrate();
        textFieldNrMaxPart_ig.setText("");
        incarcareGrupe();
        textSucces_tms.setText("");
        adaugareCursuri();
        adaugareGrupeBune();
        adaugareGrupeBune();
        textOperatieSucces_sga.setText("");
    }

    public void renuntareGrupa() {
        if (grupeLaCareEste_irg.getValue() != null) {
            String despreGrupa = grupeLaCareEste_irg.getValue().toString();
            Conexiune.getConexiune().renuntareLaGrupa(grupeLaCareEste_irg.getValue().getIdGrupa());
            textSuccesRenuntareGrupa_irg.setText("Ai renuntat la grupa " + despreGrupa);
            refresh();
        }
    }

    public ChoiceBox<Profesor> choiceBoxProfesori_ag;
    public ChoiceBox<Grupe> choiceBoxGrupeAdministrate_ag;
    public Text mesajCorectitudine_ig;
    public TextField textFieldNrMaxPart_ig;

    private void incarcareProfesori() {
        choiceBoxProfesori_ag.getItems().clear();
        ArrayList<Profesor> profesori = Conexiune.getConexiune().getListaProfesori();
        for(Profesor profesor: profesori) {
            choiceBoxProfesori_ag.getItems().add(profesor);
        }
    }

    private void incarcareGrupeAdministrate() {
        ArrayList<Grupe> grupe = Conexiune.getConexiune().getGrupeLaCareEAdmin();
        System.out.println(grupe);
        choiceBoxGrupeAdministrate_ag.getItems().clear();
        for(Grupe grupa: grupe) {
            choiceBoxGrupeAdministrate_ag.getItems().add(grupa);
        }
    }

    public void editareGrupa() {
        boolean ok = false;
        boolean nuAFostNumar = true;
        if (isNumber(textFieldNrMaxPart_ig.getText())) {
            Conexiune.getConexiune().updateNrMaxPart(choiceBoxGrupeAdministrate_ag.getValue().getIdGrupa(), Integer.parseInt(textFieldNrMaxPart_ig.getText()));
            ok = true;
        } else if (textFieldNrMaxPart_ig.getText().compareTo("") != 0) {
            nuAFostNumar = false;
        }
        if (nuAFostNumar == true && choiceBoxProfesori_ag.getValue() != null) {
            Conexiune.getConexiune().updateProfesorGrupa(choiceBoxGrupeAdministrate_ag.getValue().getIdGrupa(), choiceBoxProfesori_ag.getValue().getIdUser());
            ok = true;
        }
        refresh();
        if (ok == true) {
            mesajCorectitudine_ig.fillProperty().setValue(Paint.valueOf("#11AA11"));
            mesajCorectitudine_ig.setText("Modificarile au fost facute!");
        } else {
            if (nuAFostNumar == false) {
                mesajCorectitudine_ig.fillProperty().setValue(Paint.valueOf("#FF0000"));
                mesajCorectitudine_ig.setText("Te rog sa introduci un numar valid!!");
            } else {
                mesajCorectitudine_ig.setText("");
            }
        }
    }

    public boolean isNumber(String n) {
        try{
            int nr = Integer.parseInt(n);
            if (nr > 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public DatePicker datePickerZiua_sag;

    public ChoiceBox<Ore> choiceBoxOre_sag;

    public void incarcareOre() {
        if (choiceBoxGrupuri_ag.getValue() != null &&  datePickerZiua_sag.getValue() != null) {
            choiceBoxOre_sag.getItems().clear();
            System.out.println(datePickerZiua_sag.getValue().getDayOfWeek().getValue());
            ArrayList<Ore> ore = Conexiune.getConexiune().getOreRecomandate(choiceBoxGrupuri_ag.getValue().getIdGrupa(), datePickerZiua_sag.getValue().getDayOfWeek().getValue());
            for (Ore ora : ore) {
                choiceBoxOre_sag.getItems().add(ora);
            }
        }
    }

    public TextField textFieldNrMinParticipActivitate_ag, textFieldNrOreValabilitate_ag, textFieldDurata_sga;
    public Text textOperatieSucces_sga;
    public void rezervaActivitate() {
        if (choiceBoxGrupuri_ag.getValue() != null && datePickerZiua_sag.getValue() != null && choiceBoxOre_sag.getValue() != null && isNumber(textFieldNrMinParticipActivitate_ag.getText()) && isNumber(textFieldNrOreValabilitate_ag.getText()) && isNumber(textFieldDurata_sga.getText())) {
            // creareActivitateNouaDeGrupa(int pidGrupa, int nrMinPart, LocalDate dataActivitate, int oraActivitate, int durata, int deadline)
            Conexiune.getConexiune().creareActivitateNouaDeGrupa(choiceBoxGrupuri_ag.getValue().getIdGrupa(), Integer.parseInt(textFieldNrMinParticipActivitate_ag.getText()), datePickerZiua_sag.getValue(), choiceBoxOre_sag.getValue().getOra(), Integer.parseInt(textFieldDurata_sga.getText()), Integer.parseInt(textFieldNrOreValabilitate_ag.getText()));
            textOperatieSucces_sga.fillProperty().setValue(Paint.valueOf("#11CC11"));
            textOperatieSucces_sga.setText("Activitatea a fost adaugata!");
        } else {
            textOperatieSucces_sga.fillProperty().setValue(Paint.valueOf("#CC1111"));
            textOperatieSucces_sga.setText("Te rog sa completezi toate campurile aferet!");
        }
    }
}
