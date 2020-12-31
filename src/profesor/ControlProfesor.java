package profesor;

import bazaDate.Conexiune;
import bazaDate.User;
import constante.Ecran;
import curs.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static bazaDate.Functii.*;

public class ControlProfesor implements Initializable {
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
    public Label welcome;

    // Date personale
    public TextField cnpText;
    public TextField numeText;
    public TextField prenumeText;
    public TextField adresaText;
    public TextField nrTelefonText;
    public TextField emailText;
    public TextField ibanText;
    public TextField nrContractText;

    // Orar
    public TableView<Curs> activitatiCurs1;
    public TableColumn<Curs,String> disciplinaColumn1;
    public TableColumn<Curs,String> activitateColumn1;
    public TableColumn<Curs,String> perioadaColumn1;
    public TableColumn<Curs,String> ziSaptamanaColumn1;
    public TableColumn<Curs,Integer> oraInceputColumn1;
    public TableColumn<Curs,Integer> durataColumn1;
    public Button descarcareButon1;

    public TableView<Curs> activitatiCurs2;
    public TableColumn<Curs,String> disciplinaColumn2;
    public TableColumn<Curs,String> activitateColumn2;
    public TableColumn<Curs,String> perioadaColumn2;
    public TableColumn<Curs,String> ziSaptamanaColumn2;
    public TableColumn<Curs,Time> oraInceputColumn2;
    public TableColumn<Curs,Integer> durataColumn2;
    public Button descarcareButon2;

    public TableView<ActivitateGrupa> activitatiGrupe1;
    public TableColumn<ActivitateGrupa,Integer> grupaColumn1;
    public TableColumn<ActivitateGrupa,String> disciplinaGrupColumn1;
    public TableColumn<ActivitateGrupa,Date> dataColumn1;
    public TableColumn<ActivitateGrupa,Integer> oraColumn1;
    public TableColumn<ActivitateGrupa,Integer> durataActivColumn1;
    public Button descarcareButon3;

    public TableView<ActivitateGrupa> activitatiGrupe2;
    public TableColumn<ActivitateGrupa,Integer> grupaColumn2;
    public TableColumn<ActivitateGrupa,String> disciplinaGrupColumn2;
    public TableColumn<ActivitateGrupa,Date> dataColumn2;
    public TableColumn<ActivitateGrupa,Integer> oraColumn2;
    public TableColumn<ActivitateGrupa,Integer> durataActivColumn2;
    public Button descarcareButon4;

    public Button refresh;
    public Button descarcareButon6;
    public Button descarcareButon7;

    // Catalog
    public ChoiceBox<String> cursFilter;
    public ChoiceBox<Integer> anFilter;
    public TableView<Nota> catalog;
    public TableColumn<Nota,String> numeStudentColumn;
    public TableColumn<Nota,String> disciplinaNotaColumn;
    public TableColumn<Nota,Integer> seminarColumn;
    public TableColumn<Nota,Integer> laboratorColumn;
    public TableColumn<Nota,Integer> examenColumn;
    public TableColumn<Nota,Integer> notaColumn;
    public TableColumn<Nota,Integer> anColumn;
    public Button afisareNoteButton;
    public Button descarcareButon5;

    //Programare cursuri
    public ChoiceBox<String> cursFilter2;
    public Button selectCurs;
    public Tab seminarTab;
    public Tab laboratorTab;
    public Label eroarePondere;
    public Label eroareProgram;
    public Label programareReusita;
    public Label profInvalid;

    //CURS
    public ChoiceBox<String> perioadaFilter1;
    public Spinner<Integer> pondereSpinner1;
    public ChoiceBox<Integer> durataFilter1;
    public Spinner<Integer> maxParticipantiSpinner1;
    public ChoiceBox<String> ziFilter1;
    public Spinner<Integer> oraSpinner1;
    public CheckBox seminarCheckBox;
    public CheckBox laboratorCheckBox;
    public Button programeazaCurs;

    //SEMINAR
    public ChoiceBox<String> perioadaFilter2;
    public Spinner<Integer> pondereSpinner2;
    public ChoiceBox<Integer> durataFilter2;
    public Spinner<Integer> maxParticipantiSpinner2;
    public ChoiceBox<String> ziFilter2;
    public Spinner<Integer> oraSpinner2;
    public ChoiceBox<String> profDelegatFilter1;
    public TextField profDelegatField1;
    public Button programeazaSeminar;

    //LABORATOR
    public ChoiceBox<String> perioadaFilter3;
    public Spinner<Integer> pondereSpinner3;
    public ChoiceBox<Integer> durataFilter3;
    public Spinner<Integer> maxParticipantiSpinner3;
    public ChoiceBox<String> ziFilter3;
    public Spinner<Integer> oraSpinner3;
    public ChoiceBox<String> profDelegatFilter2;
    public TextField profDelegatField2;
    public Button programeazaLaborator;

    // Grupuri
    public ChoiceBox<String> cursFilter3;
    public Button cursButton;
    public ChoiceBox<Integer> grupFilter;
    public Button grupButton;

    // Programare examene
    public ChoiceBox<String> cursFilter4;
    public Button cursButton1;
    public ChoiceBox<String> tipExamenFilter;
    public DatePicker dataExamen;
    public Spinner<Integer> oraSpinner4;
    public Spinner<Integer> durataSpinner4;
    public ChoiceBox<String> profDelegatFilter3;
    public TextField profDelegatField3;
    public Button programeazaExamen;

