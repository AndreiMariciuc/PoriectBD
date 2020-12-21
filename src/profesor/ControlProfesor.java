package profesor;

import bazaDate.Conexiune;
import constante.Ecran;
import curs.ActivitateGrupa;
import curs.Curs;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

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


    public void schimbareStare(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == datePersonaleButton) {
            datePersonalePane.toFront();
            welcome.setText("Bun venit " + Conexiune.getConexiune().getUser().getNume() + " " + Conexiune.getConexiune().getUser().getPrenume() + "!");
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
            currentStage.setScene(new Scene(root, Ecran.WIDTH, (int)(Ecran.HEIGHT / 1.5)));
            currentStage.show();
        }
    }

    public void defaultChange() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Date personale
        welcome.setText("Bun venit: \n" + Conexiune.getConexiune().getUser().getNume() + " " + Conexiune.getConexiune().getUser().getPrenume() + "!");
        cnpText.setText(Conexiune.getConexiune().getUser().getCNP());
        numeText.setText(Conexiune.getConexiune().getUser().getNume());
        prenumeText.setText(Conexiune.getConexiune().getUser().getPrenume());
        adresaText.setText(Conexiune.getConexiune().getUser().getAdresa());
        nrTelefonText.setText(Conexiune.getConexiune().getUser().getNrTelefon());
        emailText.setText(Conexiune.getConexiune().getUser().getEmail());
        ibanText.setText(Conexiune.getConexiune().getUser().getIBAN());
        nrContractText.setText(Integer.toString(Conexiune.getConexiune().getUser().getNrContract()));

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

        // Orar
                    /*
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int ziCurenta = cal.get(Calendar.DAY_OF_WEEK)-1;
             */
        int ziCurenta = 3;

        try {
            Connection conex = Conexiune.getConexiune().connection;
            int id_user = Conexiune.getConexiune().getUser().getIdUser();
            PreparedStatement s = conex.prepareStatement
                    ("select * from curs_activitati where "
                            + "id_prof_titular = "+id_user+" or id_prof_delegat = "+id_user);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                PreparedStatement s1 = conex.prepareStatement("select descriere from cursuri where id_curs = "+rs.getInt("id_curs"));
                ResultSet rs1 = s1.executeQuery();
                String numeCurs="";
                while(rs1.next())
                    numeCurs = rs1.getString("descriere");

                PreparedStatement s2 = conex.prepareStatement("select denumire_activitate from activitati where id_activitate = "+rs.getInt("id_activ"));
                ResultSet rs2 = s2.executeQuery();
                String tipActivitate="";
                while(rs2.next())
                    tipActivitate = rs2.getString("denumire_activitate");

                PreparedStatement s3 = conex.prepareStatement("select descriere from perioade where id_perioada = "+rs.getInt("perioada"));
                ResultSet rs3 = s3.executeQuery();
                String perioada="";
                while(rs3.next())
                    perioada = rs3.getString("descriere");

                int nr_zi = rs.getInt("nr_zi_sapt");
                PreparedStatement s4 = conex.prepareStatement("select zi from zile where id_zi = "+nr_zi);
                ResultSet rs4 = s4.executeQuery();
                String zi="";
                while(rs4.next())
                    zi = rs4.getString("zi");
                int ora_inceput = rs.getInt("ora_inceput");
                int durata = rs.getInt("durata");
                Curs curs = new Curs(numeCurs,tipActivitate,perioada,zi,ora_inceput,durata);
                if (nr_zi == ziCurenta)
                    activitatiCurs1.getItems().add(curs);
                activitatiCurs2.getItems().add(curs);
            }
        } catch (SQLException throwables) {
            System.out.println("Conexiune esuata!");
        }

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

        try {
            Connection conex = Conexiune.getConexiune().connection;
            PreparedStatement s = conex.prepareStatement
                ("select * from programari_activitati_grupe where id_grupa in "
                + "(select id_grupa from grupe where id_profesor_raspunzator = "+Conexiune.getConexiune().getUser().getIdUser()+")");
            ResultSet rs = s.executeQuery();
            while(rs.next()){
                int grupa = rs.getInt("id_grupa");
                PreparedStatement s1 = conex.prepareStatement("select descriere from cursuri where id_curs = "+rs.getInt("id_curs"));
                ResultSet rs1 = s1.executeQuery();
                String numeCurs="";
                while(rs1.next())
                    numeCurs = rs1.getString("descriere");
                Date data = rs.getDate("data");
                int ora = rs.getInt("ora");
                int durata = rs.getInt("durata");
                ActivitateGrupa ag = new ActivitateGrupa(grupa,numeCurs,data,ora,durata);
                if (data.equals(new Date())) // data = data_curenta
                    activitatiGrupe1.getItems().add(ag);
                activitatiGrupe2.getItems().add(ag);
            }
        } catch (SQLException throwables) {
            System.out.println("Conexiune esuata!");
        }
    }

    void descarca(TableView<Curs> listaCursuri, String numeFisier) throws IOException {
        String path = System.getProperty("user.home")+"/Downloads/"+numeFisier;
        File lista = new File(path);
        lista.createNewFile();
        String content = "";
        for (Curs curs : listaCursuri.getItems())
            content = content + curs.getActivitate() +" de "+curs.getNume()+", se desfasoara "+curs.getPerioada()+" in zilele de "+curs.getZi_a_saptamanii()+" de la ora "+curs.getOra_inceput()+", timp de "+curs.getDurata()+" ore\n";
        Files.write(Paths.get(path), content.getBytes());
    }

    void descarca1(TableView<ActivitateGrupa> listaActiv, String numeFisier) throws IOException{
        String home = System.getProperty("user.home");
        String path = home+"/Downloads/"+numeFisier;
        File lista = new File(path);
        lista.createNewFile();
        String content = "";
//        for (ActivitateGrupa ag : listaActiv.getItems())
//            content = content + ag.getActivitate() +" de "+ag.getNume()+", se desfasoara "+ag.getPerioada()+" in zilele de "+ag.getZi_a_saptamanii()+" de la ora "+ag.getOra_inceput()+", timp de "+ag.getDurata()+" ore, reprezentand "+ag.getProcent()+"% din nota finala\n";
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
    }
}
