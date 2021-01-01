package bazaDate;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Conexiune {
    static final String urlAless = null;
    static final String urlTuddi = null;
    static final String urlAndrei = null;
    static User curent;
    public static Connection connection = null;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aplicatie", "root", "mysquel1");
            selectStatement = connection.createStatement();
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

    public User getDate(String username, String parola) {
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
            if (rs.getInt(2) == 3) {
                //profesor;
            } else if (rs.getInt(2) == 4) {
                rs = selectStatement.executeQuery("select * from studenti where id_student = " + rs.getInt(1) + ";");
                rs.next();
                Student student = new Student();
                student.setIdUser(result.getIdUser());
                student.setIdRol(result.getIdRol());
                student.setCNP(result.getCNP());
                student.setNume(result.getNume());
                student.setPrenume(result.getPrenume());
                student.setAdresa(result.getAdresa());
                student.setNrTelefon(result.getNrTelefon());
                student.setEmail(result.getEmail());
                student.setIBAN(result.getIBAN());
                student.setNrContract(result.getNrContract());
                student.setUsername(result.getUsername());
                student.setParola(result.getParola());
                student.setNrOre(rs.getInt(2));
                student.setAnStudiu(rs.getInt(3));
                student.setIdStatus(rs.getInt(4));
                student.setDenumireCursuri(getCursuriDisponibile(student.getIdUser()));
                result = student;
            }
            return result;
        } catch (Exception e) {
            System.out.println("blabla!");
        }
        return null;
    }

    public ArrayList<String> getCursuriDisponibile(int idStud) {
        try {
            ArrayList<String> result = new ArrayList<>();
            rs = selectStatement.executeQuery("call cursuriDisponibile( " + idStud + " );");
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            return result;
        } catch (SQLException e) {
            System.out.println("cursuri disponibile");
        }
        return null;
    }

    public ArrayList<User> getUsersList(ArrayList<Integer> ids) {
        ArrayList<User> resultedList = new ArrayList<User>();
        String statement = "SELECT * FROM users WHERE ";
        for (Integer i : ids) {
            statement += "id_rol = " + String.valueOf(i) + " OR ";
        }
        statement += "FALSE;";
        try {
            rs = selectStatement.executeQuery(statement);
            while (rs.next()) {
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
                resultedList.add(result);
            }
            return resultedList;
        } catch (Exception e) {
            return null;
        }
    }

    public static User getUser() {
        return curent;
    }

    public static void resetUser() {
        curent = null;
    }

    public ArrayList<User> getResultByQuery(String query) {
        ArrayList<User> resultedList = new ArrayList<User>();
        try {
            rs = selectStatement.executeQuery(query);
            while (rs.next()) {
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
                resultedList.add(result);
            }
            return resultedList;
        } catch (Exception ignored) {
            return resultedList;
        }
    }

    public ArrayList<Departamente> getDepartamente() {
        ArrayList<Departamente> result = new ArrayList<Departamente>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM departamente");
            while (rs.next()) {
                Departamente departament = new Departamente();
                departament.setId(rs.getInt(1));
                departament.setCod(rs.getString(2));
                departament.setDescriere(rs.getString(3));
                result.add(departament);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<Status> getStatus() {
        ArrayList<Status> result = new ArrayList<Status>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM status_activ;");
            while (rs.next()) {
                Status status = new Status();
                status.setIdStatus(rs.getInt(1));
                status.setDescriereStatus(rs.getString(2));
                result.add(status);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<Cursuri> getCursuri() {
        ArrayList<Cursuri> result = new ArrayList<Cursuri>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM cursuri;");
            while (rs.next()) {
                Cursuri curs = new Cursuri();
                curs.setId(rs.getInt(1));
                curs.setDenumire(rs.getString(2));
                curs.setDescriere(rs.getString(3));
                result.add(curs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean executareQuery(String query) {
        try {
            connection.createStatement().execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<VedereCurs> getListaCursuri() {
        ArrayList<VedereCurs> listaVedereCurs = new ArrayList<VedereCurs>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM vCursuri;");
            while (rs.next()) {
                VedereCurs vc = new VedereCurs();
                vc.setIdCurs(rs.getInt(1));
                vc.setIdCursActivitate(rs.getInt(2));
                vc.setDenumireCurs(rs.getString(3));
                vc.setDescriereCurs(rs.getString(4));
                vc.setNrZiSaptamana(rs.getInt(5));
                vc.setOraInceput(rs.getInt(6));
                vc.setNrMaxParticipanti(rs.getInt(7));
                vc.setNumeProf(rs.getString(8));
                vc.setPrenumeProf(rs.getString(9));
                listaVedereCurs.add(vc);
            }
        } catch (Exception e) {
            System.out.println("salut" + e.getMessage());
        }
        return listaVedereCurs;
    }

    public ArrayList<VedereStudentiLaCurs> getListaStudentiLaCurs() {
        ArrayList<VedereStudentiLaCurs> result = new ArrayList<VedereStudentiLaCurs>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM vStudentiLaCurs;");
            while (rs.next()) {
                VedereStudentiLaCurs vsc = new VedereStudentiLaCurs();
                vsc.setIdUser(rs.getInt(1));
                vsc.setNumeStudent(rs.getString(2));
                vsc.setPrenumeStudent(rs.getString(3));
                vsc.setNrTelefon(rs.getString(4));
                vsc.setEmail(rs.getString(5));
                vsc.setDenumireCurs(rs.getString(6));
                vsc.setDescriereCurs(rs.getString(7));
                vsc.setNumeProfesor(rs.getString(8));
                vsc.setPrenumeProfesor(rs.getString(9));
                vsc.setIdCursActivitate(rs.getInt(10));
                result.add(vsc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<Profesor> getListaProfesori() {
        ArrayList<Profesor> result = new ArrayList<Profesor>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM vProfesori;");
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setIdUser(rs.getInt(1));
                p.setCNP(rs.getString(2));
                p.setIdRol(3);
                p.setNume(rs.getString(4));
                p.setPrenume(rs.getString(5));
                p.setAdresa(rs.getString(6));
                p.setNrTelefon(rs.getString(7));
                p.setEmail(rs.getString(8));
                p.setIBAN(rs.getString(9));
                p.setNrContract(rs.getInt(10));
                p.setNrMinOre(rs.getInt(11));
                p.setNrMaxOre(rs.getInt(12));
                p.setIdDepartament(rs.getInt(15));
                result.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean existaProfesorAsignat(int idProf, int idCurs) {
        boolean result = false;
        try {
            rs = selectStatement.executeQuery("SELECT count(*) FROM curs_activitati WHERE id_curs =" + idCurs + " AND id_prof_titular = " + idProf + ";");
            rs.next();
            if (rs.getInt(1) == 0) {
                result = false;
            } else {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public String getProfesorTitular(String cursDescriere) {
        try {
            rs = selectStatement.executeQuery("SELECT id_curs FROM cursuri WHERE descriere = '" + cursDescriere + "';");
            if (rs.next()) {
                int idCurs = rs.getInt(1);
                rs = selectStatement.executeQuery("call profesorCuStudMin(" + idCurs + ");");
                if (rs.next()) {
                    int idProf = rs.getInt(1);
                    rs = selectStatement.executeQuery("SELECT nume, prenume FROM USERS WHERE id_user = " + idProf + ";");
                    if (rs.next()) {
                        ((Student) curent).setIdProfTitular(idProf);
                        return rs.getString(1) + " " + rs.getString(2);
                    }
                } else {
                    //nu studenti la nimeni, alegem un profesor la nimereala
                    rs = selectStatement.executeQuery("select ca.id_prof_titular from curs_activitati ca where id_curs = " + idCurs + ";");
                    if (rs.next()) {
                        int idProf = rs.getInt(1);
                        rs = selectStatement.executeQuery("SELECT nume, prenume FROM USERS WHERE id_user = " + idProf + ";");
                        if (rs.next()) {
                            ((Student) curent).setIdProfTitular(idProf);
                            return rs.getString(1) + " " + rs.getString(2);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("eroare profesor titular!");
        }
        return null;
    }

    public ArrayList<String> getActivitatiDisponibile(int idProf, String cursDescriere, int idStudent, int activitate) {
        try {
            rs = selectStatement.executeQuery("SELECT id_curs FROM cursuri WHERE descriere = '" + cursDescriere + "';");
            if (rs.next()) {
                int idCurs = rs.getInt(1);
                rs = selectStatement.executeQuery("call getActivitatiCurs(" + idCurs + "," + idStudent + "," + idProf + "," + activitate + ");");
                ArrayList<String> result = new ArrayList<>();
                ArrayList<String> zile = new ArrayList<>() {
                    {
                        add("nimic");
                        add("luni");
                        add("marti");
                        add("miercuri");
                        add("joi");
                        add("vineri");
                        add("sambata");
                        add("duminica");
                    }
                };
                while (rs.next()) {
                    String adauga;
                    int perioada = rs.getInt("perioada");
                    int ziSaptamana = rs.getInt("nr_zi_sapt");
                    int oraInceput = rs.getInt("ora_inceput");
                    int durata = rs.getInt("durata");
                    String nume = null, perioadaDescirere = null;
                    Statement localStatement = connection.createStatement();
                    ResultSet localResult = localStatement.executeQuery("select nume, prenume from users where id_user = " + rs.getInt("id_prof_delegat") + ";");
                    if (localResult.next())
                        nume = localResult.getString(1) + " " + localResult.getString(2);

                    localResult = localStatement.executeQuery("select descriere from perioade where id_perioada =" + perioada + ";");
                    if (localResult.next())
                        perioadaDescirere = localResult.getString(1);
                    //System.out.println(rs.getInt(1));
                    //System.out.println("Profesor: " + nume + ", " + perioadaDescirere + " de la ora " + oraInceput + " in ziua " + zile.get(ziSaptamana) + " si tine " + durata + " ore");
                    String deAdaugat = "Profesor: " + nume + ", " + perioadaDescirere + " de la ora " + oraInceput + " in ziua " + zile.get(ziSaptamana) + " si tine " + durata + " ore";
                    ((Student) curent).addToMap(deAdaugat, rs.getInt(1));
                    result.add(deAdaugat);
                }
                return result;
            }
        } catch (SQLException throwables) {
            System.out.println("Ceva nu e bun la getAcitivatai cu codul: " + activitate);
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getDenumireCursuriInscris(int idStud) {
        try {
            rs = selectStatement.executeQuery("call cursuriStudent(" + idStud + ");");
            ArrayList<String> result = new ArrayList<>();
            while (rs.next())
                result.add(rs.getString(1));
            return result;
        } catch (SQLException e) {
            System.out.println("getCursuriInscrise");
        }
        return null;
    }

    public void executeSQL(String statement) {
        try {
            selectStatement = connection.createStatement();
            selectStatement.executeUpdate(statement);
        } catch (SQLException e) {
            System.out.println("nu merge ceva cand executi sql statement!");
        }
    }

    public void calendar() {
        try {
            List<Calendar> toateActivitatile = new ArrayList<>();
            List<Calendar> activitatiAzi = new ArrayList<>();
            ArrayList<String> zile = new ArrayList<>() {
                {
                    add("nimic");
                    add("luni");
                    add("marti");
                    add("miercuri");
                    add("joi");
                    add("vineri");
                    add("sambata");
                    add("duminica");
                }
            };


            rs = selectStatement.executeQuery("call calendarToate(" + curent.getIdUser() + ");");
            while (rs.next()) {
                Calendar c = new Calendar();
                c.setDisciplina(rs.getString(1));
                c.setActivitate(rs.getString(2));
                c.setPerioada(rs.getString(3));
                c.setZi(zile.get(rs.getInt(4)));
                c.setOra(rs.getInt(5));
                c.setDurata(rs.getInt(6));
                c.setNumeProfesor(rs.getString(7));
                toateActivitatile.add(c);
            }

            ((Student) curent).setToateActivitati(toateActivitatile);

            rs = selectStatement.executeQuery("call calendar(" + curent.getIdUser() + ");");
            while (rs.next()) {
                Calendar c = new Calendar();
                if (rs.getInt(4) == LocalDateTime.now().getDayOfWeek().getValue()) {
                    c.setDisciplina(rs.getString(1));
                    c.setActivitate(rs.getString(2));
                    c.setPerioada(rs.getString(3));
                    c.setZi(zile.get(rs.getInt(4)));
                    c.setOra(rs.getInt(5));
                    c.setDurata(rs.getInt(6));
                    c.setNumeProfesor(rs.getString(7));
                    activitatiAzi.add(c);
                }
            }

            ((Student) curent).setAziActivitati(activitatiAzi);

        } catch (SQLException e) {
            System.out.println("uita te la metoda Calendar!");
        }
    }

    public void carnet() {
        try {
            List<Carnet> notePartial = new ArrayList<>();
            List<Carnet> noteFinal = new ArrayList<>();

            rs = selectStatement.executeQuery("call notePartiale(" + curent.getIdUser() + ");");
            while (rs.next()) {
                Carnet c = new Carnet();
                c.setDisciplina(rs.getString(1));
                c.setActivitate(rs.getString(2));
                c.setNumeProfesor(rs.getString(3));
                if (rs.getInt(5) != 0) {
                    c.setData(rs.getDate(4));
                    c.setNota(String.valueOf(rs.getDouble(5)));
                } else {
                    c.setData(null);
                    c.setNota("fara nota");
                }
                c.setProcent(rs.getInt(6));
                notePartial.add(c);
            }

            rs = selectStatement.executeQuery("call noteFinale(" + curent.getIdUser() + ");");
            while (rs.next()) {
                Carnet c = new Carnet();
                c.setDisciplina(rs.getString(1));
                c.setNumeProfesor(rs.getString(2));
                c.setNota(String.valueOf(rs.getString(3)));
                noteFinal.add(c);
            }

            ((Student) curent).setNoteFinale(noteFinal);
            ((Student) curent).setNotePartiale(notePartial);
        } catch (SQLException e) {
            System.out.println("uita te la metoda Carnet!");
        }
    }

    public ArrayList<Mesaj> getMesaje(int idGrupa) {
        ArrayList<Mesaj> mesaje = new ArrayList<Mesaj>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM mesaje_grupe WHERE id_grupa = " + idGrupa + ";");
            while(rs.next()) {
                Mesaj mesaj = new Mesaj();
                mesaj.setIdGrupa(rs.getInt(3));
                mesaj.setSubiect(rs.getString(4));
                mesaj.setContinutMesaj(rs.getString(5));
                mesaj.setTimpPrimire(rs.getTimestamp(6));
                mesaj.setProgramare(rs.getInt(7));
                int idStudent = rs.getInt(2);
                ResultSet resultTemp = connection.createStatement().executeQuery("SELECT nume, prenume FROM vGetStudById WHERE id_student = " + idStudent + ";");
                resultTemp.next();
                mesaj.setNumeFrom(resultTemp.getString(1));
                mesaj.setPrenumeFrom(resultTemp.getString(2));
                mesaje.add(mesaj);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
        return mesaje;
    }

    public ArrayList<Grupe> getGrupeStudent() {
        ArrayList<Grupe> grupe = new ArrayList<Grupe>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM vGetDetaliiGrupe WHERE id_student = " + curent.getIdUser() + ";");
            while (rs.next()) {
                Grupe grupa = new Grupe();
                grupa.setIdGrupa(rs.getInt(2));
                grupa.setNumeAdmin(rs.getString(3));
                grupa.setPrenumeAdmin(rs.getString(4));
                grupa.setDenumireMaterie(rs.getString(5));
                grupa.setDescriereMaterie(rs.getString(6));
                if (rs.getInt(7) == 0) {
                    //System.out.println("Aikilsea");
                    // inutil dar sa fie :)))
                    grupa.setNumeProfesor(null);
                    grupa.setPrenumeProfesor(null);
                    //System.out.println("A iesit de akilisea");
                } else {
                    //System.out.println("Bine");
                    String statement = new String("SELECT nume, prenume FROM vGetProfById WHERE id_profesor = " + rs.getInt(7) + ";");
                    /*System.out.println(statement);*/
                    ResultSet resultTemp = connection.createStatement().executeQuery(statement);
                    resultTemp.next();
                    grupa.setNumeProfesor(resultTemp.getString(1));
                    grupa.setPrenumeProfesor(resultTemp.getString(2));
                    //System.out.println("Boon");
                }
                grupe.add(grupa);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
        return grupe;
    }

    public ArrayList<Grupe> getToateGrupele() {
        ArrayList<Grupe> grupe = new ArrayList<Grupe>();
        try {
            rs = selectStatement.executeQuery("SELECT * FROM vGetDetaliiGrupe;");
            while (rs.next()) {
                Grupe grupa = new Grupe();
                grupa.setIdGrupa(rs.getInt(2));
                grupa.setNumeAdmin(rs.getString(3));
                grupa.setPrenumeAdmin(rs.getString(4));
                grupa.setDenumireMaterie(rs.getString(5));
                grupa.setDescriereMaterie(rs.getString(6));
                if (rs.getString(7) == null) {
                    ResultSet resultTemp = connection.createStatement().executeQuery("SELECT nume, prenume FROM vGetProfById WHERE id_profesor = " + rs.getInt(7) + ";");
                    grupa.setNumeProfesor(resultTemp.getString(1));
                    grupa.setNumeProfesor(resultTemp.getString(2));
                } else {
                    // inutil dar sa fie :)))
                    grupa.setNumeProfesor(null);
                    grupa.setPrenumeProfesor(null);
                }
                grupe.add(grupa);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
        return grupe;
    }

    public void sendMessage(int pidGrupa, String subiect, String continutMesaj, int programare) {
        //PROCEDURE pTrimiteMesaj(IN pid_grupa INT, IN pid_from INT, IN psubiect VARCHAR(32), IN ptext TEXT, IN pprogramare TINYINT)
        try {
            selectStatement.executeQuery("call pTrimiteMesaj(" + pidGrupa + ", " + curent.getIdUser() + ", '" + subiect + "', '" + continutMesaj + "', " + programare + ");");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
    }

    public void setProfesorLaGrupa() {

    }

    public void participare(int id) {
        try{
            selectStatement.executeQuery("call pAdaugaIncaUnul(" + id + ");");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
    }

    public void creareGrupa(int pidCurs) {
        try {
            // pCreareGrupa(IN pid_student INT, IN pid_curs INT)
            selectStatement.executeQuery("call pCreareGrupa(" + curent.getIdUser() + ", " + pidCurs + ");");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
    }

    public ArrayList<Grupe> getGrupeLaCareNuE() {
        ArrayList<Grupe> grupe = new ArrayList<Grupe>();
        try {
            rs = selectStatement.executeQuery("call pGetGrupeBune(" + curent.getIdUser() + ");");
            while (rs.next()) {
                Grupe grupa = new Grupe();
                grupa.setIdGrupa(rs.getInt(1));
                grupa.setNumeAdmin(rs.getString(2));
                grupa.setPrenumeAdmin(rs.getString(3));
                grupa.setDenumireMaterie(rs.getString(4));
                grupa.setDescriereMaterie(rs.getString(5));
                if (rs.getInt(6) == 0) {
                    grupa.setNumeProfesor(null);
                    grupa.setPrenumeProfesor(null);
                } else {
                    String statement = new String("SELECT nume, prenume FROM vGetProfById WHERE id_profesor = " + rs.getInt(6) + ";");
                    ResultSet resultTemp = connection.createStatement().executeQuery(statement);
                    resultTemp.next();
                    grupa.setNumeProfesor(resultTemp.getString(1));
                    grupa.setPrenumeProfesor(resultTemp.getString(2));
                }
                grupe.add(grupa);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
        return grupe;
    }

    public boolean inscriereInGrupa(int pid_grupa) {
        String statement = new String("call pJoinLaGrupa(" + curent.getIdUser() + ", " + pid_grupa + ");");
        try{
            rs = selectStatement.executeQuery(statement);
            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            //throwables.printStackTrace();
        }
        return false;
    }

    public void renuntareLaGrupa(int pid_grupa) {
        try{
            // pRenuntaLaGrupa (IN pid_student INT, IN pid_grupa INT)
            selectStatement.executeUpdate("call pRenuntaLaGrupa(" + curent.getIdUser() + ", " + pid_grupa + ");");
        } catch (SQLException throwables) {
            System.out.println(throwables);
            //throwables.printStackTrace();
        }
    }
}