package bazaDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Functii {
    static Connection conexiune = Conexiune.getConexiune().connection;

    public static String idCursToDenumireCurs(int id_curs) throws SQLException {
        PreparedStatement s = conexiune.prepareStatement("select descriere from cursuri where id_curs = " + id_curs);
        ResultSet rs = s.executeQuery();
        String numeCurs = "";
        while (rs.next())
            numeCurs = rs.getString("descriere");
        return numeCurs;
    }

    public static String idActivtoDenumireActiv(int id_activ) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select denumire_activitate from activitati where id_activitate = " + id_activ);
        ResultSet rs = s.executeQuery();
        String tipActivitate = "";
        while (rs.next())
            tipActivitate = rs.getString("denumire_activitate");
        return tipActivitate;
    }

    public static int denumireActivtoIdActiv(String activitate) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select id_activitate from activitati where denumire_activitate = '" + activitate +"'");
        ResultSet rs = s.executeQuery();
        int id_activitate=-1;
        while (rs.next())
            id_activitate = rs.getInt("id_activitate");
        return id_activitate;
    }

    public static String idPerioadaToPerioada(int id_perioada) throws SQLException {
        PreparedStatement s = conexiune.prepareStatement("select descriere from perioade where id_perioada = " + id_perioada);
        ResultSet rs = s.executeQuery();
        String perioada = "";
        while (rs.next())
            perioada = rs.getString("descriere");
        return perioada;
    }

    public static int perioadaToIdPerioada(String perioada) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select id_perioada from perioade where descriere = ?");
        s.setString(1,perioada);
        ResultSet rs = s.executeQuery();
        int id_perioada = -1;
        while(rs.next())
            id_perioada = rs.getInt("id_perioada");
        return id_perioada;
    }

    public static String idZiToZi(int nr_zi) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select zi from zile where id_zi = " + nr_zi);
        ResultSet rs = s.executeQuery();
        String zi = "";
        while (rs.next())
            zi = rs.getString("zi");
        return zi;
    }

    public static int ziToIdZi(String zi) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select id_zi from zile where zi = ?");
        s.setString(1,zi);
        ResultSet rs = s.executeQuery();
        int id_zi=-1;
        while(rs.next())
            id_zi=rs.getInt("id_zi");
        return id_zi;
    }

    public static int denumireToIdCurs(String numeCurs) throws SQLException {
        PreparedStatement s = conexiune.prepareStatement("select id_curs from cursuri where descriere = '"+numeCurs+"'");
        ResultSet rs = s.executeQuery();
        int id_curs=-1;
        while(rs.next())
            id_curs = rs.getInt("id_curs");
        return id_curs;
    }

    public static int numeUserToIdUser (String numeUser) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select id_user from users where concat(nume,' ',prenume) = '"+numeUser+"'");
        ResultSet rs=s.executeQuery();
        int id_user = -1;
        while(rs.next())
            id_user = rs.getInt("id_user");
        return id_user;
    }

    public static String idUserToNumeUser (int id_user) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select concat(nume,' ',prenume) from users where id_user = "+id_user);
        ResultSet rs=s.executeQuery();
        String nume="";
        while(rs.next())
            nume=rs.getString(1);
        return nume;
    }

    public static int cnpUserToIdUser (String cnpUser) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select id_user from users where cnp = '"+cnpUser+"'");
        ResultSet rs=s.executeQuery();
        int id_user = -1;
        while(rs.next())
            id_user = rs.getInt("id_user");
        return id_user;
    }

    public static float pondereActiv(int id_curs,int id_activitate) throws SQLException {
        PreparedStatement s = conexiune.prepareStatement("select procent from curs_activitati where id_curs = "+id_curs+" and id_activ = "+id_activitate);
        ResultSet rs=s.executeQuery();
        int pondere = -1;
        while(rs.next())
            pondere = rs.getInt("procent");
        return (float)pondere;
    }

    public static int capacitateMaxActivitate(String activitate) throws SQLException {
        PreparedStatement s = conexiune.prepareStatement("select nr_max_participanti from activitati where denumire_activitate = ?");
        s.setString(1,activitate);
        ResultSet rs=s.executeQuery();
        int capacitate=-1;
        while(rs.next())
            capacitate = rs.getInt("nr_max_participanti");
        return capacitate;
    }

    public static boolean existaActivitate(int id_curs, String activitate) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement("select * from curs_activitati where id_curs = ? and id_activ = ? limit 1");
        s.setInt(1,id_curs);
        s.setInt(2,denumireActivtoIdActiv(activitate));
        ResultSet rs=s.executeQuery();
        return rs.next();
    }

    public static boolean cautaProfesor(String numeUser) throws SQLException{
        PreparedStatement s = conexiune.prepareStatement
        ("select * from users where concat(nume,' ',prenume) = ? and id_rol = 3");
        s.setString(1,numeUser);
        ResultSet rs = s.executeQuery();
        return (rs.next());
    }
}
