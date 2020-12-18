package bazaDate;

public class VedereStudentiLaCurs {
    private int idUser, idCursActivitate;
    private String numeStudent, prenumeStudent, nrTelefon, email, denumireCurs, descriereCurs, numeProfesor, prenumeProfesor;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCursActivitate() {
        return idCursActivitate;
    }

    public void setIdCursActivitate(int idCursActivitate) {
        this.idCursActivitate = idCursActivitate;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public String getPrenumeStudent() {
        return prenumeStudent;
    }

    public void setPrenumeStudent(String prenumeStudent) {
        this.prenumeStudent = prenumeStudent;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDenumireCurs() {
        return denumireCurs;
    }

    public void setDenumireCurs(String denumireCurs) {
        this.denumireCurs = denumireCurs;
    }

    public String getDescriereCurs() {
        return descriereCurs;
    }

    public void setDescriereCurs(String descriereCurs) {
        this.descriereCurs = descriereCurs;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public String getPrenumeProfesor() {
        return prenumeProfesor;
    }

    public void setPrenumeProfesor(String prenumeProfesor) {
        this.prenumeProfesor = prenumeProfesor;
    }

    @Override
    public String toString() {
        return new String(idUser + " " + numeStudent + " " + prenumeStudent + ", e-mail: " + email + ", Numar de telefon: " + nrTelefon);
    }
}
