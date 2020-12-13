package bazaDate;

import java.sql.*;

public class Conexiune {
    static final String urlAless = null;
    static final String urlTuddi = null;
    static final String urlAndrei = null;
    static User curent;
    Connection connection = null;
    Statement selectStatement = null, interrogationStatement = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    static Conexiune c = new Conexiune();

    private Conexiune() {
        curent = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Negasit!");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aplicatie", "root", "password");
            selectStatement = connection.createStatement();
            /*rs = selectStatement.executeQuery("SELECT * FROM users");
            rsmd = rs.getMetaData();
            int nColumns = rsmd.getColumnCount();
            while (rs.next()) {
                for (int j = 1; j < nColumns; j++) {
                    System.out.print(rs.getString(j) + " ");
                }
                System.out.println("");
            }*/
        } catch (Exception e) {
            System.out.println("Erori in interogarea bazei de date!");
        }
    }

    public static Conexiune getConexiune() {
        return c;
    }

    @Override
    public void finalize() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Nici nu a fost deschis");
        }
        try {
            selectStatement.close();
        } catch (Exception e) {
            System.out.println("Nici nu a fost deschis");
        }
        try {
            rs.close();
        } catch (Exception e) {
            System.out.println("Nici nu a fost deschis");
        }
        try {
            interrogationStatement.close();
        } catch (Exception e) {
            System.out.println("Nici nu a fost deschis");
        }
    }

    public int getUserType(String username, String parola) {
        try {
            curent = getDate(username, parola);
            return curent.getIdRol();
        } catch (Exception e) {
            return 0;
        }
    }
    public User getDate(String username, String parola){
        try {
            rs = selectStatement.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND parola = '" + parola + "';");
            rs.next();
            User result = new User();
            result.setIdRol(rs.getInt(2));
            result.setIdUser(rs.getInt(1));
            result.setCNP(rs.getString(3));
            result.setNume(rs.getString(4));
            result.setPrenume(rs.getString(5));
            result.setAdresa(rs.getString(6));
            result.setNrTelefon(rs.getString(7));
            result.setEmail(rs.getString(8));
            result.setIBAN(rs.getString(9));
            result.setUsername(rs.getString(11));
            result.setParola(rs.getString(12));
            result.setNrContract(rs.getInt(10));
            return result;
        } catch (Exception e){
            return null;
        }
    }
    public static User getUser(){
        return curent;
    }
}