    public Label eroareProgram1;
    public Label programareReusita1;
    public Label profInvalid1;
    public Label dataDepasita;

    public TableView<Examen> exameneAstazi;
    public TableColumn<Examen,String> disciplinaColumn3;
    public TableColumn<Examen,String> tipExamenColumn;
    public TableColumn<Examen,String> dataExamenColumn;
    public TableColumn<Examen,Integer> durataExamColumn;

    public TableView<Examen> exameneAll;
    public TableColumn<Examen,String> disciplinaColumn4;
    public TableColumn<Examen,String> tipExamenColumn1;
    public TableColumn<Examen,String> dataExamenColumn1;
    public TableColumn<Examen,Integer> durataExamColumn1;
    public ListView<String> chat;

    Connection conexiune = Conexiune.getConexiune().connection;
    User user = Conexiune.getConexiune().getUser();
    Calendar cal = Calendar.getInstance();
    Date data_inceput = null;
    Date data_sfarsit = null;

    public void schimbareStare(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == datePersonaleButton) {
            datePersonalePane.toFront();
            welcome.setText("Bun venit " + user.getNume() + " " + user.getPrenume() + "!");
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
            currentStage.setScene(new Scene(root, Ecran.WIDTH, (int) (Ecran.HEIGHT / 1.5)));
            currentStage.show();
        }
    }

    public void defaultChange() {

    }

    public void refresh() throws SQLException{
        disciplinaColumn1.setCellValueFactory(new PropertyValueFactory<>("nume"));
        activitateColumn1.setCellValueFactory(new PropertyValueFactory<>("activitate"));
        perioadaColumn1.setCellValueFactory(new PropertyValueFactory<>("perioada"));
        ziSaptamanaColumn1.setCellValueFactory(new PropertyValueFactory<>("zi_a_saptamanii"));
        oraInceputColumn1.setCellValueFactory(new PropertyValueFactory<>("ora_inceput"));
        durataColumn1.setCellValueFactory(new PropertyValueFactory<>("durata"));
        activitatiCurs1.setItems(FXCollections.observableArrayList());

        disciplinaColumn2.setCellValueFactory(new PropertyValueFactory<>("nume"));
        activitateColumn2.setCellValueFactory(new PropertyValueFactory<>("activitate"));
        perioadaColumn2.setCellValueFactory(new PropertyValueFactory<>("perioada"));
        ziSaptamanaColumn2.setCellValueFactory(new PropertyValueFactory<>("zi_a_saptamanii"));
        oraInceputColumn2.setCellValueFactory(new PropertyValueFactory<>("ora_inceput"));
        durataColumn2.setCellValueFactory(new PropertyValueFactory<>("durata"));
        activitatiCurs2.setItems(FXCollections.observableArrayList());

        cal.setTime(new Date());
        int ziCurenta = cal.get(Calendar.DAY_OF_WEEK)-1;
        int saptCurenta = cal.get(Calendar.WEEK_OF_YEAR);

        boolean gasit = false;
        PreparedStatement s = conexiune.prepareStatement("select * from an_universitar");
        ResultSet rs = s.executeQuery();
        while(rs.next() && !gasit){
            data_inceput = rs.getDate("data_inceput");
            data_sfarsit = rs.getDate("data_sfarsit");
            if (!data_inceput.after(new Date()) && !data_sfarsit.before(new Date()))
                gasit = true;
        }

        cal.setTime(data_inceput);
        int primaSapt = cal.get(Calendar.WEEK_OF_YEAR);
        int id_user = user.getIdUser();
        PreparedStatement s1 = conexiune.prepareStatement("select * from curs_activitati where "
                + "id_prof_titular = " + id_user + " or id_prof_delegat = " + id_user + " order by nr_zi_sapt, ora_inceput");
        ResultSet rs1 = s1.executeQuery();

        while (rs1.next()) {
            String numeCurs = idCursToDenumireCurs(rs1.getInt("id_curs"));
            String tipActivitate = idActivtoDenumireActiv(rs1.getInt("id_activ"));
            int id_perioada = rs1.getInt("perioada");
            String perioada = idPerioadaToPerioada(id_perioada);
            int nr_zi = rs1.getInt("nr_zi_sapt");
            String zi = idZiToZi(nr_zi);
            int ora_inceput = rs1.getInt("ora_inceput");
            int durata = rs1.getInt("durata");
            Curs curs = new Curs(numeCurs, tipActivitate, perioada, zi, ora_inceput, durata);
            int paritate=1-(52+saptCurenta - primaSapt)%2;
            if (nr_zi == ziCurenta && (id_perioada == 2 || id_perioada == paritate))
                activitatiCurs1.getItems().add(curs);
            activitatiCurs2.getItems().add(curs);
        }

        // Orar activitati grupe

        grupaColumn1.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        disciplinaGrupColumn1.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        dataColumn1.setCellValueFactory(new PropertyValueFactory<>("data"));
        oraColumn1.setCellValueFactory(new PropertyValueFactory<>("ora"));
        durataActivColumn1.setCellValueFactory(new PropertyValueFactory<>("durata"));
        activitatiGrupe1.setItems(FXCollections.observableArrayList());

        grupaColumn2.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        disciplinaGrupColumn2.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        dataColumn2.setCellValueFactory(new PropertyValueFactory<>("data"));
        oraColumn2.setCellValueFactory(new PropertyValueFactory<>("ora"));
        durataActivColumn2.setCellValueFactory(new PropertyValueFactory<>("durata"));
        activitatiGrupe2.setItems(FXCollections.observableArrayList());

        PreparedStatement s2 = conexiune.prepareStatement
                ("select * from programari_activitati_grupe where id_grupa in "
                        + "(select id_grupa from grupe where id_profesor_raspunzator = "+user.getIdUser()+")");
        ResultSet rs2 = s2.executeQuery();
        while(rs2.next()){
            int grupa = rs2.getInt("id_grupa");
            String numeCurs = idCursToDenumireCurs(rs2.getInt("id_curs"));
            Date data = rs2.getDate("data");
            int ora = rs2.getInt("ora");
            int durata = rs2.getInt("durata");
            ActivitateGrupa ag = new ActivitateGrupa(grupa,numeCurs,data,ora,durata);
            if (data.equals(new Date())) // data = data_curenta
                activitatiGrupe1.getItems().add(ag);
            activitatiGrupe2.getItems().add(ag);
        }

        // Orar examene
        disciplinaColumn3.setCellValueFactory(new PropertyValueFactory<>("curs"));
        tipExamenColumn.setCellValueFactory(new PropertyValueFactory<>("tip"));
        dataExamenColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        durataExamColumn.setCellValueFactory(new PropertyValueFactory<>("durata"));
        exameneAstazi.setItems(FXCollections.observableArrayList());

        PreparedStatement s3 = conexiune.prepareStatement
        ("select * from examinari where id_activ_curs in (select id_ca from curs_activitati where id_prof_titular = ? or id_prof_delegat = ?)" +
        " and cast(data_desfasurare as date) = curdate()");
        s3.setInt(1,user.getIdUser());
        s3.setInt(2,user.getIdUser());
        ResultSet rs3 = s3.executeQuery();
        while(rs3.next()){
            PreparedStatement s4 = conexiune.prepareStatement("select id_curs from curs_activitati where id_ca = ?");
            s4.setInt(1,rs3.getInt("id_activ_curs"));
            ResultSet rs4 = s4.executeQuery();
            String curs = "";
            while(rs4.next())
                curs = idCursToDenumireCurs(rs4.getInt("id_curs"));

            String tipExam = idActivtoDenumireActiv(rs3.getInt("id_tip_examinare"));
            String data = rs3.getString("data_desfasurare");
            int durata = rs3.getInt("durata");
            Examen e = new Examen(curs,tipExam,data,durata);
            exameneAstazi.getItems().add(e);
        }

        disciplinaColumn4.setCellValueFactory(new PropertyValueFactory<>("curs"));
        tipExamenColumn1.setCellValueFactory(new PropertyValueFactory<>("tip"));
        dataExamenColumn1.setCellValueFactory(new PropertyValueFactory<>("data"));
        durataExamColumn1.setCellValueFactory(new PropertyValueFactory<>("durata"));
        exameneAll.setItems(FXCollections.observableArrayList());

        PreparedStatement s5 = conexiune.prepareStatement
                ("select * from examinari where id_activ_curs in (select id_ca from curs_activitati where id_prof_titular = ? or id_prof_delegat = ?)");
        s5.setInt(1,user.getIdUser());
        s5.setInt(2,user.getIdUser());
        ResultSet rs5 = s5.executeQuery();
        while(rs5.next()){
            PreparedStatement s6 = conexiune.prepareStatement("select id_curs from curs_activitati where id_ca = ?");
            s6.setInt(1,rs5.getInt("id_activ_curs"));
            ResultSet rs6 = s6.executeQuery();
            String curs = "";
            while(rs6.next())
                curs = idCursToDenumireCurs(rs6.getInt("id_curs"));

            String tipExam = idActivtoDenumireActiv(rs5.getInt("id_tip_examinare"));
            String data = rs5.getString("data_desfasurare");
            int durata = rs5.getInt("durata");
            Examen e = new Examen(curs,tipExam,data,durata);
            exameneAll.getItems().add(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Date personale
        welcome.setText("Bun venit: \n" + user.getNume() + " " + user.getPrenume() + "!");
        cnpText.setText(user.getCNP());
        numeText.setText(user.getNume());
        prenumeText.setText(user.getPrenume());
        adresaText.setText(user.getAdresa());
        nrTelefonText.setText(user.getNrTelefon());
        emailText.setText(user.getEmail());
        ibanText.setText(user.getIBAN());
        nrContractText.setText(Integer.toString(user.getNrContract()));

        // Orar activitati didactice
        try {
            refresh();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Catalog

        cursFilter.setItems(FXCollections.observableArrayList());
        cursFilter2.setItems(FXCollections.observableArrayList());
        cursFilter3.setItems(FXCollections.observableArrayList());
        cursFilter4.setItems(FXCollections.observableArrayList());
        try {
            PreparedStatement s = conexiune.prepareStatement("select descriere from cursuri where id_curs in " +
                    "(select id_curs from curs_activitati where id_prof_titular = "+user.getIdUser()+
                    " or id_prof_delegat = "+user.getIdUser()+")");
            ResultSet rs = s.executeQuery();
            String curs = "";
            while(rs.next())
                curs = rs.getString("descriere");
            cursFilter.getItems().add(curs);
            cursFilter2.getItems().add(curs);
            cursFilter3.getItems().add(curs);
            cursFilter4.getItems().add(curs);
        } catch (SQLException throwables) {
            System.out.println("Nu s-a putut crea choice-box-ul de cursuri!");
        }

        anFilter.setItems(FXCollections.observableArrayList());

        try {
            PreparedStatement s = conexiune.prepareStatement("select distinct an_inceput from an_universitar order by an_inceput desc");
            ResultSet rs=s.executeQuery();
            int nrAni = 0;
            while(rs.next() && nrAni<8){
                ++nrAni;
                anFilter.getItems().add(rs.getInt("an_inceput"));
            }
        } catch (SQLException throwables) {
            System.out.println("Nu s-a putut crea choice-box-ul cu anii universitari!");
        }

        numeStudentColumn.setCellValueFactory(new PropertyValueFactory<>("numeStudent"));
        disciplinaNotaColumn.setCellValueFactory(new PropertyValueFactory<>("disciplina"));

        seminarColumn.setCellValueFactory(new PropertyValueFactory<>("notaSeminar"));
        seminarColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        seminarColumn.setOnEditCommit(event -> {
            try {
                Nota nota = event.getRowValue();
                int nouaNota = event.getNewValue();
                nota.setNotaSeminar(nouaNota);
                nota.setNotaFinala();
            } catch (SQLException throwables) {
                System.out.println("Eroare la update!");
            }
        });

        laboratorColumn.setCellValueFactory(new PropertyValueFactory<>("notaLab"));
        laboratorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        laboratorColumn.setOnEditCommit(event ->{
            try {
                Nota nota = event.getRowValue();
                int nouaNota = event.getNewValue();
                nota.setNotaLab(nouaNota);
                nota.setNotaFinala();
            } catch (SQLException throwables) {
                System.out.println("Eroare la update!");
            }
        });

        examenColumn.setCellValueFactory(new PropertyValueFactory<>("notaExamen"));
        examenColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        examenColumn.setOnEditCommit(event ->{
            try {
                Nota nota = event.getRowValue();
                int nouaNota = event.getNewValue();
                nota.setNotaExamen(nouaNota);
                nota.setNotaFinala();
            } catch (SQLException throwables) {
                System.out.println("Eroare la update!");
            }
        });

        notaColumn.setCellValueFactory(new PropertyValueFactory<>("notaFinala"));
        anColumn.setCellValueFactory(new PropertyValueFactory<>("anUniversitar"));
        catalog.setEditable(true);

        // Programare cursuri

        seminarTab.setDisable(true);
        laboratorTab.setDisable(true);
        eroarePondere.setVisible(false);
        eroareProgram.setVisible(false);
        programareReusita.setVisible(false);
        profInvalid.setVisible(false);

        // Programare examene
        eroareProgram1.setVisible(false);
        programareReusita1.setVisible(false);
        profInvalid1.setVisible(false);
        dataDepasita.setVisible(false);
    }

    public void filtrareCurs2() throws SQLException{
        String cursSel = cursFilter2.getValue();
        int id_curs = denumireToIdCurs(cursSel);
        if (cursSel.equals(""))
            return;

        //CURS
        int procent = 5;
        int durata = 2;
        int nr_max_participanti = 15;
        PreparedStatement s = conexiune.prepareStatement("select * from curs_activitati where id_curs = ? and id_activ = ?");
        s.setInt(1,id_curs);
        s.setInt(2,denumireActivtoIdActiv("curs"));
        ResultSet rs = s.executeQuery();
        while(rs.next()){
            procent = rs.getInt("procent");
            durata = rs.getInt("durata");
            nr_max_participanti = rs.getInt("nr_max_participanti");
        }

        perioadaFilter1.setItems(FXCollections.observableArrayList());
        perioadaFilter1.getItems().addAll("saptamanal","saptamana para","saptamana impara");
        ziFilter1.setItems(FXCollections.observableArrayList());
        ziFilter1.getItems().addAll("luni","marti","miercuri","joi","vineri","sambata","duminica");
        pondereSpinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5,100,procent));
        oraSpinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8,20));
        durataFilter1.setItems(FXCollections.observableArrayList());
        durataFilter1.getItems().addAll(2,3);
        durataFilter1.setValue(durata);
        maxParticipantiSpinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(15,capacitateMaxActivitate("curs"),nr_max_participanti));

        //SEMINAR
        procent = 5;
        durata = 2;
        nr_max_participanti = 15;
        PreparedStatement s1 = conexiune.prepareStatement("select * from curs_activitati where id_curs = ? and id_activ = ?");
        s1.setInt(1,id_curs);
        s1.setInt(2,denumireActivtoIdActiv("seminar"));
        ResultSet rs1 = s1.executeQuery();
        while(rs1.next()){
            procent = rs1.getInt("procent");
            durata = rs1.getInt("durata");
            nr_max_participanti = rs1.getInt("nr_max_participanti");
        }
        perioadaFilter2.setItems(FXCollections.observableArrayList());
        perioadaFilter2.getItems().addAll("saptamanal","saptamana para","saptamana impara");
        ziFilter2.setItems(FXCollections.observableArrayList());
        ziFilter2.getItems().addAll("luni","marti","miercuri","joi","vineri","sambata","duminica");
        pondereSpinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5,100,procent));
        oraSpinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8,20));
        durataFilter2.setItems(FXCollections.observableArrayList());
        durataFilter2.getItems().addAll(2,3);
        durataFilter2.setValue(durata);
        maxParticipantiSpinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(15,capacitateMaxActivitate("seminar"),nr_max_participanti));
        profDelegatFilter1.setItems(FXCollections.observableArrayList());
        profDelegatFilter1.getItems().addAll("dumneavoastra","alt profesor");

        //LABORATOR
        procent = 5;
        durata = 2;
        nr_max_participanti = 15;
        PreparedStatement s2 = conexiune.prepareStatement("select * from curs_activitati where id_curs = ? and id_activ = ?");
        s2.setInt(1,id_curs);
        s2.setInt(2,denumireActivtoIdActiv("laborator"));
        ResultSet rs2 = s2.executeQuery();
        while(rs2.next()){
            procent = rs2.getInt("procent");
            durata = rs2.getInt("durata");
            nr_max_participanti = rs2.getInt("nr_max_participanti");
        }

        perioadaFilter3.setItems(FXCollections.observableArrayList());
        perioadaFilter3.getItems().addAll("saptamanal","saptamana para","saptamana impara");
        ziFilter3.setItems(FXCollections.observableArrayList());
        ziFilter3.getItems().addAll("luni","marti","miercuri","joi","vineri","sambata","duminica");
        pondereSpinner3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5,100,procent));
        oraSpinner3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8,20));
        durataFilter3.setItems(FXCollections.observableArrayList());
        durataFilter3.getItems().addAll(2,3);
        durataFilter3.setValue(durata);
        maxParticipantiSpinner3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(15,capacitateMaxActivitate("laborator"),nr_max_participanti));
        profDelegatFilter2.setItems(FXCollections.observableArrayList());
        profDelegatFilter2.getItems().addAll("dumneavoastra","alt profesor");

        seminarCheckBox.setSelected(existaActivitate(id_curs,"seminar"));
        if (seminarCheckBox.isSelected())
            seminarTab.setDisable(false);
        laboratorCheckBox.setSelected(existaActivitate(id_curs,"laborator"));
        if (laboratorCheckBox.isSelected())
            laboratorTab.setDisable(false);

        // Programare examene

        eroareProgram1.setVisible(false);
        programareReusita1.setVisible(false);
        profInvalid1.setVisible(false);
    }

    void stergeActivitati(int id_curs,String activitate) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("call pDeleteActivitate(?,?)");
        s.setInt(1,id_curs);
        s.setInt(2,denumireActivtoIdActiv(activitate));
        s.executeUpdate();
    }

    public void enableDisable(ActionEvent actionEvent) throws SQLException{
        if (actionEvent.getSource() == seminarCheckBox) {
            if (seminarCheckBox.isSelected())
                seminarTab.setDisable(false);
            else{
                seminarTab.setDisable(true);
                String cursSel = cursFilter2.getValue();
                int id_curs = denumireToIdCurs(cursSel);
                stergeActivitati(id_curs,"seminar");
            }
        }
        else{ // daca am apasat Checkbox-ul laboratorCheckbox
            if (laboratorCheckBox.isSelected())
                laboratorTab.setDisable(false);
            else{
                laboratorTab.setDisable(true);
                String cursSel = cursFilter2.getValue();
                int id_curs = denumireToIdCurs(cursSel);
                stergeActivitati(id_curs,"laborator");
            }
        }
    }

    boolean suprapuneActivitate(int perioada,int nr_zi_sapt,int ora,int durata,int id_prof_delegat) throws SQLException{
        String query = "select * from curs_activitati where nr_zi_sapt = ? and id_prof_delegat = ?";
        PreparedStatement s = conexiune.prepareStatement(query);
        s.setInt(1,nr_zi_sapt);
        s.setInt(2,id_prof_delegat);
        ResultSet rs = s.executeQuery();
        while(rs.next()){
            int perioada1 = rs.getInt("perioada");
            int ora1 = rs.getInt("ora_inceput");
            int durata1 = rs.getInt("durata");
            if ((ora<=ora1 && ora+durata>ora1) || (ora>=ora1 && ora1+durata1>ora)){// suprapunere activitati
                if (perioada == perioada1) //daca ambele activitati se desfasoara in aceleasi saptamani
                    return true;
                else {
                    // daca o activitate are loc saptamanal, iar cealalta o data la 2 saptamani
                    if (perioada == perioadaToIdPerioada("saptamanal"))
                        return true;
                    else if (perioada1 == perioadaToIdPerioada("saptamanal"))
                        return true;
                }
            }
        }
        return false;
    }

    boolean suprapuneActivitate2(LocalDateTime data,int durata,int id_prof_delegat) throws SQLException{
        String query = "select * from examinari where id_activ_curs in (select id_ca from curs_activitati where id_prof_delegat = ?)";
        PreparedStatement s = conexiune.prepareStatement(query);
        s.setInt(1,id_prof_delegat);
        ResultSet rs = s.executeQuery();
        while(rs.next()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime data1=LocalDateTime.parse(rs.getString("data_desfasurare"),formatter);
            int durata1=rs.getInt("durata");
            int zi = data.getDayOfYear();
            int zi1 = data1.getDayOfYear();
            if (zi == zi1){
                int diff = Math.abs(data.getHour()-data1.getHour())*60;
                if (diff<durata && diff<durata1)
                    return true;
            }
        }
        return false;
    }
    boolean pondereValida(){
        eroareProgram.setVisible(false);
        eroarePondere.setVisible(false);
        programareReusita.setVisible(false);
        profInvalid.setVisible(false);
        int sum=pondereSpinner1.getValue();
        if (!seminarTab.isDisabled())
            sum+=pondereSpinner2.getValue();
        if (!laboratorTab.isDisabled())
            sum+=pondereSpinner3.getValue();
        if (sum==100)
            return true;
        eroarePondere.setVisible(true);
        return false;
    }

    public void programareCurs() throws SQLException{
        if (!pondereValida())
            return;
        String cursSel = cursFilter2.getValue();
        int id_curs = denumireToIdCurs(cursSel);
        int perioada = perioadaToIdPerioada(perioadaFilter1.getValue());
        int nr_zi_sapt = ziToIdZi(ziFilter1.getValue());
        int pondere = pondereSpinner1.getValue();
        int ora = oraSpinner1.getValue();
        int durata = durataFilter1.getValue();
        int nr_max_participanti = maxParticipantiSpinner1.getValue();
        if (suprapuneActivitate(perioada,nr_zi_sapt,ora,durata,user.getIdUser()))
            eroareProgram.setVisible(true);
        else{
            PreparedStatement s = conexiune.prepareStatement("call pInsertActivitate(?,?,?,?,?,?,?,?,?,?)");
            s.setInt(1,id_curs);
            s.setInt(2,denumireActivtoIdActiv("curs"));
            s.setInt(3,perioada);
            s.setInt(4,nr_zi_sapt);
            s.setInt(5,pondere);
            s.setInt(6,user.getIdUser());
            s.setInt(7,user.getIdUser());
            s.setInt(8,ora);
            s.setInt(9,durata);
            s.setInt(10,nr_max_participanti);
            s.executeUpdate();
            programareReusita.setVisible(true);
        }
    }

    public void programareSeminar() throws SQLException{
        if (!pondereValida())
            return;
        String cursSel = cursFilter2.getValue();
        int id_curs = denumireToIdCurs(cursSel);
        int perioada = perioadaToIdPerioada(perioadaFilter2.getValue());
        int nr_zi_sapt = ziToIdZi(ziFilter2.getValue());
        int pondere = pondereSpinner2.getValue();
        int ora = oraSpinner2.getValue();
        int durata = durataFilter2.getValue();
        int nr_max_participanti = maxParticipantiSpinner2.getValue();
        String careProf=profDelegatFilter1.getValue();
        int id_prof_delegat;
        if (careProf.equals("dumneavoastra"))
            id_prof_delegat = user.getIdUser();
        else{
            String cnpProf = profDelegatField1.getText();
            if (!cautaProfesor(cnpProf)){
                profInvalid.setVisible(true);
                return;
            }
            id_prof_delegat = cnpUserToIdUser(cnpProf);
        }
        if (suprapuneActivitate(perioada,nr_zi_sapt,ora,durata,id_prof_delegat))
            eroareProgram.setVisible(true);
        else{
            PreparedStatement s = conexiune.prepareStatement("call pInsertActivitate(?,?,?,?,?,?,?,?,?,?)");
            s.setInt(1,id_curs);
            s.setInt(2,denumireActivtoIdActiv("seminar"));
            s.setInt(3,perioada);
            s.setInt(4,nr_zi_sapt);
            s.setInt(5,pondere);
            s.setInt(6,user.getIdUser());
            s.setInt(7,id_prof_delegat);
            s.setInt(8,ora);
            s.setInt(9,durata);
            s.setInt(10,nr_max_participanti);
            s.executeUpdate();
            programareReusita.setVisible(true);
        }
    }

    public void programareLaborator() throws SQLException{
        if (!pondereValida())
            return;
        String cursSel = cursFilter2.getValue();
        int id_curs = denumireToIdCurs(cursSel);
        int perioada = perioadaToIdPerioada(perioadaFilter3.getValue());
        int nr_zi_sapt = ziToIdZi(ziFilter3.getValue());
        int pondere = pondereSpinner3.getValue();
        int ora = oraSpinner3.getValue();
        int durata = durataFilter3.getValue();
        int nr_max_participanti = maxParticipantiSpinner3.getValue();
        String careProf=profDelegatFilter2.getValue();
        int id_prof_delegat;
        if (careProf.equals("dumneavoastra"))
            id_prof_delegat = user.getIdUser();
        else{
            String cnpProf = profDelegatField2.getText();
            if (!cautaProfesor(cnpProf)){
                profInvalid.setVisible(true);
                return;
            }
            id_prof_delegat = cnpUserToIdUser(cnpProf);
        }
        if (suprapuneActivitate(perioada,nr_zi_sapt,ora,durata,id_prof_delegat))
            eroareProgram.setVisible(true);
        else{
            PreparedStatement s = conexiune.prepareStatement("call pInsertActivitate(?,?,?,?,?,?,?,?,?,?)");
            s.setInt(1,id_curs);
            s.setInt(2,denumireActivtoIdActiv("laborator"));
            s.setInt(3,perioada);
            s.setInt(4,nr_zi_sapt);
            s.setInt(5,pondere);
            s.setInt(6,user.getIdUser());
            s.setInt(7,id_prof_delegat);
            s.setInt(8,ora);
            s.setInt(9,durata);
            s.setInt(10,nr_max_participanti);
            s.executeUpdate();
            programareReusita.setVisible(true);
        }
    }

    public void filtrareCurs() throws SQLException, NullPointerException {
        catalog.setItems(FXCollections.observableArrayList());
        String cursSel = cursFilter.getValue();
        int anSel = anFilter.getValue();
        if (cursSel.equals(""))
            return;

        PreparedStatement s = conexiune.prepareStatement("select * from an_universitar where an_inceput = "+anSel);
        ResultSet rs=s.executeQuery();
        Date dataInceput = null;
        Date dataSfarsit = null;
        while(rs.next()){
            dataInceput = rs.getDate("data_inceput");
            dataSfarsit = rs.getDate("data_sfarsit");
        }

        int id_curs = denumireToIdCurs(cursSel);

        PreparedStatement s1 = conexiune.prepareStatement("select id_student from studenti_activitati group by id_student");
        ResultSet rs1 = s1.executeQuery();
        while(rs1.next()){
            int id_student = rs1.getInt("id_student");
            String numeStudent = idUserToNumeUser(id_student);
            int notaSeminar=0, notaLaborator=0, notaExamen=0;

            PreparedStatement s3 = conexiune.prepareStatement("select * from studenti_activitati where id_student = "+id_student
                    + " and id_activitate in (select id_ca from curs_activitati where id_curs = "+id_curs
                    + ") and data_notare between '"+dataInceput+"' and '"+dataSfarsit+"'");
            ResultSet rs3 = s3.executeQuery();
            while(rs3.next()){
                int activitate = rs3.getInt("id_activitate");
                PreparedStatement s4 = conexiune.prepareStatement("select * from curs_activitati where id_ca = "+activitate);
                ResultSet rs4=s4.executeQuery();
                int tipActiv;
                while(rs4.next()) {
                    tipActiv = rs4.getInt("id_activ");
                    if (idActivtoDenumireActiv(tipActiv).equals("curs"))  // curs
                        notaExamen = rs3.getInt("nota");
                    else if (idActivtoDenumireActiv(tipActiv).equals("seminar")) // seminar
                        notaSeminar = rs3.getInt("nota");
                    else // laborator
                        notaLaborator = rs3.getInt("nota");
                }
            }

            Nota nota = new Nota(numeStudent, cursSel,notaSeminar,notaLaborator,notaExamen,anSel);
            catalog.getItems().add(nota);
        }
    }

    void descarca(TableView<Curs> listaCursuri, String numeFisier) throws IOException {
        String path = System.getProperty("user.home")+"/Downloads/"+numeFisier;
        File lista = new File(path);
        lista.createNewFile();
        String content = "";
        for (Curs curs : listaCursuri.getItems())
            content = content + curs.getActivitate() +" de "+curs.getNume()+", se desfasoara "+curs.getPerioada()+" in zilele de "+curs.getZi_a_saptamanii()
                    +" de la ora "+curs.getOra_inceput()+", timp de "+curs.getDurata()+" ore\n";
        Files.write(Paths.get(path), content.getBytes());
    }

    void descarca1(TableView<ActivitateGrupa> listaActiv, String numeFisier) throws IOException{
        String path = System.getProperty("user.home")+"/Downloads/"+numeFisier;
        File lista = new File(path);
        lista.createNewFile();
        String content = "";
        for (ActivitateGrupa ag : listaActiv.getItems())
            content = content + "Activitate la "+ag.getDisciplina() +" cu grupa "+ag.getGrupa()+", va avea loc pe "+ag.getData()+", ora "+ag.getOra()
                    +" de la ora "+ag.getOra()+", timp de "+ag.getDurata()+" ore\n";
        Files.write(Paths.get(path), content.getBytes());
    }

    void descarca2() throws IOException{
        String path = System.getProperty("user.home")+"/Downloads/catalog.txt";
        File lista = new File(path);
        lista.createNewFile();
        String disciplina = catalog.getItems().get(0).getDisciplina();
        String content = "Notele studentilor la disciplina "+disciplina+"\n\n";
        for (Nota nota : catalog.getItems()) {
            content = content + nota.getNumeStudent() + ": seminar " + nota.getNotaSeminar() + ", laborator " + nota.getNotaLab()
            + ", examen "+nota.getNotaExamen()+", nota finala: "+nota.getNotaFinala()+"\n";
        }
        Files.write(Paths.get(path), content.getBytes());
    }

    void descarca3(TableView<Examen> listaExam,String numeFisier) throws IOException{
        String path = System.getProperty("user.home")+"/Downloads/"+numeFisier;
        File lista = new File(path);
        lista.createNewFile();
        String content = "";
        for (Examen e : listaExam.getItems()){
            content = content + e.getTip()+" la "+e.getCurs()+", se va desfasura la "+e.getData()+", timp de "+e.getDurata()+" minute\n";
        }
        Files.write(Paths.get(path), content.getBytes());
    }

    public void descarcaLista(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == descarcareButon1)
            descarca(activitatiCurs1,"orar_zi_curenta.txt");
        else if (actionEvent.getSource() == descarcareButon2)
            descarca(activitatiCurs2,"orar_complet.txt");
        else if (actionEvent.getSource() == descarcareButon3)
            descarca1(activitatiGrupe1,"orar_grupe_zi_curenta.txt");
        else if (actionEvent.getSource() == descarcareButon4)
            descarca1(activitatiGrupe2, "orar_grupe_complet.txt");
        else if (actionEvent.getSource() == descarcareButon5)
            descarca2();
        else if (actionEvent.getSource() == descarcareButon6)
            descarca3(exameneAstazi,"orar_examene_azi.txt");
        else if (actionEvent.getSource() == descarcareButon7)
            descarca3(exameneAll,"orar_examene_complet.txt");
    }

    public void filtrareCurs3() throws SQLException {
        String cursSel = cursFilter3.getValue();
        if (cursSel.equals(""))
            return;
        int id_curs = denumireToIdCurs(cursSel);
        grupFilter.setItems(FXCollections.observableArrayList());
        PreparedStatement s = conexiune.prepareStatement("select id_grupa from grupe where id_curs = "+id_curs);
        ResultSet rs = s.executeQuery();
        while(rs.next())
            grupFilter.getItems().add(rs.getInt("id_grupa"));
    }

    public void filtrareGrup() throws SQLException{
        int id_grupa = grupFilter.getValue();
        chat.setItems(FXCollections.observableArrayList());
        PreparedStatement s = conexiune.prepareStatement("select * from mesaje_grupe where id_grupa = " + id_grupa);
        ResultSet rs = s.executeQuery();
        while(rs.next()){
            String destinatar = idUserToNumeUser(rs.getInt("id_from"));
            String subiect = rs.getString("subiect");
            String mesaj = rs.getString("continut_mesaj");
            String data = rs.getString("timp_primire");
            String mesaj1 = "De la "+destinatar+"\nSubiect: "+subiect+"\nMesaj: "+mesaj+"\nTrimis pe "+data;
            chat.getItems().add(mesaj1);
        }
    }

    public void filtrareCurs4() throws SQLException{
        String cursSel = cursFilter4.getValue();
        if (cursSel.equals(""))
            return;
        int id_curs = denumireToIdCurs(cursSel);
        tipExamenFilter.setItems(FXCollections.observableArrayList());
        tipExamenFilter.getItems().add("examen");
        if (existaActivitate(id_curs,"laborator"))
            tipExamenFilter.getItems().add("colocviu");
        dataExamen.setValue(LocalDate.now());
        oraSpinner4.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8,20));
        durataSpinner4.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10,180));
        profDelegatFilter3.setItems(FXCollections.observableArrayList());
        profDelegatFilter3.getItems().addAll("dumneavoastra","alt profesor");
    }

    public void programareExamen() throws SQLException {
        programareReusita1.setVisible(false);
        profInvalid1.setVisible(false);
        eroareProgram1.setVisible(false);
        dataDepasita.setVisible(false);
        String cursSel = cursFilter4.getValue();
        if (cursSel.equals(""))
            return;
        int id_curs = denumireToIdCurs(cursSel);
        String tipExam = tipExamenFilter.getValue();
        int id_tip_examinare = denumireActivtoIdActiv(tipExam);
        int tip_activ=0;
        if (tipExam.equals("examen"))
            tip_activ = denumireActivtoIdActiv("curs");
        else if (tipExam.equals("colocviu"))
            tip_activ = denumireActivtoIdActiv("laborator");

        LocalDate dataExam = dataExamen.getValue();
        int an = dataExam.getYear();
        int luna = dataExam.getMonthValue();
        int zi = dataExam.getDayOfMonth();
        int ora = oraSpinner4.getValue();
        LocalDateTime data = LocalDateTime.of(an,luna,zi,ora,0);
        if (data.isBefore(LocalDateTime.now())){
            dataDepasita.setVisible(true);
            return;
        }

        cal.setTime(data_sfarsit);
        an=cal.get(Calendar.YEAR);
        luna=cal.get(Calendar.MONTH)+1;
        zi=cal.get(Calendar.DAY_OF_MONTH);
        LocalDateTime data_sfarsit1 = LocalDateTime.of(an,luna,zi,23,59);
        if(data.isAfter(data_sfarsit1)){
            dataDepasita.setVisible(true);
            return;
        }

        int durata = durataSpinner4.getValue();
        int id_prof_delegat;
        if (profDelegatFilter3.getValue().equals("dumneavoastra"))
            id_prof_delegat = user.getIdUser();
        else {
            String cnpProf = profDelegatField3.getText();
            if (!cautaProfesor(cnpProf)){
                profInvalid1.setVisible(true);
                return;
            }
            id_prof_delegat = cnpUserToIdUser(cnpProf);
        }
        if (suprapuneActivitate2(data,durata,id_prof_delegat)){
            eroareProgram1.setVisible(true);
            return;
        }

        int id_activ_curs = 0;
        PreparedStatement s = conexiune.prepareStatement
        ("select id_ca from curs_activitati where id_curs = ? and id_activ = ? and id_prof_titular = ? and id_prof_delegat = ?");
        s.setInt(1,id_curs);
        s.setInt(2,tip_activ);
        s.setInt(3,user.getIdUser());
        s.setInt(4,id_prof_delegat);
        ResultSet rs = s.executeQuery();
        while(rs.next())
            id_activ_curs = rs.getInt("id_ca");

        PreparedStatement s1 = conexiune.prepareStatement("call pInsertExamen (?,?,?,?)");
        s1.setInt(1,id_tip_examinare);
        s1.setString(2, String.valueOf(data));
        s1.setInt(3,durata);
        s1.setInt(4,id_activ_curs);
        s1.executeUpdate();
        programareReusita1.setVisible(true);
    }
}
