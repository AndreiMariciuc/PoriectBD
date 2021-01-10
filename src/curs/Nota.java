package curs;

import bazaDate.Conexiune;

import java.sql.*;

import static bazaDate.Functii.*;
import static java.lang.Math.round;

public class Nota {
    private String numeStudent;
    private String disciplina;
    private int notaSeminar;
    private int notaLab;
    private int notaExamen;
    private int notaFinala;
    private int anUniversitar;

    static Connection conexiune = Conexiune.getConexiune().connection;

    public Nota(String numeStudent, String disciplina, int notaSeminar, int notaLab, int notaExamen, int anUniversitar) throws SQLException {
        this.numeStudent = numeStudent;
        this.disciplina = disciplina;
        this.notaSeminar = notaSeminar;
        this.notaLab = notaLab;
        this.notaExamen = notaExamen;
        this.anUniversitar = anUniversitar;
        setNotaFinala();
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public int getNotaSeminar() {
        return notaSeminar;
    }

    public int getNotaLab() {
        return notaLab;
    }

    public int getNotaExamen() {
        return notaExamen;
    }

    public int getNotaFinala() {
        return notaFinala;
    }

    public void setNotaFinala(int notaFinala) {
        this.notaFinala = notaFinala;
    }

    public int getAnUniversitar() {
        return anUniversitar;
    }

    public void setAnUniversitar(int anUniversitar) {
        this.anUniversitar = anUniversitar;
    }

    public static Connection getConexiune() {
        return conexiune;
    }

    public static void setConexiune(Connection conexiune) {
        Nota.conexiune = conexiune;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setNotaSeminar(int notaSeminar) throws SQLException {
        this.notaSeminar = notaSeminar;
        PreparedStatement s = conexiune.prepareStatement
                ("update studenti_activitati set nota = ?, data_notare = curdate() where id_student = ? and id_activitate in " +
                        "(select id_ca from curs_activitati where id_curs = ? and id_activ = ?)");
        s.setInt(1, notaSeminar);
        s.setInt(2, numeUserToIdUser(numeStudent));
        s.setInt(3, denumireToIdCurs(disciplina));
        s.setInt(4, denumireActivtoIdActiv("seminar"));
        s.executeUpdate();
        setNotaFinala();
    }

    public void setNotaLab(int notaLab) throws SQLException {
        this.notaLab = notaLab;
        PreparedStatement s = conexiune.prepareStatement
                ("update studenti_activitati set nota = ?, data_notare = curdate() where id_student = ? and id_activitate in " +
                        "(select id_ca from curs_activitati where id_curs = ? and id_activ = ?)");
        s.setInt(1, notaLab);
        s.setInt(2, numeUserToIdUser(numeStudent));
        s.setInt(3, denumireToIdCurs(disciplina));
        s.setInt(4, denumireActivtoIdActiv("laborator"));
        s.executeUpdate();
        setNotaFinala();
    }

    public void setNotaExamen(int notaExamen) throws SQLException {
        this.notaExamen = notaExamen;
        PreparedStatement s = conexiune.prepareStatement
                ("update studenti_activitati set nota = ?, data_notare = curdate() where id_student = ? and id_activitate in " +
                        "(select id_ca from curs_activitati where id_curs = ? and id_activ = ?)");
        s.setInt(1, notaExamen);
        s.setInt(2, numeUserToIdUser(numeStudent));
        s.setInt(3, denumireToIdCurs(disciplina));
        s.setInt(4, denumireActivtoIdActiv("curs"));
        s.executeUpdate();
        setNotaFinala();
    }

    public void setNotaFinala() throws SQLException {
        int id_curs = denumireToIdCurs(disciplina);
        int id_prof = -1;
        PreparedStatement s = conexiune.prepareStatement
                ("select id_prof_titular from vactivitatistudent where id_student = " + numeUserToIdUser(numeStudent)+" group by id_prof_titular");
        ResultSet rs = s.executeQuery();
        while (rs.next())
            id_prof = rs.getInt("id_prof_titular");
        System.out.println(notaExamen);
        System.out.println(pondereActiv(id_curs, denumireActivtoIdActiv("curs"), id_prof));
        System.out.println(notaSeminar);
        System.out.println(pondereActiv(id_curs, denumireActivtoIdActiv("seminar"), id_prof));
        System.out.println(notaLab);
        System.out.println(pondereActiv(id_curs, denumireActivtoIdActiv("laborator"), id_prof));
        notaFinala = round((1.0f * notaExamen * pondereActiv(id_curs, denumireActivtoIdActiv("curs"), id_prof)
                + 1.0f * notaSeminar * pondereActiv(id_curs, denumireActivtoIdActiv("seminar"), id_prof)
                + 1.0f * notaLab * pondereActiv(id_curs, denumireActivtoIdActiv("laborator"), id_prof)) / 100);
    }
}
